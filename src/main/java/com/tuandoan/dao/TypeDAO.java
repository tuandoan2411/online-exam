package com.tuandoan.dao;

import com.tuandoan.entity.*;

import java.util.List;

public interface TypeDAO {
    void addType(int level, String value, Integer previousLevelId);

    boolean hasType(int level, Integer previousLevelId, String value);

    boolean hasType(int level, String value);

    void updateType(int level, Integer id, String value);

    void deleteType(int level, Integer id);

    boolean hasReferenced(int level, Integer id);

    List<Level1> getLevel1s();

    List<Level2> getLeve2sReferenceLevel1(Integer level1);

    List<Level3> getLeve3sReferenceLevel2(Integer level2);

    List<Level4> getLeve4sReferenceLevel3(Integer level3);

    List<Level5> getLeve5sReferenceLevel4(Integer level4);
}
