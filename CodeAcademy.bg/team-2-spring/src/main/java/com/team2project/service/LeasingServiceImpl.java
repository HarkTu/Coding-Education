package com.team2project.service;

import com.team2project.common.Validation;
import com.team2project.dao.leasing.LeasingDao;
import com.team2project.dao.user.UserDao;
import com.team2project.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Transactional
@Service
public class LeasingServiceImpl implements LeasingService {

    private final LeasingDao leasingDao;
    private final Validation validation;
    private final UserDao userDao;

    @Autowired
    public LeasingServiceImpl(LeasingDao leasingDao, Validation validation, UserDao userDao) {
        this.leasingDao = leasingDao;
        this.validation = validation;
        this.userDao = userDao;
    }

    @Override
    public String createLeasingRequest(LeasingRequest leasingRequest) {

        Long userId = validation.getCurrentLoggedUser().getUserId();
        leasingDao.insertLeasingRequest(leasingRequest);

        String rating = calculateClientCreditRating(leasingRequest, userId);

        if (rating.matches("Reliable")) {
            leasingDao.updateLeasing(leasingRequest);
            leasingDao.insertPaymentPlan(leasingRequest, userId);
            userDao.updateClientCreditRating(rating, userId);
            return "You have successfully applied for a lease!";
        }

        userDao.updateClientCreditRating(rating, userId);
        return "Sorry, but we are not able to grant you a new lease!";
    }

    @Override
    public List<Leasing> showUserLeases() {
        Long userId = validation.getCurrentLoggedUser().getUserId();

        return leasingDao.getAllLeases(userId);
    }

    @Override
    public List<RequestedLeasesResponse> showRequestedLeases() {

        return leasingDao.getRequestedLeases();
    }

    @Override
    public List<Leasing> showApprovedLeases() {

        return leasingDao.getApprovedLeases();
    }

    @Override
    public List<ClientsRatingResponse> showClientsCreditRating() {
        return leasingDao.getClientsRating();
    }

    public String calculateClientCreditRating(LeasingRequest leasingRequest, Long userId) {

        Account currAccount = userDao.getAccountInfoByUserId(userId);
        BigDecimal clientMoneyLeft = getClientMoneyLeft(currAccount);

        BigDecimal interestRate = new BigDecimal(1.10);
        BigDecimal installmentFromPreviousPlan = BigDecimal.ZERO;
        BigDecimal installment;

        if (!leasingDao.getAllLeases(userId).isEmpty()) {

            List<PaymentPlan> paymentPlans = leasingDao.getPaymentPlanByUserId(userId);
            for (PaymentPlan plan : paymentPlans) {
                installmentFromPreviousPlan = installmentFromPreviousPlan.add(plan.getPayment());
            }

            installment = (leasingRequest.getItemPrice()
                    .multiply(interestRate))
                    .divide(BigDecimal.valueOf(leasingRequest.getMonths()), 2, RoundingMode.FLOOR);

            installment = installment.add(installmentFromPreviousPlan);

        } else {
            installment = leasingRequest.getItemPrice().multiply(interestRate);
            installment = installment
                    .divide(BigDecimal.valueOf(leasingRequest.getMonths()), 2, RoundingMode.FLOOR);
        }

        if (installment.longValue() > clientMoneyLeft.longValue()) {
            return "Low";
        }
        return "Reliable";
    }

    private BigDecimal getClientMoneyLeft(Account account) {

        BigDecimal clientMoneyLeft = account.getSalary();

        int familyMembers = account.getFamilyMembers();
        BigDecimal moneyForKid = new BigDecimal(500);
        BigDecimal moneyForAdult = new BigDecimal(1000);

        if (account.getFamilyStatus().equals(Account.FamilyStatus.MARRIED)) {
            clientMoneyLeft = clientMoneyLeft.subtract(BigDecimal
                    .valueOf(moneyForAdult.longValue())
                    .multiply(BigDecimal.valueOf(2)));
            familyMembers -= 2;
        } else {
            clientMoneyLeft = clientMoneyLeft.subtract(BigDecimal
                    .valueOf(moneyForAdult.longValue()));

            familyMembers -= 1;
        }

        if (familyMembers != 0) {
            clientMoneyLeft = clientMoneyLeft.subtract(BigDecimal
                    .valueOf(moneyForKid.longValue())
                    .multiply(BigDecimal.valueOf(familyMembers)));
        }

        return clientMoneyLeft;
    }

}
