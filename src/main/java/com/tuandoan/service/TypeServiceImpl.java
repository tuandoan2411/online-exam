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
    public List<Level2> getLeve2sReferenceLevel1(Integer level1) {
        return typeDAO.getLeve2sReferenceLevel1(level1);
    }

    @Override
    @Transactional
    public List<Level3> getLeve3sReferenceLevel2(Integer level2) {
        return typeDAO.getLeve3sReferenceLevel2(level2);
    }

    @Override
    @Transactional
    public List<Level4> getLeve4sReferenceLevel3(Integer level3) {
        return typeDAO.getLeve4sReferenceLevel3(level3);
    }

    @Override
    @Transactional
    public List<Level5> getLeve5sReferenceLevel4(Integer level4) {
        return typeDAO.getLeve5sReferenceLevel4(level4);
    }
}
