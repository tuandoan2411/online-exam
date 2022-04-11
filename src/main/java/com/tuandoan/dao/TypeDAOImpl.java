package com.tuandoan.dao;

import com.tuandoan.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TypeDAOImpl implements TypeDAO{
    private SessionFactory sessionFactory;

    @Autowired
    public TypeDAOImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addType(int level, String value, Integer previousLevelId) {
        Session session = sessionFactory.getCurrentSession();
        switch(level){
            case 1:
                session.save(new Level1(value));
                return;
            case 2:
                Level1 level1 = session.get(Level1.class, previousLevelId);
                session.save(new Level2(value, level1));
                return;
            case 3:
                Level2 level2 = session.get(Level2.class, previousLevelId);
                session.save(new Level3(value, level2));
                return;
            case 4:
                Level3 level3 = session.get(Level3.class, previousLevelId);
                session.save(new Level4(value, level3));
                return;
            case 5:
                Level4 level4 = session.get(Level4.class, previousLevelId);
                session.save(new Level5(value, level4));
                return;
        }
        throw new RuntimeException("level = " + level + " not valid");
    }

    @Override
    public boolean hasType(int level, Integer previousLevelId, String value) {
        Session session = sessionFactory.getCurrentSession();
        switch(level){
            case 2:
                Level1 level1 = session.get(Level1.class, previousLevelId);
                List<Level2> level2s = level1.getLevel2s();
                for(Level2 level2 : level2s){
                    if(value.equals(level2.getName())){
                        return true;
                    }
                }
                return false;
            case 3:
                Level2 level2 = session.get(Level2.class, previousLevelId);
                List<Level3> level3s = level2.getLevel3s();
                for(Level3 level3 : level3s){
                    if(value.equals(level3.getName())){
                        return true;
                    }
                }
                return false;
            case 4:
                Level3 level3 = session.get(Level3.class, previousLevelId);
                List<Level4> level4s = level3.getLevel4s();
                for(Level4 level4 : level4s){
                    if(value.equals(level4.getName())){
                        return true;
                    }
                }
                return false;
            case 5:
                Level4 level4 = session.get(Level4.class, previousLevelId);
                List<Level5> level5s = level4.getLevel5s();
                for(Level5 level5 : level5s){
                    if(value.equals(level5.getName())){
                        return true;
                    }
                }
                return false;
        }
        throw new RuntimeException("level = " + level + " not valid");
    }

    @Override
    public boolean hasType(int level, String value) {
        if(level == 1){
            Session session = sessionFactory.getCurrentSession();
            Query<Level1> theQuery = session.createQuery("from Level1", Level1.class);
            List<Level1> level1s = theQuery.getResultList();
            for(Level1 level1 : level1s){
                if(value.equals(level1.getName())){
                    return true;
                }
            }
            return false;
        }
        throw new RuntimeException("level = " + level + " not valid");
    }

    @Override
    public void updateType(int level, Integer id, String value) {
        Session session = sessionFactory.getCurrentSession();
        switch(level){
            case 1:
                Level1 level1 = session.get(Level1.class, id);
                level1.setName(value);
                return;
            case 2:
                Level2 level2 = session.get(Level2.class, id);
                level2.setName(value);
                return;
            case 3:
                Level3 level3 = session.get(Level3.class, id);
                level3.setName(value);
                return;
            case 4:
                Level4 level4 = session.get(Level4.class, id);
                level4.setName(value);
                return;
            case 5:
                Level5 level5 = session.get(Level5.class, id);
                level5.setName(value);
                return;
        }
        throw new RuntimeException("level = " + level + " not valid");
    }

    @Override
    public void deleteType(int level, Integer id) {
        Session session = sessionFactory.getCurrentSession();
        switch(level){
            case 1:
                session.delete(session.get(Level1.class, id));
                return;
            case 2:
                session.delete(session.get(Level2.class, id));
                return;
            case 3:
                session.delete(session.get(Level3.class, id));
                return;
            case 4:
                session.delete(session.get(Level4.class, id));
                return;
            case 5:
                session.delete(session.get(Level5.class, id));
                return;
        }
        throw new RuntimeException("level = " + level + " not valid");
    }

    @Override
    public boolean hasReferenced(int level, Integer id) {
        Session session = sessionFactory.getCurrentSession();
        switch(level){
            case 1:
                Level1 level1 = session.get(Level1.class, id);
                return !level1.getLevel2s().isEmpty();
            case 2:
                Level2 level2 = session.get(Level2.class, id);
                return !level2.getLevel3s().isEmpty();
            case 3:
                Level3 level3 = session.get(Level3.class, id);
                return !level3.getLevel4s().isEmpty();
            case 4:
                Level4 level4 = session.get(Level4.class, id);
                return !level4.getLevel5s().isEmpty();
            case 5:
                return false;
        }
        throw new RuntimeException("level = " + level + " not valid");
    }

    @Override
    public List<Level1> getLevel1s() {
        Session session = sessionFactory.getCurrentSession();
        Query<Level1> theQuery = session.createQuery("from Level1", Level1.class);
        List<Level1> level1s = theQuery.getResultList();
        return level1s;
    }

    @Override
    public List<Level2> getLeve2sReferenceLevel1(Integer level1) {
        Session session = sessionFactory.getCurrentSession();
        Level1 l1 = session.get(Level1.class, level1);
        Query<Level2> getLevel2s = session.createQuery(
                                          "select l " +
                                            "from Level2 l " +
                                            "where l.level1 = :l1",
                                            Level2.class)
                .setParameter("l1", l1);
        return getLevel2s.getResultList();
    }

    @Override
    public List<Level3> getLeve3sReferenceLevel2(Integer level2) {
        Session session = sessionFactory.getCurrentSession();
        Level2 l2 = session.get(Level2.class, level2);
        Query<Level3> getLevel3s = session.createQuery(
                        "select l " +
                                "from Level3 l " +
                                "where l.level2 = :l2",
                        Level3.class)
                .setParameter("l2", l2);
        return getLevel3s.getResultList();
    }

    @Override
    public List<Level4> getLeve4sReferenceLevel3(Integer level3) {
        Session session = sessionFactory.getCurrentSession();
        Level3 l3 = session.get(Level3.class, level3);
        Query<Level4> getLevel4s = session.createQuery(
                        "select l " +
                                "from Level4 l " +
                                "where l.level3 = :l3",
                        Level4.class)
                .setParameter("l3", l3);
        return getLevel4s.getResultList();
    }

    @Override
    public List<Level5> getLeve5sReferenceLevel4(Integer level4) {
        Session session = sessionFactory.getCurrentSession();
        Level4 l4 = session.get(Level4.class, level4);
        Query<Level5> getLevel5s = session.createQuery(
                        "select l " +
                                "from Level5 l " +
                                "where l.level4 = :l4",
                        Level5.class)
                .setParameter("l4", l4);
        return getLevel5s.getResultList();
    }
}
