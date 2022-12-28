package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.User;
import com.example.demo.exceptions.EntityNotFound;
import com.example.demo.service.UserService;

@RestController
public class UserController {

    private static Logger userLogger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ExceptionHandler(EntityNotFound.class)
    public ResponseEntity<String> entityNotFound(EntityNotFound e) {
        userLogger.error(e.getLocalizedMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/user/id/{id}")
    public ResponseEntity<User> findById(@PathVariable int id) {
        return new ResponseEntity<>(this.userService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/api/user/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username) {
        return new ResponseEntity<>(this.userService.findByUsername(username), HttpStatus.OK);
    }

    @PostMapping("/api/user")
    public ResponseEntity<String> createUser(@RequestBody User newUser) {
        String message = this.userService.createUser(newUser);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}
