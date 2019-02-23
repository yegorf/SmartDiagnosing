package com.yegorf.controllers.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequestMapping("index")
public class MainController {
    @GetMapping
    public String index(Map<String, Object> model) {
        //LocalDateTime time = LocalDateTime.now();
        //model.put("time", time.getDayOfMonth() + "." + time.getMonth() + "." + time.getYear());
        return "index";
    }
}
