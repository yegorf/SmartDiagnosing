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


        System.out.println("kek");
    }

    @GetMapping
    public String enter(Map<String, Object> model) {
        showList(model);
        return "enter";
    }

    private boolean checkIn(ArrayList<String> list) {
        ArrayList<Diagnose> diagnoses = (ArrayList<Diagnose>) diagnoseRepo.findAll();

        for(Diagnose d : diagnoses) {
            ArrayList<Matches> matches = matchesRepo.findAllByDiagnose(d);
            int count = 0;

            for(String s : list) {
                for(Matches m : matches) {
                    if(s.equals(m.getSymptome().getSymptome())) {
                        count++;
                    }
                }
            }

            if(list.size() == count && list.size() < matches.size()) {
                System.out.println("Содержится");
                return false;
            }
            if(list.size() == count && list.size() == matches.size()) {
                System.out.println("Существует");
                return false;
            }
            if(matches.size() == count && list.size() > matches.size() && matches!=null) {
                System.out.println("Содержит");
                return false;
            }
        }
        return true;
    }

    @PostMapping(params = {"text", "list"})
    public String addA(@RequestParam String text,
                       @RequestParam String[] list,
                       Map<String, Object> model) {


        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<Symptome> symptomes = (ArrayList<Symptome>) symptomeRepo.findAll();
        for(Symptome s : symptomes) {
            for(String ss : list) {
                if(s.getId()== Integer.parseInt(ss)) {
                    list1.add(s.getSymptome());
                }
            }
        }

        boolean check = checkIn(list1);
        if(!check) {
            model.put("error", "Конфликт симптомов");
            return "error";
        }
        if(check) {
            Diagnose diagnose = new Diagnose(text);
            diagnoseRepo.save(diagnose);
            showList(model);

            Matches matches;
            ArrayList<Symptome> sy = (ArrayList<Symptome>) symptomeRepo.findAll();

            for (String s : list1) {
                for (Symptome symptome : sy) {
                    if (symptome.getSymptome().equals(s)) {
                        matches = new Matches(diagnose, symptome);
                        matchesRepo.save(matches);
                    }
                }
            }
            return "enter";
        } else {
            return "error";
        }
    }

    @PostMapping(params = "sym")
    public String addB(@RequestParam String sym, Map<String, Object> model) {
        Symptome symptome = new Symptome(sym);
        symptomeRepo.save(symptome);

        showList(model);
        return "enter";
    }

    @PostMapping(params = "dia")
    public String deleteDiagnose(@RequestParam String dia, Map<String, Object> model) {

        ArrayList<Diagnose> list = (ArrayList<Diagnose>) diagnoseRepo.findAll();
        for(Diagnose d : list) {
            if(dia.equals(d.getDiagnose())) {
                diagnoseRepo.delete(d);
            }
        }
        showList(model);
        return "enter";
    }

    @PostMapping(params = "sia")
    public String deleteSymptom(@RequestParam String sia, Map<String, Object> model) {
        Symptome symptome = null;
        ArrayList<Symptome> list = (ArrayList<Symptome>) symptomeRepo.findAll();
        for(Symptome s : list) {
            if(s.getSymptome().equals(sia)) {
                symptome = s;
            }
        }

        ArrayList<Matches> matches = matchesRepo.findAllBySymptome(symptome);
        for(Matches m : matches) {
            diagnoseRepo.delete(m.getDiagnose());
        }
        symptomeRepo.delete(symptome);

        showList(model);
        return "enter";
    }
}
