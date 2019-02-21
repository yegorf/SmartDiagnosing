package com.yegorf.controllers.enities;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Diagnose {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String diagnose;

    @OneToMany
    private Set<Match> matches;

    public Diagnose(String diagnose) {
        this.diagnose = diagnose;
    }
}

