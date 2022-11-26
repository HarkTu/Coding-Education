package com.team2project.service;

import com.team2project.common.Validation;
import com.team2project.dao.user.UserDao;
import com.team2project.dto.Account;
import com.team2project.dto.AccountRequest;
import com.team2project.dto.User;
import com.team2project.dto.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final Validation validation;

    @Autowired
    public UserServiceImpl(UserDao userDao, Validation validation) {
        this.userDao = userDao;
        this.validation = validation;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public String createUser(UserRequest userRequest) {

        validation.checkIfUsernameExists(userRequest.getUsername());
        validation.checkIfEmailExists(userRequest.getEmail());
        userRequest.setPass(passwordEncoder.encode(userRequest.getPass()));
        userDao.insertUser(userRequest);

        return "Registered successfully";
    }

    @Override
    public void implementUserInfo(AccountRequest accountRequest) {

        Long userId = validation.getCurrentLoggedUser().getUserId();
        validation.checkIfAccountRequestInfoValid(accountRequest);
        userDao.setInfo(accountRequest, userId);
    }

    @Override
    public String getMyProfile() {

        User user = validation.getCurrentLoggedUser();
        return user.toString();
    }

    @Override
    public String getMyAccount() {
        User user = validation.getCurrentLoggedUser();
        Account account = userDao.getAccountInfoByUserId(user.getUserId());
        return account.toString();
    }

    @Override
    public String changeUsername(String newUsername) {

        String username = validation.getCurrentLoggedUser().getUsername();
        validation. checkIfUsernameExists(newUsername);
        userDao.updateUsername(newUsername, username);

        return "Username changed successful!";
    }

    @Override
    public String changePassword(String oldPassword, String newPassword, String confirmedNewPassword) {

        String expectedPassword = validation.getCurrentLoggedUser().getPass();
        String username = validation.getCurrentLoggedUser().getUsername();

        validation.checkIfPasswordsMatches(oldPassword, expectedPassword, confirmedNewPassword, newPassword);
        newPassword = passwordEncoder.encode(newPassword);
        userDao.updatePassword(newPassword, username);

        return "Password changed!";
    }

    @Override
    public String changeEmail(String newEmail) {

        validation.checkIfEmailExists(newEmail);
        userDao.updateEmail(newEmail, validation.getCurrentLoggedUser().getUsername());

        return "Email changed!";
    }

}
