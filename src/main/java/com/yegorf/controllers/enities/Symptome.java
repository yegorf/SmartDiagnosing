package com.yegorf.controllers.enities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Symptome {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String symptome;

    @OneToMany
    private Set<Match> matches;

    public Symptome(String symptome) {
        this.symptome = symptome;
    }
}
