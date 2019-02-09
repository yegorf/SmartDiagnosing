package com.yegorf.controllers.controllers;


import com.yegorf.controllers.enities.Diagnose;
import com.yegorf.controllers.enities.Symptome;
import com.yegorf.controllers.repos.DiagnoseRepo;
import com.yegorf.controllers.repos.SymptomeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class TestController {

    @Autowired
    private DiagnoseRepo diagnoseRepo;
    @Autowired
    private SymptomeRepo symptomeRepo;

    @GetMapping
    public String index(
            @RequestParam(name="name", required = false, defaultValue = "test") String name,
            Map<String, Object> model
    ) {
        model.put("name",name);
        return "index";
    }

    @GetMapping("/enter")
    public String enter(Map<String, Object> model) {
        Iterable<Diagnose> diagnoses = diagnoseRepo.findAll();
        Iterable<Symptome> symptomes = symptomeRepo.findAll();

        model.put("diagnoses", diagnoses);
        model.put("symptomes", symptomes);
        return "enter";
    }

    @GetMapping("/test")
    public String test(
            @RequestParam(name="name", required = false, defaultValue = "test") String name,
            Map<String, Object> model
    ) {
        model.put("name",name);
        return "test";
    }
}
