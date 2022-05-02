package com.tuandoan.controller;

import com.tuandoan.constant.TypeName;
import com.tuandoan.convert.ConvertExamDTOToExam;
import com.tuandoan.dto.*;
import com.tuandoan.entity.*;
import com.tuandoan.service.ExamService;
import com.tuandoan.service.SentenceService;
import com.tuandoan.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/exam")
@SessionAttributes({"sentenceSessionDTOs","examSessionDTO"})
public class ExamController {
    private TypeService typeService;
    private ExamService examService;
    private SentenceService sentenceService;

    @Autowired
    public ExamController(TypeService typeService, ExamService examService, SentenceService sentenceService){
        this.typeService = typeService;
        this.examService = examService;
        this.sentenceService = sentenceService;
    }

    @RequestMapping("")
    public String exam(Model model){
        List<ExamDTO> examDTOs = examService.getAllExam();
        model.addAttribute("exams", examDTOs);
        return "exam/exam";
    }

    @RequestMapping("/showExamDetail")
    public String showExamDetail(@RequestParam(name = "exam-id") Integer examId,
                           Model model){
        ExamDTO examDTO = examService.getExam(examId);
        model.addAttribute("exam", examDTO);
        model.addAttribute("sentences", examDTO.getSentenceDTOs());
        return "exam/examDetail";
    }

    @GetMapping("/addExam")
    public String showFormForAdd(@RequestParam(required = false) Integer level1,
                                 @RequestParam(required = false) Integer level2,
                                 @RequestParam(required = false) Integer level3,
                                 @RequestParam(required = false) Integer level4,
                                 @RequestParam(required = false) String typeId,
                                 @RequestParam(required = false) String typeInformation,
                                 @ModelAttribute("examSessionDTO") ExamSessionDTO examSessionDTO,
                                 @ModelAttribute("sentenceSessionDTOs") SentenceDTOWrapper sentenceDTOWrapper,
                                 Model model){
        examSessionDTO.clean();
        if(sentenceDTOWrapper.getSentenceSessionDTOList().size() > 0){
            sentenceDTOWrapper.getSentenceSessionDTOList().clear();
        }
        List<Level1DTO> level1s = getAllLevel1(typeService);
        model.addAttribute("level1s", level1s);
        if(typeId == null) {
            // Show next levels
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
            examSessionDTO.getExamDTO().setType(typeId);
            examSessionDTO.getExamDTO().setTypeInformation(typeInformation);
            setInformationForAddOrUpdateExam(false, typeId, null, examSessionDTO, sentenceDTOWrapper);
        }
        return "exam/addUpdateExam";
    }

    static List<Level1DTO> getAllLevel1(TypeService typeService) {
        List<Level1> l1s = typeService.getLevel1s();
        List<Level1DTO> level1s = new ArrayList<>();
        for (Level1 l1 : l1s) {
            if (typeService.hasReferenced(1, l1.getId())) {
                level1s.add(new Level1DTO(l1.getId(), l1.getName(), true));
            } else {
                level1s.add(new Level1DTO(l1.getId(), l1.getName(), false));
            }
        }
        return level1s;
    }

    @RequestMapping("/showExamForUpdate")
    public String showExamForUpdate(@RequestParam(name = "exam-id") Integer examId,
                                    @ModelAttribute("examSessionDTO") ExamSessionDTO examSessionDTO,
                                    @ModelAttribute("sentenceSessionDTOs") SentenceDTOWrapper sentenceDTOWrapper,
                                    Model model){
        examSessionDTO.clean();
        ExamDTO examDTO = examService.getExam(examId);
        examSessionDTO.setExamDTO(examDTO);

        sentenceDTOWrapper.getSentenceSessionDTOList().clear();
        List<SentenceDTO> sentenceDTOs = examService.getSentencesOfExam(examId);
        for(SentenceDTO sentenceDTO : sentenceDTOs){
            SentenceSessionDTO sentenceSessionDTO = new SentenceSessionDTO();
            sentenceSessionDTO.setSentenceDTO(sentenceDTO);
            sentenceDTOWrapper.getSentenceSessionDTOList().add(sentenceSessionDTO);
        }
        String typeId = examDTO.getType();
        setInformationForAddOrUpdateExam(true, typeId, sentenceDTOs, examSessionDTO, sentenceDTOWrapper);
        model.addAttribute("isUpdate", true);
        return "exam/addUpdateExam";
    }

    @PostMapping(value = "/addOrUpdateExam")
    public String performExamForm(@RequestParam String action,
                          @RequestParam(name = "is-update", required = false) Boolean isUpdate,
                          @ModelAttribute("examSessionDTO") ExamSessionDTO examSessionDTO,
                          @ModelAttribute("sentenceSessionDTOs") SentenceDTOWrapper sentenceDTOWrapper,
                          Model model){
        List<Level1DTO> level1s = getAllLevel1(typeService);
        model.addAttribute("level1s", level1s);
        if(action.equals("ADD_NEW_QUESTION")){
            sentenceDTOWrapper.getSentenceSessionDTOList().add(new SentenceSessionDTO());
            if(isUpdate){
                model.addAttribute("isUpdate", true);
            }
        }else {
            if(isUpdate){
                addOrUpdateExam(true, examSessionDTO, sentenceDTOWrapper);
            }else{
                addOrUpdateExam(false, examSessionDTO, sentenceDTOWrapper);
            }
            sentenceDTOWrapper.getSentenceSessionDTOList().clear();
            examSessionDTO.clean();
            return "redirect:/exam";
        }
        return "exam/addUpdateExam";
    }

