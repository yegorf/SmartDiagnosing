package com.yegorf.controllers.different;

import com.yegorf.controllers.enities.Diagnose;
import lombok.Data;

@Data
public class DiagnoseParams {
    private String diagnose;
    private double koef;
    private double dov = 0.0;
    private double ned = 0.0;

    public DiagnoseParams(String diagnose, double koef, double dov, double ned) {
        this.diagnose = diagnose;
        this.koef = koef;
        this.dov = dov;
        this.ned = ned;
    }
}
