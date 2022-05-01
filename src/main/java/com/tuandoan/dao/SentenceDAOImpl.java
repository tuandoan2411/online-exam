package com.tuandoan.dao;

import com.tuandoan.entity.Exam;
import com.tuandoan.entity.Sentence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SentenceDAOImpl implements SentenceDAO{
    private SessionFactory sessionFactory;

    @Autowired
    public SentenceDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Sentence sentence = session.get(Sentence.class, id);
        if(id == 0) System.out.println("id == 0");
        if(sentence != null){
            System.out.println("SentenceDAOImpl delete " + id);
            session.delete(sentence);
        }
    }

    @Override
    public void saveOrUpdate(Sentence sentence, int examId) {
        Session session = sessionFactory.getCurrentSession();
        Exam exam = session.get(Exam.class, examId);
        sentence.setExam(exam);
        if(sentence.getId() != 0){
            session.update(sentence);
            return;
        }
        session.save(sentence);
    }
}
