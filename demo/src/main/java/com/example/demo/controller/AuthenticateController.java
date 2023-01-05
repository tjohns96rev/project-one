package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.LoginInfo;
import com.example.demo.service.UserService;

@RestController
public class AuthenticateController {

    private boolean isLoggedIn;

    private static Logger authenticateLogger = LoggerFactory.getLogger(MoonController.class);

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    @Autowired
    private UserService userService;

    @ExceptionHandler
    public ResponseEntity<String> loginFailed(Error e) {
        authenticateLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginInfo loginInfo) {
        if (isLoggedIn) {
            return new ResponseEntity<>("You are already logged in. To switch accounts please logout first.",
                    HttpStatus.OK);
        }
        if (!userService.login(loginInfo)) {
            throw new Error("Sorry those credentials don't match any existing account.");
        }
        isLoggedIn = true;
        return new ResponseEntity<>("Login Successful", HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        isLoggedIn = false;
        return new ResponseEntity<>("Logged out", HttpStatus.OK);
    }
}
