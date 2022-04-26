package com.tuandoan.service;

import com.tuandoan.entity.*;

import java.util.List;

public interface TypeService {
    void addType(int level, String value, Integer previousLevelId);

    boolean hasType(int level, Integer previousLevelId, String value);

    boolean hasType(int level, String value);

    void updateType(int level, Integer id, String value);

    void deleteType(int level, Integer id);

    boolean hasReferenced(int level, Integer id);

    List<Level1> getLevel1s();

    List<Level2> getLevel2sReferenceLevel1(Integer level1);

    List<Level3> getLevel3sReferenceLevel2(Integer level2);

    List<Level4> getLevel4sReferenceLevel3(Integer level3);

    List<Level5> getLevel5sReferenceLevel4(Integer level4);

    String getLevel1Name(Integer id);

    String getLevel2Name(Integer id);

    String getLevel3Name(Integer id);

    String getLevel4Name(Integer id);

    String getTypeInformationForExam(String type);
}
