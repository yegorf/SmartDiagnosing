package com.yegorf.controllers.controllers;


import com.yegorf.controllers.enities.Symptome;
import com.yegorf.controllers.repos.SymptomeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("test")
public class TestController {

    @Autowired
    private SymptomeRepo symptomeRepo;

    @GetMapping
    public String test(Map<String, Object> model) {
        Iterable<Symptome> sym = symptomeRepo.findAll();

        model.put("symptoms", sym);
        return "test";
    }

    @PostMapping
    public String go(@RequestParam String[] list,
                     Map<String, Object> model) {

        for (String s : list) {
            System.out.println(s);
        }
        return "test";
    }
}
