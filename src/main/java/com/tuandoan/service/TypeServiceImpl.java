package com.tuandoan.service;

import com.tuandoan.dao.TypeDAO;
import com.tuandoan.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService{
    private TypeDAO typeDAO;

    @Autowired
    public TypeServiceImpl(TypeDAO typeDAO){
        this.typeDAO = typeDAO;
    }

    @Override
    @Transactional
    public void addType(int level, String value, Integer previousLevelId) {
        typeDAO.addType(level, value, previousLevelId);
    }

    @Override
    @Transactional
    public boolean hasType(int level, Integer previousLevelId, String value) {
        return typeDAO.hasType(level, previousLevelId, value);
    }

    @Override
    @Transactional
    public boolean hasType(int level, String value) {
        return typeDAO.hasType(level, value);
    }

    @Override
    @Transactional
    public void updateType(int level, Integer id, String value) {
        typeDAO.updateType(level, id, value);
    }

    @Override
    @Transactional
    public void deleteType(int level, Integer id) {
        typeDAO.deleteType(level, id);
    }

    @Override
    @Transactional
    public boolean hasReferenced(int level, Integer id) {
        return typeDAO.hasReferenced(level, id);
    }

    @Override
    @Transactional
    public List<Level1> getLevel1s() {
        return typeDAO.getLevel1s();
    }

    @Override
    @Transactional
    public List<Level2> getLevel2sReferenceLevel1(Integer level1) {
        return typeDAO.getLevel2sReferenceLevel1(level1);
    }

    @Override
    @Transactional
    public List<Level3> getLevel3sReferenceLevel2(Integer level2) {
        return typeDAO.getLevel3sReferenceLevel2(level2);
    }

    @Override
    @Transactional
    public List<Level4> getLevel4sReferenceLevel3(Integer level3) {
        return typeDAO.getLevel4sReferenceLevel3(level3);
    }

    @Override
    @Transactional
    public String getTypeInformationForExam(String type) {
        System.out.println("type = " + type);
        if(type == null) return null;
        String typeInformation = "";
        String[] splitType = type.split("-");
        for(int i = 0; i < splitType.length; i++){
            if(i < splitType.length - 1){
                typeInformation += getLevelName(i + 1, Integer.parseInt(splitType[i])) + " - ";
            }else {
                typeInformation += getLevelName(i + 1, Integer.parseInt(splitType[i]));
            }
        }
        System.out.println("typeInformation " + typeInformation);
        System.out.println("END FUNCTION getTypeInformationForExam(String type)");
        return typeInformation;
    }

    @Override
    @Transactional
    public List<Level5> getLevel5sReferenceLevel4(Integer level4) {
        return typeDAO.getLevel5sReferenceLevel4(level4);
    }

    @Override
    @Transactional
    public String getLevel1Name(Integer id) {
        return typeDAO.getLevel1Name(id);
    }

    @Override
    @Transactional
    public String getLevel2Name(Integer id) {
        return typeDAO.getLevel2Name(id);
    }

    @Override
    @Transactional
    public String getLevel3Name(Integer id) {
        return typeDAO.getLevel3Name(id);
    }

    @Override
    @Transactional
    public String getLevel4Name(Integer id) {
        return typeDAO.getLevel4Name(id);
    }

    private String getLevelName(int level, int id) {
        System.out.println("getLevelName(int level, int id)");
        switch(level){
            case 1:
                return getLevel1Name(id);
            case 2:
                return getLevel2Name(id);
            case 3:
                return getLevel3Name(id);
            case 4:
                return getLevel4Name(id);
        }
        return null;
    }


}
