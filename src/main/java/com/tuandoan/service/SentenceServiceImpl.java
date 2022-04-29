package com.tuandoan.service;

import com.tuandoan.dao.SentenceDAO;
import com.tuandoan.entity.Sentence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SentenceServiceImpl implements SentenceService{
    private SentenceDAO sentenceDAO;

    @Autowired
    public SentenceServiceImpl(SentenceDAO sentenceDAO){
        this.sentenceDAO = sentenceDAO;
    }
    
    @Override
    @Transactional
    public void delete(Integer id) {
        sentenceDAO.delete(id);
    }

    @Override
    @Transactional
    public void saveOrUpdate(Sentence sentence) {
        sentenceDAO.saveOrUpdate(sentence);
    }

    @Override
    @Transactional
    public void save(Sentence sentence, int examId) {
        sentenceDAO.save(sentence, examId);
    }
}
