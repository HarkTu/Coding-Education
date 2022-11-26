package com.team2project.service;

import com.team2project.dto.AccountRequest;
import com.team2project.dto.UserRequest;

public interface UserService {

    String createUser(UserRequest userRequest);
    void implementUserInfo(AccountRequest accountRequest);
    String getMyProfile();
    String getMyAccount();
    String changeUsername(String newUsername);
    String changePassword(String oldPassword, String newPassword, String confirmedNewPassword);
    String changeEmail(String newEmail);

}
