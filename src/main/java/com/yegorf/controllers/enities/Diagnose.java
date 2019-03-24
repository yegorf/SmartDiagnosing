package com.yegorf.controllers.enities;


import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@EqualsAndHashCode(of="id")
@Entity
@NoArgsConstructor
public class Diagnose {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String diagnose;

    @OneToMany(mappedBy = "diagnose", cascade = CascadeType.ALL)
    private Set<Matches> matches;

    public Diagnose(String diagnose) {
        this.diagnose = diagnose;
    }

}

