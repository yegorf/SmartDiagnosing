package com.yegorf.controllers.controllers;


import com.yegorf.controllers.different.DiagnoseParams;
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
import java.util.Arrays;
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

    public void calculateKoef(DiagnoseParams dp, Matches matches) {

    }

    public ArrayList<DiagnoseParams> testing(ArrayList<Integer> ids) {
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Symptome> symptomes = (ArrayList<Symptome>) symptomeRepo.findAll();

        for(Integer id : ids) {
            for(Symptome s : symptomes) {
                if(id == s.getId()) {
                    list.add(s.getSymptome());
                }
            }
        }

        ArrayList<Diagnose> diagnoses = (ArrayList<Diagnose>) diagnoseRepo.findAll();
        ArrayList<DiagnoseParams> diagnoseParams = new ArrayList<>();

        for(Diagnose d : diagnoses) {
            ArrayList<Matches> matches = matchesRepo.findAllByDiagnose(d);
            double dov = 0.0;
            double ned = 0.0;

            for(Matches m : matches) {
                for(String s : list) {
                    if(s.equals(m.getSymptome().getSymptome())) {
                        //dp.setDov(dp.getDov() + m.getDov());
                        //dp.setNed(dp.getNed() + m.getNed());
                        dov += (m.getDov() * (1 - dov));
                        ned += (m.getNed() * (1 - ned));
                    }
                }
            }

            DiagnoseParams dp = new DiagnoseParams(d.getDiagnose(), (dov - ned), dov, ned);
            diagnoseParams.add(dp);
        }

        for(DiagnoseParams dp : diagnoseParams) {
            System.out.println("Диагноз: " + dp.getDiagnose() + " Коэффициент уверенности:" + dp.getKoef());
        }

        return diagnoseParams;
    }

    @PostMapping
    public String go(@RequestParam Integer[] list,
                     Map<String, Object> model) throws IOException {

        ArrayList<DiagnoseParams> diagnoseParams = testing(new ArrayList<>(Arrays.asList(list)));
        model.put("dp", diagnoseParams);

        return "result";
    }
}

