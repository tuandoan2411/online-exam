package com.tuandoan.controller;

import com.tuandoan.dto.Level1DTO;
import com.tuandoan.dto.Level2DTO;
import com.tuandoan.dto.Level3DTO;
import com.tuandoan.dto.Level4DTO;
import com.tuandoan.entity.*;
import com.tuandoan.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {
    private TypeService typeService;

    @Autowired
    public HelloController(TypeService typeService){
        this.typeService = typeService;
    }

    @RequestMapping("/hello")
    public String hello(Model model, @RequestParam(required = false) Integer i1,
                        @RequestParam(required = false) String s1,
                        @RequestParam String levelUrl){
        System.out.println("=>>>>> hello()  tuân đoàn");
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
