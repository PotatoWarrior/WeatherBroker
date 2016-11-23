package com.listener;

import com.entities.ForecastEntity;
import com.listener.exceptions.XmlParseException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class XmlToEntityConverter {
    @Value("${city}")
    private String cityPath;
    @Value("${country}")
    private String countryPath;
    @Value("${region}")
    private String regionPath;
    @Value("${date}")
    private String datePath;
    @Value("${high}")
    private String highPath;
    @Value("${low}")
    private String lowPath;
    @Autowired
    private SimpleDateFormat format;

    public ForecastEntity convert(String xml) throws XmlParseException {
        Document document;
        try {
            document = new SAXReader().read(new ByteArrayInputStream(xml.getBytes()));
        } catch (DocumentException e) {
            throw new XmlParseException();
        }
        ForecastEntity entity = new ForecastEntity();
        entity.setCity(document.valueOf(cityPath).trim());
        entity.setCountry(document.valueOf(countryPath).trim());
        entity.setRegion(document.valueOf(regionPath).trim());
        Date date;
        try {
            date = format.parse(document.valueOf(datePath));
        } catch (ParseException e) {
            throw new XmlParseException();
        }

        entity.setDate(new java.sql.Date(date.getTime()));
        try {
            entity.setHigh(Short.parseShort(document.valueOf(highPath)));
            entity.setLow(Short.parseShort(document.valueOf(lowPath)));
        } catch (NumberFormatException e){
            throw new XmlParseException();
        }
        return entity;
    }
}
