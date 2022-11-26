package com.team2project.dao.leasing;

import com.team2project.common.Validation;
import com.team2project.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LeasingDaoImpl implements LeasingDao{

    private final BigDecimal leasingPercent = new BigDecimal(1.10);
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final Validation validation;

    @Autowired
    public LeasingDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations, Validation validation){
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.validation = validation;
    }

    @Override
    public void insertLeasingRequest(LeasingRequest leasingRequest) {
        String sql =
                " INSERT INTO requested_leases "
               +"       (item,                 "
               +"        amount,               "
               +"        months_to_pay,        "
               +"        fk_user_id)           "
               +"        VALUES                "
               +"       (:item,                "
               +"        :amount,              "
               +"        :months_to_pay,       "
               +"        :fk_user_id)          ";

        Long userId = validation.getCurrentLoggedUser().getUserId();

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("item", leasingRequest.getItem())
                .addValue("amount", leasingRequest.getItemPrice())
                .addValue("months_to_pay", leasingRequest.getMonths())
                .addValue("fk_user_id", userId);

        namedParameterJdbcOperations.update(sql, parameterSource);
    }


    @Override
    public void updateLeasing(LeasingRequest leasingRequest) {

        String sql =
                " INSERT INTO leases "
               +"        (item,      "
               +"        pmt_amount, "
               +"        start_date, "
               +"        end_date,   "
               +"        status,     "
               +"        fk_user_id) "
               +"       VALUES       "
               +"      (:item,       "
               +"       :pmt_amount, "
               +"       :start_date, "
               +"       :end_date,   "
               +"       :status,     "
               +"       :fk_user_id) ";

        Long userId = validation.getCurrentLoggedUser().getUserId();

        LocalDate endDate = LocalDate.now().plusMonths(leasingRequest.getMonths());

        BigDecimal amountWithInterest = leasingRequest.getItemPrice()
                .multiply(leasingPercent)
                .setScale(2, BigDecimal.ROUND_UP);

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("item", leasingRequest.getItem())
                .addValue("pmt_amount", amountWithInterest)
                .addValue("start_date", LocalDate.now())
                .addValue("end_date", endDate)
                .addValue("status", Leasing.Status.ACTIVE.name())
                .addValue("fk_user_id", userId);

        namedParameterJdbcOperations.update(sql, parameterSource);
    }

    @Override
    public void insertPaymentPlan(LeasingRequest leasingRequest, Long userId) {

        String sql =
                " INSERT INTO payment_plans "
               +"        (iban,             "
               +"         months_to_pay,    "
               +"         installment,      "
               +"         day_to_pay,       "
               +"         remaining_amount, "
               +"         fk_user_id)       "
               +"        VALUES             "
               +"       (:iban,             "
               +"        :months_to_pay,    "
               +"        :installment,      "
               +"        :day_to_pay,       "
               +"        :remaining_amount, "
               +"        :fk_user_id)       ";

        BigDecimal installment = new BigDecimal(leasingRequest.getItemPrice().longValue())
                .multiply(leasingPercent)
                .divide(BigDecimal.valueOf(leasingRequest.getMonths()))
                .setScale(2, RoundingMode.DOWN);


        BigDecimal remainingAmount = leasingRequest.getItemPrice()
                .multiply(leasingPercent)
                .setScale(2, RoundingMode.DOWN);

        LocalDate payDate = LocalDate.now().plusMonths(1);

        String iban = generateIban(userId);

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("iban", iban)
                .addValue("months_to_pay", leasingRequest.getMonths())
                .addValue("installment", installment)
                .addValue("day_to_pay", payDate)
                .addValue("remaining_amount", remainingAmount)
                .addValue("fk_user_id", userId);


        namedParameterJdbcOperations.update(sql, parameterSource);

    }
    @Override
    public List<PaymentPlan> getPaymentPlanByUserId(Long userId) {

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("fk_user_id", userId);

        String sql =
                " SELECT months_to_pay,          "
               +"        installment,            "
               +"        day_to_pay,             "
               +"        remaining_amount,       "
               +"        fk_user_id              "
               +" FROM payment_plans             "
               +" WHERE fk_user_id = :fk_user_id ";

            return namedParameterJdbcOperations.queryForObject(sql, parameterSource,
                    (rs, rowNum) -> {

                        List<PaymentPlan> paymentPlans = new ArrayList<>();
                        do {
                            PaymentPlan paymentPlan = new PaymentPlan();

                            paymentPlan.setLeaseTermInMonths(rs.getInt("months_to_pay"));
                            paymentPlan.setPayment(rs.getBigDecimal("installment"));
                            paymentPlan.setPayDate(rs.getDate("day_to_pay"));
                            paymentPlan.setRemainingAmount(rs.getBigDecimal("remaining_amount"));
                            paymentPlan.setUserId(rs.getLong("fk_user_id"));

                            paymentPlans.add(paymentPlan);
                        }while (rs.next());
                        return paymentPlans;
                    });
    }


    @Override
    public List<Leasing> getAllLeases(Long userId) {

        String sql =
                " SELECT item,                  "
               +"        pmt_amount,            "
               +"        start_date,            "
               +"        end_date,              "
               +"        status,                "
               +"        fk_user_id             "
               +" FROM leases                   ";

        List<Leasing> leases = namedParameterJdbcOperations
                .query(sql, rs -> {
                    List<Leasing> leasesViewList = new ArrayList<>();

                    while (rs.next()) {
                        if (rs.getLong("fk_user_id") != userId) {
                            continue;
                        }
                        leasesViewList.add(fillLeasingInfo(rs));
                    }
                    return leasesViewList;
                });
        return leases;


    }

    @Override
    public List<RequestedLeasesResponse> getRequestedLeases() {
        String sql =
                " SELECT item,          "
               +"        amount,        "
               +"        months_to_pay, "
               +"        fk_user_id     "
               +" FROM requested_leases ";


        List<RequestedLeasesResponse> requestedLeases = namedParameterJdbcOperations
                .query(sql,  new ResultSetExtractor<List<RequestedLeasesResponse>>()
                {
                    @Override
                    public List<RequestedLeasesResponse> extractData(ResultSet rs)
                            throws SQLException, DataAccessException
                    {
                        List<RequestedLeasesResponse> leasesViewList = new ArrayList<>();

                        while (rs.next()) {
                            RequestedLeasesResponse requestedLeasesResponse = new RequestedLeasesResponse();

                            requestedLeasesResponse.setItem(rs.getString("item"));
                            requestedLeasesResponse.setItemPrice(rs.getBigDecimal("amount"));
                            requestedLeasesResponse.setMonths(rs.getInt("months_to_pay"));
                            requestedLeasesResponse.setUserId(rs.getLong("fk_user_id"));

                            leasesViewList.add(requestedLeasesResponse);
                        }
                        return leasesViewList;
                    }
                });
        return requestedLeases;
    }

    @Override
    public List<Leasing> getApprovedLeases()
    {
        String sql =
                " SELECT leasing_id, "
               +"        item,       "
               +"        pmt_amount, "
               +"        start_date, "
               +"        end_date,   "
               +"        status,     "
               +"        fk_user_id  "
               +" FROM leases        ";


        List<Leasing> approvedLeases = namedParameterJdbcOperations
                .query(sql,  new ResultSetExtractor<List<Leasing>>()
                {
                    @Override
                    public List<Leasing> extractData(ResultSet rs)
                            throws SQLException, DataAccessException
                    {
                        List<Leasing> leasesViewList = new ArrayList<>();

                        while (rs.next()) {
                            if (rs.getObject("status",
                                    Leasing.Status.class) != Leasing.Status.ACTIVE){
                                continue;
                            }
                           leasesViewList.add(fillLeasingInfo(rs));
                        }
                        return leasesViewList;
                    }
                });
        return approvedLeases;
    }

    @Override
    public List<ClientsRatingResponse> getClientsRating()
    {
        String sql=
                " SELECT first_name,     "
              + "        last_name,      "
              + "        credit_rating, "
              + "        fk_user        "
              + " FROM accounts_info    ";

        List<ClientsRatingResponse> clientsRating = namedParameterJdbcOperations
                .query(sql,  new ResultSetExtractor<List<ClientsRatingResponse>>()
                {
                    @Override
                    public List<ClientsRatingResponse> extractData(ResultSet rs)
                            throws SQLException, DataAccessException
                    {
                        List<ClientsRatingResponse> clientsViewList = new ArrayList<>();

                        while (rs.next()) {
                            ClientsRatingResponse clientsRatingResponse = new ClientsRatingResponse();

                            clientsRatingResponse.setUserId(rs.getLong("fk_user"));
                            clientsRatingResponse.setFirstName(rs.getString("first_name"));
                            clientsRatingResponse.setLastName(rs.getString("last_name"));
                            clientsRatingResponse.setCreditRating(rs.getString("credit_rating"));

                            clientsViewList.add(clientsRatingResponse);

                        }
                        return clientsViewList;
                    }
                });
        return clientsRating;
    }

    private Leasing fillLeasingInfo(ResultSet rs)
            throws SQLException, DataAccessException
    {
        Leasing leasing = new Leasing();

        leasing.setItem(rs.getString("item"));
        leasing.setPmtAmount(rs.getBigDecimal("pmt_amount"));
        leasing.setStartDate(rs.getDate("start_date"));
        leasing.setEndDate(rs.getDate("end_date"));
        leasing.setStatus(rs.getObject("status", Leasing.Status.class));
        leasing.setUserId(rs.getLong("fk_user_id"));

        return leasing;
    }

    private String generateIban(Long userId) {
        int max = 99999999;
        int min = 10000000;

        int serialNumber = (int) (Math.random() * (max - min)) + max;

        String iban = String.format("%d%d",userId, serialNumber);
        return iban;
    }

}
