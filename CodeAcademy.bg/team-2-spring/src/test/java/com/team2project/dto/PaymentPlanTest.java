package com.team2project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PaymentPlanTest {
    @Test
    public void TestConstructor()
    {
        PaymentPlan paymentPlan = new PaymentPlan();
        assertNotNull(paymentPlan);
    }
}
