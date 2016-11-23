package com.service.dao;

import com.entities.ForecastEntity;
import com.entities.ForecastEntityPK;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlForecastDao implements ForecastDao {
    @Autowired
    private SessionFactory factory;
    @Override
    public ForecastEntity getByPrimaryKey(ForecastEntityPK key) {
        Session session = factory.openSession();
        ForecastEntity entity = session.get(ForecastEntity.class, key);
        return entity;
    }
}
