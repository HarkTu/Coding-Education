package com.team2project.common;


import com.team2project.dao.user.UserDao;
import com.team2project.dto.Account;
import com.team2project.dto.Account.FamilyStatus;
import com.team2project.dto.Account.PrimaryCurrency;
import com.team2project.dto.AccountRequest;
import com.team2project.dto.PaymentPlan;
import com.team2project.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.InvalidParameterException;

@Component
public class ValidationImpl implements Validation {

  private final UserDao userDao;

  @Autowired
  public ValidationImpl(UserDao userDao) {
    this.userDao = userDao;

  }

  @Override
  public void checkIfUsernameExists(String username) {
    if (userDao.findUserByUsername(username).isPresent()) {
      throw new InvalidParameterException("Username already exist");
    }
  }

  @Override
  public void checkIfEmailExists(String email) {
    if (userDao.findUserByEmail(email).isPresent()) {
      throw new InvalidParameterException("This email already exist!");
    }
  }

  @Override
  public void checkIfPasswordsMatches(String oldPassword, String expectedPassword,
      String confirmedPassword, String newPassword) {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    if (!passwordEncoder.matches(oldPassword, expectedPassword)) {
      throw new InvalidParameterException("Wrong password!");
    }
    if (!newPassword.matches(confirmedPassword)) {
      throw new InvalidParameterException("Password don`t match!");
    }
  }

  @Override
  public void checkIfAccountRequestInfoValid(AccountRequest accountRequest) {
    if (accountRequest.getFirstName().length() < 2) {
      throw new IllegalArgumentException("Your name should be at least 2 symbols!");
    }
    if (accountRequest.getLastName().length() < 2) {
      throw new IllegalArgumentException("Your name should be at least 2 symbols!");
    }
    if (accountRequest.getFamilyStatus().name().equalsIgnoreCase(FamilyStatus.MARRIED.name())
        && accountRequest.getFamilyMembers() < 2) {
      throw new IllegalArgumentException(
          "The members of your family must be at least 2 since you're married!");
    }
    if (accountRequest.getFamilyMembers() < 1) {
      throw new IllegalArgumentException(
          "The members of your family must be at least 1!");
    }

    if (accountRequest.getSalary().longValue()<0){
      throw new IllegalArgumentException("Your salary can not be negative!");
    }
  }


  @Override
  public User getCurrentLoggedUser() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String principalUsername = "";
    if (principal instanceof UserDetails) {
      principalUsername = ((UserDetails) principal).getUsername();
    }
    return userDao.findUserByUsername(principalUsername)
        .orElseThrow(() -> new InvalidParameterException("User not logged in!"));

  }

}
