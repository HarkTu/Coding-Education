package com.team2project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.team2project.common.URLAddresses.LOGIN;


@RestController
public class LoginController {

    @PostMapping(LOGIN)
    @ResponseStatus(HttpStatus.OK)
    public String login()
    {return "Successfully login!";}
}