    private void addOrUpdateExam(boolean isUpdate,
                                 @ModelAttribute("examSessionDTO") ExamSessionDTO examSessionDTO,
                                 @ModelAttribute("sentenceSessionDTOs") SentenceDTOWrapper sentenceDTOWrapper) {
        ExamDTO examDTO = examSessionDTO.getExamDTO();
        examDTO.setNumberOfSentences(sentenceDTOWrapper.getSentenceSessionDTOList().size());
        if(isUpdate){
            examDTO.setSentenceDTOs(null);
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
                if(sentenceDTO.getId() == null){
                    sentenceDTO.setId(0);
                }
                sentenceService.saveOrUpdate(sentenceDTO, examDTO.getId());
            }else {
                examDTO.addSentenceDTO(sentenceDTO);
            }
        }
        if(!isUpdate){
            examDTO.setDate(LocalDate.now());
            System.out.println(examDTO);
            examService.addExam(examDTO);
        }
    }

    private void setInformationForAddOrUpdateExam(boolean isUpdate,
                   String typeId,
                   List<SentenceDTO> sentenceDTOs,
                   @ModelAttribute("examSessionDTO") ExamSessionDTO examSessionDTO,
                   @ModelAttribute("sentenceSessionDTOs") SentenceDTOWrapper sentenceDTOWrapper){
        String[] levels = typeId.split("-");
        if(levels.length == 4){
            Integer level1Id = Integer.parseInt(levels[0]);
            String level1Name = typeService.getLevel1Name(level1Id);
            if(level1Name.equals(TypeName.DE_KIEM_TRA)){
                Integer level2Id = Integer.parseInt(levels[1]);
                String level2Name = typeService.getLevel2Name(level2Id);
                if(level2Name.equals(TypeName.DE_15_PHUT) || level2Name.equals(TypeName.DE_1_TIET)){
                    examSessionDTO.setHasDefaultInformation(true);
                    if(!isUpdate){
                        if(level2Name.equals(TypeName.DE_15_PHUT)){
                            setDefaultTitleDescriptionMinutes(examSessionDTO.getExamDTO(), "Đề kiểm tra 15 phút", 15);
                        }else {
                            setDefaultTitleDescriptionMinutes(examSessionDTO.getExamDTO(), "Đề kiểm tra 1 tiết", 45);
                        }
                    }
                }
                if(level2Name.equals(TypeName.DE_15_PHUT) || level2Name.equals(TypeName.DE_1_TIET)
                        || level2Name.equals(TypeName.DE_HOC_KY)){
                    Integer level4Id = Integer.parseInt(levels[3]);
                    examSessionDTO.setLevel5s(typeService.getLevel5sReferenceLevel4(level4Id));
                    if(isUpdate){
                        for(int i = 0; i < sentenceDTOs.size(); i++){
                            SentenceDTO sentenceDTO = sentenceDTOs.get(i);
                            String[] levelsOfSentence = sentenceDTO.getType().split("-");
                            sentenceDTOWrapper.getSentenceSessionDTOList().get(i).setTypeOfEndLevel(levelsOfSentence[levelsOfSentence.length - 1]);
                        }
                    }
                }
            }
        }

    }

    private void setDefaultTitleDescriptionMinutes(ExamDTO examDTO, String title, int minutes){
        examDTO.setTitle(title);
        examDTO.setDescription("");
        examDTO.setMinutes(minutes);
    }

    @RequestMapping("/deleteSentenceInSession")
    public String deleteSentenceInSession(@RequestParam int sentence,
                                          @RequestParam(name = "is-update") Boolean isUpdate,
                                          @ModelAttribute("sentenceSessionDTOs") SentenceDTOWrapper sentenceDTOWrapper,
                                          Model model){
        Integer sentenceId = sentenceDTOWrapper.getSentenceSessionDTOList().get(sentence).getSentenceDTO().getId();
        sentenceDTOWrapper.getSentenceSessionDTOList().remove(sentence);
        if(isUpdate){
            sentenceService.delete(sentenceId);
            model.addAttribute("isUpdate", true);
        }
        return "exam/addUpdateExam";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam(name = "exam-id") Integer examId){
        if(examService.allowDelete(examId)){
            examService.delete(examId);
        }
        return "redirect:/exam";
    }

    @ModelAttribute("examSessionDTO")
    public ExamSessionDTO examSessionDTO(){
        return new ExamSessionDTO();
    }

    @ModelAttribute("sentenceSessionDTOs")
    public SentenceDTOWrapper sentenceDTOWrapper(){
        return new SentenceDTOWrapper();
    }

}
