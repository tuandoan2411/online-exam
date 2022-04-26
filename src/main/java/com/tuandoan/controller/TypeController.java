package com.tuandoan.controller;

import com.tuandoan.entity.*;
import com.tuandoan.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/type")
public class TypeController {
    private static final String EXIST_TYPE = "Tên loại này đã tồn tại";
    private static final String HAS_REFERENCED = "Loại này được tham chiếu đến nên không được xoá";
    private static final String ADD_SUCCESSFUL = "Thêm loại thành công";
    private static final String UPDATE_SUCCESSFUL = "Sửa loại thành công";
    private static final String DELETE_SUCCESSFUL = "Xoá loại thành công";
    private TypeService typeService;

    @Autowired
    public TypeController(TypeService typeService){
        this.typeService = typeService;
    }

    @RequestMapping()
    public String type(@RequestParam(required = false) Integer level1,
                       @RequestParam(required = false) Integer level2,
                       @RequestParam(required = false) Integer level3,
                       @RequestParam(required = false) Integer level4,
                       @RequestParam(required = false) String message,
                       Model model){
        List<Level1> level1s = typeService.getLevel1s();
        model.addAttribute("level1s", level1s);
        if(level1 != null){
            model.addAttribute("level1Id", level1);
            List<Level2> level2s = typeService.getLevel2sReferenceLevel1(level1);
            model.addAttribute("level2s", level2s);
            if(level2 != null){
                model.addAttribute("level2Id", level2);
                List<Level3> level3s = typeService.getLevel3sReferenceLevel2(level2);
                model.addAttribute("level3s", level3s);
                if(level3 != null) {
                    model.addAttribute("level3Id", level3);
                    List<Level4> level4s = typeService.getLevel4sReferenceLevel3(level3);
                    model.addAttribute("level4s", level4s);
                    if (level4 != null) {
                        model.addAttribute("level4Id", level4);
                        List<Level5> level5s = typeService.getLevel5sReferenceLevel4(level4);
                        model.addAttribute("level5s", level5s);
                    }
                }
            }
        }
        if(message != null){
            if(message.equals("EXIST_TYPE")){
                model.addAttribute("message", EXIST_TYPE);
            }else if(message.equals("HAS_REFERENCED")){
                model.addAttribute("message", HAS_REFERENCED);
            }else if(message.equals("ADD_SUCCESSFUL")){
                model.addAttribute("message", ADD_SUCCESSFUL);
            }else if(message.equals("UPDATE_SUCCESSFUL")){
                model.addAttribute("message", UPDATE_SUCCESSFUL);
            }else if(message.equals("DELETE_SUCCESSFUL")){
                model.addAttribute("message", DELETE_SUCCESSFUL);
            }
        }
        return "type";
    }

    @RequestMapping("/addUpdateType")
    public String addUpdateType(@RequestParam int currentLevel,
                                @RequestParam String value,
                                @RequestParam(required = false) Integer currentLevelId,
                                @RequestParam(required = false) Integer previousLevelId,
                                @RequestParam String levelUrl){
        levelUrl = levelUrl.replaceAll("and", "&");
        System.out.println("-------------------addUpdateType()");
        System.out.println("currentLevel " + currentLevel );
        System.out.println("value " + value);
        System.out.println("currentLevelId " + currentLevelId);
        System.out.println("previousLevelId " + previousLevelId);
        System.out.println("levelUrl " + levelUrl);

        String and = "&";
        if(levelUrl.equals("")) and = "";
        boolean hasType = false;
        if(currentLevel == 1){
            if(typeService.hasType(currentLevel, value)){
                hasType = true;
            }
        }else {
            if(typeService.hasType(currentLevel, previousLevelId, value)){
                hasType = true;
            }
        }
        if(hasType){
            return "redirect:/type?" + levelUrl + and + "message=EXIST_TYPE";
        }else {
            if(currentLevelId == null){
                typeService.addType(currentLevel, value, previousLevelId);
                return "redirect:/type?" + levelUrl + and + "message=ADD_SUCCESSFUL";
            }else{
                System.out.println("->>>>>> updateType");
                typeService.updateType(currentLevel, currentLevelId, value);
                return "redirect:/type?" + levelUrl + and + "message=UPDATE_SUCCESSFUL";
            }
        }
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam int currentLevel,
                         @RequestParam Integer currentLevelId,
                         @RequestParam String levelUrl){
        levelUrl = levelUrl.replaceAll("and", "&");
        System.out.println("----------------delete()");
        System.out.println("currentLevel " + currentLevel );
        System.out.println("currentLevelId " + currentLevelId);
        System.out.println("levelUrl " + levelUrl);

        String and = "&";
        if(levelUrl.equals("")) and = "";
        if(typeService.hasReferenced(currentLevel, currentLevelId)){
            return "redirect:/type?" + levelUrl + and + "message=HAS_REFERENCED";
        }
        typeService.deleteType(currentLevel, currentLevelId);
        return "redirect:/type?" + levelUrl + and + "message=DELETE_SUCCESSFUL";
    }
}
