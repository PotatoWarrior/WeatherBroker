package com.listener;

import com.entities.ForecastEntity;
import com.listener.dao.ForecastDao;
import com.listener.exceptions.SaveException;
import com.listener.exceptions.XmlParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;

public class MessageListener {
    @Autowired
    private XmlToEntityConverter converter;
    @Autowired
    private ForecastDao dao;
    @Autowired
    private JmsOperations operations;
    public void onMessage(String xml){
        try {
            ForecastEntity entity = converter.convert(xml);
            dao.save(entity);
        } catch (XmlParseException e) {
            e.printStackTrace();
        } catch (SaveException e){
            operations.convertAndSend(xml);
            e.printStackTrace();
        }
    }
}
