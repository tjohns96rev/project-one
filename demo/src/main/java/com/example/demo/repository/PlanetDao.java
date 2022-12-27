package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Planet;

public interface PlanetDao extends JpaRepository<Planet, Integer> {
    Optional<Planet> findByName(String name);

    @Transactional
    @Modifying
    @Query(value = "insert into planets values (default, :name, :ownerid)", nativeQuery = true)
    void createPlanet(@Param("name") String name, @Param("ownerid") String ownerid);

    @Transactional
    @Modifying
    @Query(value = "update planets set name = :name , ownerid = :ownerid where id = :id ", nativeQuery = true)
    int updatePlanet(@Param("name") String name, @Param("ownerid") String ownerid,
            @Param("id") int id);

}
