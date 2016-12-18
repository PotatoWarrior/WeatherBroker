package com.service.model;

import com.entities.ForecastEntity;
import com.entities.ForecastEntityPK;
import com.service.dao.ForecastDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class ForecastModel {
    @Autowired
    private ForecastDao dao;

    public ForecastEntity getEntity(String city, String country, String region, String date){
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date parsedDate = null;
        try {
            parsedDate = new Date(format.parse(date).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ForecastEntityPK key = new ForecastEntityPK();
        key.setCity(city);
        key.setCountry(country);
        key.setRegion(region);
        key.setDate(parsedDate);
        return dao.getByPrimaryKey(key);
    }

    public List<ForecastEntity> getAll(){
        return dao.getAll();
    }
}
