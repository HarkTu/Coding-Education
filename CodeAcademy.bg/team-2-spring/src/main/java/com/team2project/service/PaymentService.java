package com.team2project.service;

import java.math.BigDecimal;

public interface PaymentService {
    String payLeases(BigDecimal amount, String iban);
}
