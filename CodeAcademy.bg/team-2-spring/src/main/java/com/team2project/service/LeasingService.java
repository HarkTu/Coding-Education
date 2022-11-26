package com.team2project.service;

import com.team2project.dto.*;

import java.util.List;
import java.util.Map;

public interface LeasingService {

    String createLeasingRequest(LeasingRequest leasingRequest);
    List<Leasing> showUserLeases();
    List<RequestedLeasesResponse> showRequestedLeases();
    List<Leasing> showApprovedLeases();
    List<ClientsRatingResponse> showClientsCreditRating();
}
