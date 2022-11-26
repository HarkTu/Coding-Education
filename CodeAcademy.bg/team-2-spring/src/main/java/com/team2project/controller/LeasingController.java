package com.team2project.controller;

import com.team2project.dto.*;
import com.team2project.service.LeasingService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.team2project.common.URLAddresses.*;


@RestController
public class LeasingController {
    private final LeasingService leasingService;

    public LeasingController(LeasingService leasingService) {
        this.leasingService = leasingService;
    }

    @PostMapping(POST_REQUEST_LEASING)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public String newLeasingRequest(@RequestBody LeasingRequest leasingRequest)
    {
        return leasingService.createLeasingRequest(leasingRequest);
    }

    @GetMapping(GET_USER_LEASES)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    public List<Leasing> showCurrentLoggedUserLeases(){
        return leasingService.showUserLeases();
    }

    @GetMapping(GET_REQUESTED_LEASES)
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public List<RequestedLeasesResponse> showAllRequestedLeases(){
        return leasingService.showRequestedLeases();
    }

    @GetMapping(GET_APPROVED_LEASES)
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public List<Leasing> showAllApprovedLeases(){
        return leasingService.showApprovedLeases();
    }

    @GetMapping(GET_CLIENTS_CREDIT_RATING)
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientsRatingResponse> showAllClientsRating(){
        return leasingService.showClientsCreditRating();
    }

}
