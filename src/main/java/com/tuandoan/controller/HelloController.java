package com.tuandoan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String hello(Model model, @RequestParam(required = false) Integer i1,
                        @RequestParam(required = false) String s1,
                        @RequestParam String levelUrl){
        System.out.println("=>>>>> hello()");
        System.out.println(levelUrl);
        model.addAttribute("i2", 10);
        model.addAttribute("s2", "str");
        model.addAttribute("model", "m");
        model.addAttribute("messi", null);
        model.addAttribute("array",  new String[]{"TUAN", "TRUNG", "VIET"});
        model.addAttribute("arrayEmpty", new String[]{});
        model.addAttribute("arrayList", new ArrayList<>());
        return "hello";
    }
}
