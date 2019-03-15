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

import java.io.IOException;
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
        ArrayList<Symptome> sym = (ArrayList<Symptome>) symptomeRepo.findAll();

        model.put("symptoms", sym);
        return "test";
    }

    @PostMapping
    public String go(@RequestParam String[] list,
                     Map<String, Object> model) throws IOException {

        int count = 0;
        ArrayList<Diagnose> diagnoses = (ArrayList<Diagnose>) diagnoseRepo.findAll();
        model.put("res", "определить не удалось");

        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<Symptome> symptomes = (ArrayList<Symptome>) symptomeRepo.findAll();
        for(Symptome s : symptomes) {
            for(String ss : list) {
                if(Integer.parseInt(ss) == s.getId()) {
                    list1.add(s.getSymptome());
                }
            }
        }
        int COUNT = list.length;

        for(String s : list1) {
            System.out.println(s);
        }

        for(Diagnose d : diagnoses) {
            ArrayList<Matches> matches = matchesRepo.findAllByDiagnose(d);

            for(Matches m : matches) {
                for(String s : list1) {
                    System.out.println(m.getSymptome().getSymptome());
                    if(s.equalsIgnoreCase(m.getSymptome().getSymptome())) {
                        count ++;
                        System.out.println("count++");
                    }
                }
            }

            if (count == COUNT) {
                model.put("res", d.getDiagnose());
            }
            count = 0;
        }
        return "result";
    }
}

