package com.team2project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team2project.dto.Leasing;
import com.team2project.dto.LeasingRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static com.team2project.common.URLAddresses.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class LeasingControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;


    @Test
    @WithUserDetails("emoen")
    void newLeasingRequest() throws Exception{

        LeasingRequest leasingRequest = new LeasingRequest();
        leasingRequest.setItem("TV");
        leasingRequest.setItemPrice(BigDecimal.valueOf(2000));
        leasingRequest.setMonths(12);

        mvc
                .perform(post(POST_REQUEST_LEASING)
                        .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(leasingRequest)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("emoen")
    void newLeasingRequestWithItemThatCantAfford() throws Exception{

        LeasingRequest leasingRequest = new LeasingRequest();
        leasingRequest.setItem("Car");
        leasingRequest.setItemPrice(BigDecimal.valueOf(200000));
        leasingRequest.setMonths(12);

        mvc
                .perform(post(POST_REQUEST_LEASING)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(leasingRequest)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("vankata")
    void showCurrentLoggedUserLeases() throws Exception{
        mvc
                .perform(get(GET_USER_LEASES)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("Tsvetelin")
    void showAllRequestedLeases() throws Exception{
        mvc
                .perform(get(GET_REQUESTED_LEASES)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("vankata")
    void showAllRequestedLeasesWithoutPermits() throws Exception{
        mvc
                .perform(get(GET_REQUESTED_LEASES)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("Tsvetelin")
    void showAllApprovedLeases() throws Exception{
        mvc
                .perform(get(GET_APPROVED_LEASES)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("vankata")
    void showAllApprovedLeasesWithoutPermits() throws Exception{
        mvc
                .perform(get(GET_APPROVED_LEASES)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("Tsvetelin")
    void showAllClientsRating() throws Exception{
        mvc
                .perform(get(GET_CLIENTS_CREDIT_RATING)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("vankata")
    void showClientRatingWithoutPermits() throws Exception{
        mvc
                .perform(get(GET_CLIENTS_CREDIT_RATING)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
}