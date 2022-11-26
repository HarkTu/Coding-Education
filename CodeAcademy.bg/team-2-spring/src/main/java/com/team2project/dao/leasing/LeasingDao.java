package com.team2project.dao.leasing;

import com.team2project.dto.*;

import java.util.List;

public interface LeasingDao {

    void insertLeasingRequest(LeasingRequest leasingRequest);
    void updateLeasing(LeasingRequest leasingRequest);
    void insertPaymentPlan(LeasingRequest leasingRequest, Long userId);
    List<PaymentPlan> getPaymentPlanByUserId(Long userId);
    List<Leasing> getAllLeases(Long userId);
    List<RequestedLeasesResponse> getRequestedLeases();
    List<Leasing> getApprovedLeases();
    List<ClientsRatingResponse> getClientsRating();

}
