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

    public List<User> findAll() {
        List<User> users = this.userDao.findAll();
        if (users.size() == 0) {
            throw new EntityNotFound("No users are present in the database.");
        }
        return users;
    }

    public String createUser(User newUser) {
        this.userDao.createUser(newUser.getUsername(), newUser.getPassword());
        return "Registered user";
    }

    public String updateUser(User user) {
        int rowCount = this.userDao.updateUser(user.getUsername(), user.getPassword(), user.getId());
        if (rowCount == 0) {
            throw new EntityNotFoundException("Could not update user");
        }
        return "Player updated successfully";
    }

    public String deleteById(int id) {
        this.userDao.deleteById(id);
        return "User deleted";
    }
}
