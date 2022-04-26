package com.tuandoan.controller;

import com.tuandoan.constant.TypeName;
import com.tuandoan.dto.*;
import com.tuandoan.entity.*;
import com.tuandoan.service.ExamService;
import com.tuandoan.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/exam")
@SessionAttributes({"sentenceSessionDTOs","examSessionDTO"})
public class ExamController {
    private TypeService typeService;
    private ExamService examService;

    @Autowired
    public ExamController(TypeService typeService, ExamService examService){
        this.typeService = typeService;
        this.examService = examService;
    }

    @RequestMapping("/showExam")
    public String showExam(@RequestParam(name = "exam-id") Integer examId,
                           Model model){
        model.addAttribute("exam", examService.getExam(examId));
        model.addAttribute("sentences", examService.getSentencesOfExam(examId));

        return "examDetail";
    }

    @RequestMapping("")
    public String exam(Model model){
        List<Exam> exams = examService.getAllExam();
        System.out.println("AFTER examService.getAllExam();");
        List<ExamDTO> examDTOs = new ArrayList<>();
        for(Exam exam : exams){
            ExamDTO examDTO = new ExamDTO();
            examDTO.setId(exam.getId());
            examDTO.setTitle(exam.getTitle());
            examDTO.setDescription(exam.getDescription());
            examDTO.setDate(exam.getDate());
            examDTO.setNumberOfExams(exam.getNumberOfExams());
            examDTO.setTypeInformation(typeService.getTypeInformationForExam(exam.getType()));
            examDTOs.add(examDTO);
        }
        model.addAttribute("exams", examDTOs);
        return "exam";
    }

