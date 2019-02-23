package com.yegorf.controllers.different;

import com.yegorf.controllers.enities.Diagnose;
import com.yegorf.controllers.enities.Matches;
import com.yegorf.controllers.repos.DiagnoseRepo;
import com.yegorf.controllers.repos.MatchesRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class Tester {

    @Autowired
    private DiagnoseRepo diagnoseRepo;
    @Autowired
    private MatchesRepo matchesRepo;

    public Diagnose test(String[] list) {
        Diagnose diagnose = new Diagnose("Мы не знаем чем вы больны");

        ArrayList<Diagnose> diagnoses = (ArrayList<Diagnose>) diagnoseRepo.findAll();

        for(Diagnose d : diagnoses) {
            ArrayList<Matches> matches = matchesRepo.findAllByDiagnose(d);
            System.out.println(matches);
            System.out.println();
        }

        return  diagnose;
    }
}


//    int COUNT = list.length;
//        int count = 0;
//        Diagnose diagnose = null;
//
//        ArrayList<Diagnose> diagnoses = (ArrayList<Diagnose>) diagnoseRepo.findAll();
//
//        for(Diagnose d : diagnoses) {
//            ArrayList<Matches> matches = matchesRepo.findAllByDiagnose(d);
//
//            for(Matches m : matches) {
//                System.out.println(m.getDiagnose().getDiagnose() + " " + m.getSymptome().getSymptome());
//                System.out.println();
//                for(String s : list) {
//                    if(s.equals(m.getSymptome().getSymptome())) {
//                        count++;
//                    }
//                }
//            }
//        }