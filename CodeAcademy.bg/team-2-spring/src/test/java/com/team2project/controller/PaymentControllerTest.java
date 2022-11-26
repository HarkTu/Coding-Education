package com.team2project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static com.team2project.common.URLAddresses.PATCH_PAY_LEASES;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class PaymentControllerTest {


    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;


    @Test
    @WithUserDetails("emoen")
    void payLeasingWithInstallmentAmount() throws Exception{

        mvc
            .perform(patch(PATCH_PAY_LEASES)
                    .queryParam("amount", "1191.67")
                    .queryParam("iban", "9167354032")
                    .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("emoen")
    void payLeasingWithBiggerAmount() throws Exception{

        mvc
                .perform(patch(PATCH_PAY_LEASES)
                        .queryParam("amount", "2000")
                        .queryParam("iban", "9167354032")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("emoen")
    void payLeasingWithSmallerAmount() throws Exception{

        mvc
                .perform(patch(PATCH_PAY_LEASES)
                        .queryParam("amount", "500")
                        .queryParam("iban", "9167354032")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}