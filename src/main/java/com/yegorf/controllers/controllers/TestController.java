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
@RequestMapping("test")
public class TestController {

    @Autowired
    private DiagnoseRepo diagnoseRepo;
    @Autowired
    private SymptomeRepo symptomeRepo;
    @Autowired
    private MatchesRepo matchesRepo;

    @GetMapping
    public String test(Map<String, Object> model) {
        Iterable<Symptome> sym = symptomeRepo.findAll();

        model.put("symptoms", sym);
        return "test";
    }

    @PostMapping
    public String go(@RequestParam String[] list,
                     Map<String, Object> model) {

        int COUNT = list.length;
        int count = 0;

        ArrayList<Diagnose> diagnoses = (ArrayList<Diagnose>) diagnoseRepo.findAll();

        for(Diagnose d : diagnoses) {
            ArrayList<Matches> matches = matchesRepo.findAllByDiagnose(d);

            for(Matches m : matches) {
                System.out.println(m.getDiagnose().getDiagnose() + " " + m.getSymptome().getSymptome());
                System.out.println();
                for(String s : list) {
                    if(s.equals(m.getSymptome().getSymptome())) {
                        count++;
                    }
                }
            }
            if(count == COUNT) {
                //System.out.println("Ваш диагноз - " + d.getDiagnose());
                //break;
                model.put("res",d.getDiagnose());
                return "result";
            } else {
               // System.out.println("Мы хз чем вы больны");
                model.put("res", "idk");
            }
        }

        return "result";
    }
}
