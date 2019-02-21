package com.yegorf.controllers.controllers;

import com.yegorf.controllers.enities.Diagnose;
import com.yegorf.controllers.enities.Matches;
import com.yegorf.controllers.enities.Symptome;
import com.yegorf.controllers.repos.DiagnoseRepo;
import com.yegorf.controllers.repos.MatchesRepo;
import com.yegorf.controllers.repos.SymptomeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Map;

@Controller
@RequestMapping("enter")
public class EnterController {


    @Autowired
    private DiagnoseRepo diagnoseRepo;
    @Autowired
    private SymptomeRepo symptomeRepo;
    @Autowired
    private MatchesRepo matchesRepo;

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

        Matches matches;
        ArrayList<Symptome> sy = (ArrayList<Symptome>) symptomeRepo.findAll();

        for (String s : list) {
            for (Symptome symptome : sy) {
                if(symptome.getSymptome().equals(s)) {
                    matches = new Matches(diagnose, symptome);
                    matchesRepo.save(matches);
                }
            }
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
