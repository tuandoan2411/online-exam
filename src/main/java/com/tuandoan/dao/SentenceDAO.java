package com.tuandoan.dao;

import com.tuandoan.entity.Sentence;

public interface SentenceDAO {
    void delete(Integer id);

    void saveOrUpdate(Sentence sentence);

    void save(Sentence sentence, int examId);
}
