package com.team2project.dao.payment_plan;

import com.team2project.common.Validation;
import com.team2project.dto.PaymentPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public class PaymentDaoImpl implements PaymentDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Autowired
    public PaymentDaoImpl(NamedParameterJdbcOperations namedParameterJdbcOperations, Validation validation) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public Optional<PaymentPlan> findPaymentPlanByIban(String iban) {
        String sql =
                " SELECT iban,                   "
               +"        months_to_pay,          "
               +"        installment,            "
               +"        day_to_pay,             "
               +"        remaining_amount,       "
               +"        fk_user_id              "
               +" FROM payment_plans             "
               +" WHERE iban = :iban             ";

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("iban", iban);

        try {
            return Optional.ofNullable(namedParameterJdbcOperations.queryForObject(sql, parameterSource, (rs, rowNum) -> {
                PaymentPlan paymentPlan = new PaymentPlan();
                paymentPlan.setIban(rs.getString("iban"));
                paymentPlan.setLeaseTermInMonths(rs.getInt("months_to_pay"));
                paymentPlan.setPayment(rs.getBigDecimal("installment"));
                paymentPlan.setPayDate(rs.getDate("day_to_pay"));
                paymentPlan.setRemainingAmount(rs.getBigDecimal("remaining_amount"));
                paymentPlan.setUserId(rs.getLong("fk_user_id"));

                return paymentPlan;
            }));
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public void updatePaymentPlan(PaymentPlan plan, String iban) {

        String sql =
                " UPDATE payment_plans P                         "
               +"    SET P.iban = :iban,                        "
               +"        P.months_to_pay = :months_to_pay,      "
               +"        P.installment = :installment,          "
               +"        P.day_to_pay =:day_to_pay,             "
               +"        P.remaining_amount = :remaining_amount "
               +" WHERE iban = :iban                            ";

        LocalDate nextDate = LocalDate.now().plusMonths(1);

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("iban", iban)
                .addValue("months_to_pay", plan.getLeaseTermInMonths())
                .addValue("installment", plan.getPayment())
                .addValue("day_to_pay", nextDate)
                .addValue("remaining_amount", plan.getRemainingAmount());


        namedParameterJdbcOperations.update(sql, parameterSource);
    }
}
