package com.team2project.service;

import com.team2project.common.Validation;
import com.team2project.dao.payment_plan.PaymentDao;
import com.team2project.dto.PaymentPlan;
import com.team2project.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PaymentServiceImpl implements PaymentService{

    private final PaymentDao paymentDao;
    private final Validation validation;

    @Autowired
    public PaymentServiceImpl(PaymentDao paymentDao, Validation validation) {
        this.paymentDao = paymentDao;
        this.validation = validation;
    }

    @Override
    public String payLeases(BigDecimal amount, String iban) {

        if (!paymentDao.findPaymentPlanByIban(iban).isPresent()){
            return "Wrong iban!";
        }

        User user = validation.getCurrentLoggedUser();
        PaymentPlan paymentPlan = paymentDao.findPaymentPlanByIban(iban).get();

        BigDecimal remainingAmount = paymentPlan.getRemainingAmount();
        BigDecimal installment = paymentPlan.getPayment();
        int monthsLeft = paymentPlan.getLeaseTermInMonths();

        if (user.getUserId().compareTo(paymentPlan.getUserId()) < 0){
            return "This is not your payment plan!";
        }
        if (remainingAmount.compareTo(BigDecimal.ZERO) < 0 || monthsLeft <= 0) {

            paymentPlan.setPayment(BigDecimal.ZERO);
            return "The leasing with given iban is already paid!";
        }
        if (amount.compareTo(remainingAmount) == 0){
            paymentPlan.setPayment(BigDecimal.ZERO);
            paymentPlan.setLeaseTermInMonths(0);
            paymentPlan.setRemainingAmount(BigDecimal.ZERO);
            paymentDao.updatePaymentPlan(paymentPlan, iban);
            return "Successfully paid!";
        }
        if (amount.compareTo(remainingAmount) > 0){
            amount = amount.subtract(remainingAmount);
            paymentPlan.setPayment(BigDecimal.ZERO);
            paymentPlan.setLeaseTermInMonths(0);
            paymentPlan.setRemainingAmount(BigDecimal.ZERO);
            paymentDao.updatePaymentPlan(paymentPlan, iban);
            return String.format("Successfully paid! Here is your exchange %s", amount);
        }
        if (amount.compareTo(installment) <= 0){

            paymentPlan.setRemainingAmount(remainingAmount.subtract(installment));
            paymentPlan.setLeaseTermInMonths(monthsLeft - 1);

            paymentDao.updatePaymentPlan(paymentPlan, iban);
            return "Paid successfully!";
        }

        PaymentPlan newPaymentPlan = calculateNewPlan(remainingAmount, installment, monthsLeft, amount);
        paymentDao.updatePaymentPlan(newPaymentPlan, iban);
        return "Paid successfully! Your payment plan has been changed!";

    }

    private PaymentPlan calculateNewPlan(BigDecimal remainingAmount,
                                         BigDecimal installment,
                                         int monthsLeft,
                                         BigDecimal amount) {

        PaymentPlan plan = new PaymentPlan();

        BigDecimal paidAmount = amount;

        BigDecimal monthsPaidExtra = paidAmount.divide(installment, 0, RoundingMode.DOWN)
                .subtract(BigDecimal.valueOf(1));

        remainingAmount = remainingAmount.subtract(amount);

        BigDecimal interestPerMonth = installment.subtract(installment
                .divide(BigDecimal.valueOf(1.1), 2, RoundingMode.FLOOR));

        interestPerMonth = interestPerMonth.multiply(monthsPaidExtra);

        remainingAmount = remainingAmount.subtract(interestPerMonth);

        BigDecimal newInstallment = remainingAmount.divide(BigDecimal.valueOf(monthsLeft - 1)
                .subtract(monthsPaidExtra), 2, RoundingMode.FLOOR);

        plan.setLeaseTermInMonths(monthsLeft - (monthsPaidExtra).intValue() - 1);
        plan.setPayment(newInstallment);
        plan.setRemainingAmount(remainingAmount);

        return plan;
    }

}
