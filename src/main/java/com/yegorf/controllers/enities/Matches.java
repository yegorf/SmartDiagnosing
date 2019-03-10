package com.yegorf.controllers.enities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of="id")
@NoArgsConstructor
@Entity
public class Matches {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "diagnose_id")
    private Diagnose diagnose;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "symptome_id")
    private Symptome symptome;

    public Matches(Diagnose diagnose, Symptome symptome) {
        this.diagnose = diagnose;
        this.symptome = symptome;
    }
}