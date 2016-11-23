package com.client.model;

import com.client.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class ForecastModel {
    @Autowired
    private JmsOperations jmsOperations;
    @Autowired
    @Qualifier("weatherUrl")
    private String weatherServiceUrl;

    public void addForecastForCity(City city){
        jmsOperations.convertAndSend(getForecast(city));
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
