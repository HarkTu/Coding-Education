package com.team2project.controller;

import com.team2project.dto.AccountRequest;
import com.team2project.dto.UserRequest;
import com.team2project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.team2project.common.URLAddresses.*;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(POST_USER_REGISTER)
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody UserRequest userRequest)
    {
        return userService.createUser(userRequest);
    }

    @PutMapping(CHANGE_USER_ACCOUNT_INFO)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.OK)
    public void setProfile(@RequestBody AccountRequest account) {
        userService.implementUserInfo(account);
    }

    @GetMapping(GET_USER_PROFILE)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public String showProfile()
    {
        return userService.getMyProfile();
    }

    @GetMapping(GET_ACCOUNT_INFO)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public String showMyAccount()
    {
        return userService.getMyAccount();
    }


    @PatchMapping(PATCH_USER_CHANGE_USERNAME)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public String changeUsername(@RequestParam String username)
    {
        return userService.changeUsername(username);
    }

    @PatchMapping(PATCH_USER_CHANGE_PASSWORD)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public String changePassword(@RequestParam String oldPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String confirmedNewPassword)
    {
        return userService.changePassword(oldPassword, newPassword, confirmedNewPassword);
    }

    @PatchMapping(PATCH_USER_CHANGE_EMAIL)
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public String changeEmail(@RequestParam String newEmail)
    {
        return userService.changeEmail(newEmail);
    }
}
