package com.team2project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team2project.dto.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static com.team2project.common.URLAddresses.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class UserControllerTest
{

  @Autowired
  MockMvc      mvc;
  @Autowired
  ObjectMapper mapper;


  @Test
  @DisplayName("Register User")
  void register() throws Exception
  {
    User user = new User();
    user.setUsername("trakata");
    user.setPass("12345");
    user.setEmail("trakata@gmail.com");

    mvc.perform(post(POST_USER_REGISTER)
           .contentType(MediaType.APPLICATION_JSON)
           .content(mapper.writeValueAsString(user)))
       .andDo(print()).andExpect(status().isCreated());
  }

  @Test
  @WithUserDetails("Sheri")
  void showProfile() throws Exception
  {
    mvc
        .perform(get(GET_USER_PROFILE)
            .contentType(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk()
        );
  }

  @Test
  @WithUserDetails("Sheri")
  void changeUsername() throws Exception
  {
    mvc.perform(patch(PATCH_USER_CHANGE_USERNAME)
           .queryParam("username", "Sherif"))
       .andExpect(status().isOk());
  }

  @Test
  @WithUserDetails("Tsvetelin")
  void changePassword() throws Exception
  {
    mvc.perform(patch(PATCH_USER_CHANGE_PASSWORD)
           .queryParam("oldPassword", "Ceco77@5")
           .queryParam("newPassword", "password")
           .queryParam("confirmedNewPassword", "password"))
       .andExpect(status().isOk());
  }

  @Test
  @WithUserDetails("Tsvetelin")
  void changeEmail() throws Exception
  {

    mvc.perform(patch(PATCH_USER_CHANGE_EMAIL)
           .queryParam("newEmail", "tsvetelin@mail.bg"))
       .andExpect(status().isOk());
  }
}