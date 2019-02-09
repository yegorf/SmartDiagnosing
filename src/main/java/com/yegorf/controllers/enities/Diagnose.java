package com.yegorf.controllers.enities;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Diagnose {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String diagnose;
}