    @GetMapping("/addExam")
    public String showFormAddExam(@RequestParam(required = false) Integer level1,
                                  @RequestParam(required = false) Integer level2,
                                  @RequestParam(required = false) Integer level3,
                                  @RequestParam(required = false) Integer level4,
                                  @RequestParam(required = false) String typeId,
                                  @RequestParam(required = false) String typeInformation,
                                  @ModelAttribute("examSessionDTO") ExamSessionDTO examSessionDTO,
                                  Model model){
        System.out.println("showFormAddExam()");
        System.out.println("typeId " + typeId);
        System.out.println("typeInfor " + typeInformation);

        List<Level1> l1s = typeService.getLevel1s();
        List<Level1DTO> level1s = new ArrayList<>();
        for (Level1 l1 : l1s) {
            if (typeService.hasReferenced(1, l1.getId())) {
                level1s.add(new Level1DTO(l1.getId(), l1.getName(), true));
            } else {
                level1s.add(new Level1DTO(l1.getId(), l1.getName(), false));
            }
        }
        model.addAttribute("level1s", level1s);
        if(typeId == null) {
            if (level1 != null) {
                model.addAttribute("isShowType", true);
                model.addAttribute("level1Id", level1);
                String level1Information = typeService.getLevel1Name(level1);
                model.addAttribute("level1Information", level1Information);
                List<Level2> l2s = typeService.getLevel2sReferenceLevel1(level1);
                List<Level2DTO> level2s = new ArrayList<>();
                for (Level2 l2 : l2s) {
                    if (typeService.hasReferenced(2, l2.getId())) {
                        level2s.add(new Level2DTO(l2.getId(), l2.getName(), true));
                    } else {
                        level2s.add(new Level2DTO(l2.getId(), l2.getName(), false));
                    }
                }
                model.addAttribute("level2s", level2s);
                if (level2 != null) {
                    model.addAttribute("level2Id", level2);
                    String level2Information = typeService.getLevel2Name(level2);
                    model.addAttribute("level2Information", level2Information);
                    List<Level3> l3s = typeService.getLevel3sReferenceLevel2(level2);
                    List<Level3DTO> level3s = new ArrayList<>();
                    for (Level3 l3 : l3s) {
                        if (typeService.hasReferenced(3, l3.getId())) {
                            level3s.add(new Level3DTO(l3.getId(), l3.getName(), true));
                        } else {
                            level3s.add(new Level3DTO(l3.getId(), l3.getName(), false));
                        }
                    }
                    model.addAttribute("level3s", level3s);
                    if (level3 != null) {
                        model.addAttribute("level3Id", level3);
                        String level3Information = typeService.getLevel3Name(level3);
                        model.addAttribute("level3Information", level3Information);
                        List<Level4> l4s = typeService.getLevel4sReferenceLevel3(level3);
                        List<Level4DTO> level4s = new ArrayList<>();
                        for (Level4 l4 : l4s) {
                            if (typeService.hasReferenced(4, l4.getId())) {
                                level4s.add(new Level4DTO(l4.getId(), l4.getName(), true));
                            } else {
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
        }else {
            examSessionDTO.clean();
            examSessionDTO.setTempType(typeId);
            examSessionDTO.setTypeInformation(typeInformation);
            String[] levels = typeId.split("-");
            if(levels.length == 4){
                Integer level1Id = Integer.parseInt(levels[0]);
                String level1Name = typeService.getLevel1Name(level1Id);
                if(level1Name.equals(TypeName.DE_KIEM_TRA)){
                    Integer level2Id = Integer.parseInt(levels[1]);
                    String level2Name = typeService.getLevel2Name(level2Id);
                    if(level2Name.equals(TypeName.DE_15_PHUT)){
                        setDefaultTitleDescriptionMinutes(examSessionDTO, "Đề kiểm tra 15 phút", 15);
                        /*examSessionDTO.setTitle("Đề kiểm tra 15 phút");
                        examSessionDTO.setHasTitleDefault(true);
                        examSessionDTO.setDescription("");
                        examSessionDTO.setHasDescriptionDefault(true);
                        examSessionDTO.setMinutes(15);
                        examSessionDTO.setHasMinutesDefault(true);*/
                    }else if(level2Name.equals(TypeName.DE_1_TIET)){
                        setDefaultTitleDescriptionMinutes(examSessionDTO, "Đề kiểm tra 1 tiết", 45);
                        /*examSessionDTO.setTitle("Đề kiểm tra 1 tiết");
                        examSessionDTO.setHasTitleDefault(true);
                        examSessionDTO.setDescription("");
                        examSessionDTO.setHasDescriptionDefault(true);
                        examSessionDTO.setMinutes(45);
                        examSessionDTO.setHasMinutesDefault(true);*/
                    }
                    if(level2Name.equals(TypeName.DE_15_PHUT) || level2Name.equals(TypeName.DE_1_TIET)
                        || level2Name.equals(TypeName.DE_HOC_KY)){
                        Integer level4Id = Integer.parseInt(levels[3]);
                        examSessionDTO.setLevel5s(typeService.getLevel5sReferenceLevel4(level4Id));
                    }
                }
            }
        }
        return "addExam";
    }

    @PostMapping(value = "/addExam")
    public String addExam(@ModelAttribute("sentenceSessionDTOs") SentenceDTOWrapper sentenceDTOWrapper,
                          @ModelAttribute("examSessionDTO") ExamSessionDTO examSessionDTO,
                          @RequestParam String action,
                          Model model){
        List<Level1> l1s = typeService.getLevel1s();
        List<Level1DTO> level1s = new ArrayList<>();
        for (Level1 l1 : l1s) {
            if (typeService.hasReferenced(1, l1.getId())) {
                level1s.add(new Level1DTO(l1.getId(), l1.getName(), true));
            } else {
                level1s.add(new Level1DTO(l1.getId(), l1.getName(), false));
            }
        }
        model.addAttribute("level1s", level1s);

        if(sentenceDTOWrapper != null){
            System.out.println("sentenceDTOWrapper != null");
        }
        if(action.equals("ADD_NEW_QUESTION")){
            System.out.println("ADD_NEW_QUESTION");
            for(SentenceSessionDTO sentenceSessionDTO : sentenceDTOWrapper.getSentenceSessionDTOList()){
                System.out.println(sentenceSessionDTO);
            }
            sentenceDTOWrapper.getSentenceSessionDTOList().add(new SentenceSessionDTO());
        }else {
            System.out.println("SAVE_EXAM");
            Exam exam = new Exam();
            exam.setTitle(examSessionDTO.getTitle());
            exam.setDescription(examSessionDTO.getDescription());
            exam.setMinutes(examSessionDTO.getMinutes());
            exam.setDate(LocalDate.now());
            exam.setNumberOfSentences(sentenceDTOWrapper.getSentenceSessionDTOList().size());
            exam.setType(examSessionDTO.getTempType());
            System.out.println("EXAMMMMMMMMMMMMMMMMMMMMMMMMMM");
            System.out.println(exam);
            for(SentenceSessionDTO sentenceSessionDTO : sentenceDTOWrapper.getSentenceSessionDTOList()){
                Sentence sentence = new Sentence();
                sentence.setQuestion(sentenceSessionDTO.getQuestion());
                sentence.setA(sentenceSessionDTO.getA());
                sentence.setB(sentenceSessionDTO.getB());
                sentence.setC(sentenceSessionDTO.getC());
                sentence.setD(sentenceSessionDTO.getD());
                sentence.setAnswer(sentenceSessionDTO.getAnswer());
                sentence.setSolution(sentenceSessionDTO.getSolution());
                if (examSessionDTO.getLevel5s() != null && !examSessionDTO.getLevel5s().isEmpty()){
                    sentence.setType(examSessionDTO.getTempType() + "-" + sentenceSessionDTO.getType());
                }else {
                    sentence.setType(examSessionDTO.getTempType());
                }
                System.out.println(sentence);
                exam.addSentence(sentence);
            }
            examService.addExam(exam);
            sentenceDTOWrapper.getSentenceSessionDTOList().clear();
            examSessionDTO.clean();
            return "redirect:/exam";
        }

        return "addExam";
    }

    @RequestMapping("/deleteSentenceInSession")
    public String deleteSentenceInSession(@RequestParam int sentence,
                                          @ModelAttribute("sentenceSessionDTOs") SentenceDTOWrapper sentenceDTOWrapper){
        sentenceDTOWrapper.getSentenceSessionDTOList().remove(sentence);
        System.out.println("Removed " + sentence);
        return "redirect:addExam";
    }

    @RequestMapping("/showExamForUpdate")
    public String showExamForUpdate(@RequestParam(name = "exam-id") Integer examId,
                                    Model model){
        model.addAttribute("exam", examService.getExam(examId));
        return "updateExam";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam(name = "exam-id") Integer examId){
        if(examService.allowDelete(examId)){
            examService.delete(examId);
        }
        return "redirect:/exam";
    }

    @RequestMapping("s")
    public void s(){
        System.out.println("s()");
    }

    @ModelAttribute("examSessionDTO")
    public ExamSessionDTO examSessionDTO(){
        System.out.println("examSessionDTO()");
        return new ExamSessionDTO();
    }

    @ModelAttribute("sentenceSessionDTOs")
    public SentenceDTOWrapper sentenceDTOWrapper(){
        System.out.println("*****");
        System.out.println("sentenceDTOWrapper()");
        return new SentenceDTOWrapper();
    }

    private void setDefaultTitleDescriptionMinutes(ExamSessionDTO examSessionDTO, String title, int minutes){
        examSessionDTO.setTitle(title);
        examSessionDTO.setHasTitleDefault(true);
        examSessionDTO.setDescription("");
        examSessionDTO.setHasDescriptionDefault(true);
        examSessionDTO.setMinutes(minutes);
        examSessionDTO.setHasMinutesDefault(true);
    }

}
