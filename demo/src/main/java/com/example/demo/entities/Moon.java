package com.example.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "moons")
public class Moon {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "ownderId")
    private int ownderId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getownderId() {
        return ownderId;
    }

    public void setownderId(int ownderId) {
        this.ownderId = ownderId;
    }

    @Override
    public String toString() {
        return "Moon [id=" + id + ", name=" + name + ", ownderId=" + ownderId + "]";
    }
}
