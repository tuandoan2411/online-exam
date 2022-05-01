package com.tuandoan.dao;

import com.tuandoan.entity.Sentence;

public interface SentenceDAO {
    void delete(Integer id);

    void saveOrUpdate(Sentence sentence, int examId);
}
