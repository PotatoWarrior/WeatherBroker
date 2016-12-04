package com.client.model;

import com.client.entity.City;
import com.entities.ForecastEntity;
import com.entities.ForecastList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Component
public class ForecastModel {
    @Autowired
    private JmsOperations jmsOperations;
    @Autowired
    private RestTemplate restTemplate;
    @Value("${weatherService.url}")
    private String weatherServiceUrl;
    @Value("${restService.url}")
    private String restServiceUrl;

    public void addForecastForCity(City city){
        jmsOperations.convertAndSend(getForecast(city));
    }
    public List<ForecastEntity> getForecastList(){
        ForecastList list = restTemplate.getForObject(restServiceUrl, ForecastList.class);
        return list.getForecastEntities();
    }
    private String getForecast(City city){
        String xml = "";
        URL url = null;
        try {
            url = new URL(weatherServiceUrl.replace("{city}", city.getName()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;
            while((line = reader.readLine()) != null) {
                xml += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xml;
    }
}
