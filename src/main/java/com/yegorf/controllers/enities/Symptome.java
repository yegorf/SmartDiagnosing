package com.yegorf.controllers.enities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(of="id")
@Entity
@NoArgsConstructor
public class Symptome {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String symptome;

    @OneToMany(mappedBy = "symptome", cascade = CascadeType.ALL)
    private Set<Matches> matches;

    public Symptome(String symptome) {
        this.symptome = symptome;
    }
}
