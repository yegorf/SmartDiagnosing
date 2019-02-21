package com.yegorf.controllers.enities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Matches {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

//    @Column(name = "diagnose_id")
//    private int diagnoseId;
//
//    @Column(name = "symptome_id")
//    private int symptomeId;
//
//    public Matches(int diagnoseId, int symptomeId) {
//        this.diagnoseId = diagnoseId;
//        this.symptomeId = symptomeId;
//    }

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