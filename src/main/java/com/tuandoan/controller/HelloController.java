package com.tuandoan.controller;

import com.tuandoan.constant.TypeName;
import com.tuandoan.convert.ConvertSentenceDTOToSentence;
import com.tuandoan.dto.*;
import com.tuandoan.entity.*;
import com.tuandoan.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
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

    /*public void addOrUpdateExam(boolean isUpdate,
                  @ModelAttribute("sentenceSessionDTOs") SentenceDTOWrapper sentenceDTOWrapper,
                  @ModelAttribute("examSessionDTO") ExamSessionDTO examSessionDTO){
        ExamDTO examDTO = examSessionDTO.getExamDTO();
        examDTO.setNumberOfSentences(sentenceDTOWrapper.getSentenceSessionDTOList().size());
        if(isUpdate){
            examService.updateExam(examDTO);
        }
        for(SentenceSessionDTO sentenceSessionDTO : sentenceDTOWrapper.getSentenceSessionDTOList()) {
            SentenceDTO sentenceDTO = sentenceSessionDTO.getSentenceDTO();
            if (examSessionDTO.getLevel5s() != null && !examSessionDTO.getLevel5s().isEmpty()){
                sentenceDTO.setType(examSessionDTO.getExamDTO().getType() + "-" + sentenceSessionDTO.getTypeOfEndLevel());
            }else {
                sentenceDTO.setType(examSessionDTO.getExamDTO().getType());
            }
            if(isUpdate){
                sentenceService.save(sentenceDTO, examDTO.getId());
            }else {
                examDTO.addSentenceDTO(sentenceDTO);
            }
        }
        if(!isUpdate){
            examDTO.setDate(LocalDate.now());
            examService.addExam(examDTO);
        }
    }*/
}
