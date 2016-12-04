package com.service.controllers;

import com.entities.ForecastEntity;
import com.entities.ForecastList;
import com.service.model.ForecastModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @Autowired
    private ForecastModel forecastModel;

    @RequestMapping(value = "/getForecast")
    public @ResponseBody ForecastEntity getForecast(@RequestParam(name = "city") String city, @RequestParam(name = "country") String country, @RequestParam(name = "region") String region, @RequestParam(name = "date") String date, Model model) {
        return forecastModel.getEntity(city, country, region, date);
    }

    @RequestMapping(value = "/getAll")
    public @ResponseBody ForecastList getAll() {
        ForecastList result = new ForecastList();
        result.setForecastEntities(forecastModel.getAll());
        return result;
    }
}
