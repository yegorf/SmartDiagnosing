package com.yegorf.controllers.controllers;


import com.yegorf.controllers.enities.Diagnose;
import com.yegorf.controllers.enities.Symptome;
import com.yegorf.controllers.repos.DiagnoseRepo;
import com.yegorf.controllers.repos.SymptomeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class TestController {

    @Autowired
    private DiagnoseRepo diagnoseRepo;
    @Autowired
    private SymptomeRepo symptomeRepo;

    @GetMapping
    public String index(Map<String, Object> model) {
        return "index";
    }

    public void showList(Map<String, Object> model) {
        Iterable<Diagnose> diagnoses = diagnoseRepo.findAll();
        Iterable<Symptome> symptomes = symptomeRepo.findAll();

        model.put("diagnoses", diagnoses);
        model.put("symptomes", symptomes);
    }

    @GetMapping("/enter")
    public String enter(Map<String, Object> model) {
        showList(model);
        return "enter";
    }

    @PostMapping("/enter")
    public String addDiagnose(@RequestParam String text, @RequestParam String sym, Map<String, Object> model) {

        if(!text.equals("")) {
            Diagnose diagnose = new Diagnose(text);
            diagnoseRepo.save(diagnose);

            showList(model);
        } else if(!sym.equals("")) {
            Symptome symptome = new Symptome(sym);
            symptomeRepo.save(symptome);

            showList(model);
        } else {
            showList(model);
        }
        return "enter";
    }




    //Iterable<Symptome> sym = symptomeRepo.findAll();
    //Deque<Symptome> list = (Deque<Symptome>) symptomeRepo.findAll();

    @GetMapping("/test")
    public String test(Map<String, Object> model) {
        //showList(model);

        //model.put("symptom", list.peek().getSymptome());
        return "test";
    }

    @PostMapping()
    public String addDiagnose(@RequestParam String ok, Map<String, Object> model) {

        return "test";
    }


}
