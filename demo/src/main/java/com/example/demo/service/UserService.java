package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.exceptions.EntityNotFound;
import com.example.demo.repository.UserDao;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User findById(int id) {
        Optional<User> optUser = this.userDao.findById(id);
        if (!optUser.isPresent()) {
            throw new EntityNotFoundException("User not found");
        }
        return optUser.get();
    }

    public User findByUsername(String username) {
        Optional<User> optUser = this.userDao.findByUsername(username);
        if (!optUser.isPresent()) {
            throw new EntityNotFoundException("User not found");
        }
        return optUser.get();
    }

    public String createUser(User newUser) {
        this.userDao.createUser(newUser.getUsername(), newUser.getPassword());
        return "Registered user";
    }
}
