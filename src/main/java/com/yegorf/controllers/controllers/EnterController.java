package com.yegorf.controllers.controllers;

import com.yegorf.controllers.enities.Diagnose;
import com.yegorf.controllers.enities.Symptome;
import com.yegorf.controllers.repos.DiagnoseRepo;
import com.yegorf.controllers.repos.SymptomeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("enter")
public class EnterController {
    @Autowired
    private DiagnoseRepo diagnoseRepo;
    @Autowired
    private SymptomeRepo symptomeRepo;


    private void showList(Map<String, Object> model) {
        Iterable<Diagnose> diagnoses = diagnoseRepo.findAll();
        Iterable<Symptome> symptomes = symptomeRepo.findAll();

        model.put("diagnoses", diagnoses);
        model.put("symptomes", symptomes);
    }

    @GetMapping
    public String enter(Map<String, Object> model) {
        showList(model);
        return "enter";
    }

    @PostMapping(params = {"text", "list"})
    public String addA(@RequestParam String text,
                       @RequestParam String[] list,
                       Map<String, Object> model) {
        Diagnose diagnose = new Diagnose(text);
        diagnoseRepo.save(diagnose);
        showList(model);

        for(String s : list) {
            System.out.println(s);
        }

        return "enter";
    }

    @PostMapping(params = "sym")
    public String addB(@RequestParam String sym, Map<String, Object> model) {
        Symptome symptome = new Symptome(sym);
        symptomeRepo.save(symptome);
        showList(model);

        return "enter";
    }
}
