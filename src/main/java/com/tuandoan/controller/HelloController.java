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

    @GetMapping("/temp")
    public String temp(@RequestParam(required = false) Integer level1,
                                  @RequestParam(required = false) Integer level2,
                                  @RequestParam(required = false) Integer level3,
                                  @RequestParam(required = false) Integer level4,
                                  @RequestParam(required = false) String typeId,
                                  @RequestParam(required = false) String typeInformation,
                                  Model model){
        if("Tuân Đoàn vô địch".startsWith("Tuân Đoàn")) System.out.println("TUÂN ĐOÀN OK");
        model.addAttribute("typeInformation", typeInformation);
        System.out.println("temp()");
        System.out.println("typeId = " + typeId);
        System.out.println("typeInformation = " + typeInformation);
        if(typeInformation != null && typeInformation.startsWith("Thi THPT")){
            System.out.println("Thi THPT");
        }else {
            System.out.println("No thi thpt");
        }
        if(typeInformation != null && typeInformation.startsWith("Đề%20kiểm%20tra")){
            System.out.println("Đề kiểm tra hihi");
        }else{
            System.out.println("no no");
        }


        List<Level1> l1s = typeService.getLevel1s();
        List<Level1DTO> level1s = new ArrayList<>();
        for(Level1 l1 : l1s){
            if(typeService.hasReferenced(1, l1.getId())){
                level1s.add(new Level1DTO(l1.getId(), l1.getName(), true));
            }else {
                level1s.add(new Level1DTO(l1.getId(), l1.getName(), false));
            }
        }
        model.addAttribute("level1s", level1s);
        if(level1 != null){
            model.addAttribute("isShowType", true);
            model.addAttribute("level1Id", level1);
            String level1Information = typeService.getLevel1Name(level1);
            model.addAttribute("level1Information", level1Information);
            List<Level2> l2s = typeService.getLevel2sReferenceLevel1(level1);
            List<Level2DTO> level2s = new ArrayList<>();
            for(Level2 l2 : l2s){
                if(typeService.hasReferenced(2, l2.getId())){
                    level2s.add(new Level2DTO(l2.getId(), l2.getName(), true));
                }else {
                    level2s.add(new Level2DTO(l2.getId(), l2.getName(), false));
                }
            }
            model.addAttribute("level2s", level2s);
            if(level2 != null){
                model.addAttribute("level2Id", level2);
                String level2Information = typeService.getLevel2Name(level2);
                model.addAttribute("level2Information", level2Information);
                List<Level3> l3s = typeService.getLevel3sReferenceLevel2(level2);
                List<Level3DTO> level3s = new ArrayList<>();
                for(Level3 l3 : l3s){
                    if(typeService.hasReferenced(3, l3.getId())){
                        level3s.add(new Level3DTO(l3.getId(), l3.getName(), true));
                    }else {
                        level3s.add(new Level3DTO(l3.getId(), l3.getName(), false));
                    }
                }
                model.addAttribute("level3s", level3s);
                if(level3 != null) {
                    model.addAttribute("level3Id", level3);
                    String level3Information = typeService.getLevel3Name(level3);
                    model.addAttribute("level3Information", level3Information);
                    List<Level4> l4s = typeService.getLevel4sReferenceLevel3(level3);
                    List<Level4DTO> level4s = new ArrayList<>();
                    for(Level4 l4 : l4s){
                        if(typeService.hasReferenced(4, l4.getId())){
                            level4s.add(new Level4DTO(l4.getId(), l4.getName(), true));
                        }else {
                            level4s.add(new Level4DTO(l4.getId(), l4.getName(), false));
                        }
                    }
                    model.addAttribute("level4s", level4s);
                    if (level4 != null) {
                        model.addAttribute("level4Id", level4);
                        String level4Information = typeService.getLevel4Name(level4);
                        model.addAttribute("level4Information", level4Information);
                        List<Level5> level5s = typeService.getLevel5sReferenceLevel4(level4);
                        model.addAttribute("level5s", level5s);
                    }
                }
            }
        }
        return "hello";
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
