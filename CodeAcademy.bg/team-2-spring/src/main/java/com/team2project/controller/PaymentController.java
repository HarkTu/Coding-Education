package com.team2project.controller;

import com.team2project.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

import static com.team2project.common.URLAddresses.*;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @PatchMapping(PATCH_PAY_LEASES)
    @ResponseStatus(HttpStatus.OK)
    public String payLeasing(@RequestParam BigDecimal amount, @RequestParam String iban){
       return paymentService.payLeases(amount, iban);
    }
}
