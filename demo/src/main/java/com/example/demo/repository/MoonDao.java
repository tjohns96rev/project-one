package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Moon;

public interface MoonDao extends JpaRepository<Moon, Integer> {
    Optional<Moon> findByName(String name);

    @Transactional
    @Modifying
    @Query(value = "insert into moons values (default, :name, :ownerid)", nativeQuery = true)
    void createMoon(@Param("name") String name, @Param("ownerid") String ownerid);

    @Transactional
    @Modifying
    @Query(value = "update moons set name = :name , ownerid = :ownerid where id = :id ", nativeQuery = true)
    int updateMoon(@Param("name") String name, @Param("ownerid") String ownerid,
            @Param("id") int id);

}
