package com.yegorf.controllers.enities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Diagnose {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String diagnose;

    public Diagnose(String diagnose) {
        this.diagnose = diagnose;
    }
}

