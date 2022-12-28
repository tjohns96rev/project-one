package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Moon;
import com.example.demo.repository.MoonDao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

@Service
public class MoonService {
    @Autowired
    private MoonDao moonDao;

    public List<Moon> findAll() {
        List<Moon> moons = this.moonDao.findAll();
        if (moons.size() == 0) {
            throw new EntityNotFoundException("No moons found");
        }
        return moons;
    }

    public Moon findByName(String name) {
        Optional<Moon> optMoon = this.moonDao.findByName(name);
        if (!optMoon.isPresent()) {
            throw new EntityNotFoundException("No moon found");
        }
        return optMoon.get();
    }

    public Moon findById(int id) {
        Optional<Moon> optMoon = this.moonDao.findById(id);
        if (!optMoon.isPresent()) {
            System.out.println("Hi!");
            throw new EntityNotFoundException("No moon found");
        }
        return optMoon.get();
    }

    public String createMoon(Moon moon) {
        this.moonDao.createMoon(moon.getName(), moon.getOwnerId());
        return "Moon created";
    }

    public String deleteById(int id) {
        this.moonDao.deleteById(id);
        return "Moon deleted";
    }

    public List<Moon> findByOwnerId(int ownerId) {
        List<Moon> moons = this.moonDao.findByOwnerId(ownerId);
        if (moons.size() == 0) {
            throw new EntityNotFoundException("No moons found that meet that criteria");
        }
        return moons;
    }
}
