package com.team2project.common;

import com.team2project.dto.AccountRequest;
import com.team2project.dto.User;

public interface Validation {

  void checkIfUsernameExists(String username);

  void checkIfEmailExists(String email);

  void checkIfPasswordsMatches(String oldPassword, String expectedPassword,
      String confirmedPassword, String newPassword);

  void checkIfAccountRequestInfoValid(AccountRequest accountRequest);

  User getCurrentLoggedUser();

}
