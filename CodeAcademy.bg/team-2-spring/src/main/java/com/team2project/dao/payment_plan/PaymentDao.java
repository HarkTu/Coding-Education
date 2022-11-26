package com.team2project.dao.payment_plan;

import com.team2project.dto.PaymentPlan;

import java.math.BigDecimal;
import java.util.Optional;

public interface PaymentDao {
    Optional<PaymentPlan> findPaymentPlanByIban(String iban);

    void updatePaymentPlan(PaymentPlan plan, String iban);

}
