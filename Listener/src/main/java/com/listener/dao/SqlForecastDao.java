package com.listener.dao;

import com.entities.ForecastEntity;
import com.listener.exceptions.SaveException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlForecastDao implements ForecastDao {
    @Autowired
    private SessionFactory factory;
    @Override
    public void save(ForecastEntity entity) throws SaveException {
        Session session = factory.openSession();
        session.beginTransaction();
        try {
            session.save(entity);
        } catch (Exception e){
            session.getTransaction().rollback();
            throw new SaveException();
        }
        session.getTransaction().commit();
    }
}
