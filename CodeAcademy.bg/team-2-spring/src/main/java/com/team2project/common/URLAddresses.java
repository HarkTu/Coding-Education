package com.team2project.common;

public class URLAddresses {

    public static final String LOGIN = "/api/v1/login";
    public static final String POST_USER_REGISTER = "/api/v1/user/register";
    public static final String GET_USER_PROFILE = "/api/v1/user/my/profile";
    public static final String GET_ACCOUNT_INFO ="/api/v1/user/my/account";
    public static final String PATCH_USER_CHANGE_USERNAME = "/api/v1/user/change/username";
    public static final String PATCH_USER_CHANGE_PASSWORD = "/api/v1/user/change/password";
    public static final String PATCH_USER_CHANGE_EMAIL = "/api/v1/user/change/email";
    public static final String POST_REQUEST_LEASING ="/api/v1/request/leasing";
    public static final String GET_USER_LEASES ="/api/v1/my/leases";
    public static final String CHANGE_USER_ACCOUNT_INFO ="/api/v1/user/account/change";

    public static final String PATCH_PAY_LEASES ="/api/v1/pay/leases";

    public static final String GET_REQUESTED_LEASES ="/api/v1/view/requested/leases";

    public static final String GET_APPROVED_LEASES ="/api/v1/view/approved/leases";
    public static final String GET_CLIENTS_CREDIT_RATING ="/api/v1/view/clients/rating";

}
