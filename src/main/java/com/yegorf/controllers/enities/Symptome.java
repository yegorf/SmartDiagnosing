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
public class Symptome {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String symptome;

    public Symptome(String symptome) {
        this.symptome = symptome;
    }
}
