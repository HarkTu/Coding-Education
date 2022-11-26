package com.team2project.dao.user;

import com.team2project.dto.Account;
import com.team2project.dto.AccountRequest;
import com.team2project.dto.UserRequest;
import com.team2project.dto.User;

import java.util.Optional;

public interface UserDao {
    void insertUser(UserRequest userRequest);
    void setInfo(AccountRequest account, Long id);
    Account getAccountInfoByUserId(Long id);
    void updateClientCreditRating(String rating, Long userId);
    void updateUsername(String newUsername, String oldUsername);
    void updatePassword(String newPassword, String username);
    void updateEmail(String newEmail, String username);
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);
}
