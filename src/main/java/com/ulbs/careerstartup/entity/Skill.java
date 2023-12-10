package com.ulbs.careerstartup.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Skill {

    @Id
    @GeneratedValue
    public Long id;

    public String name;
}
