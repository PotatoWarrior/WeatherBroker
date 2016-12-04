package com.client.controllers;

import com.client.entity.City;
import com.client.model.ForecastModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ApplicationController {
    @Autowired
    ForecastModel forecastModel;

    @RequestMapping(value = {"/", "homepage"}, method = RequestMethod.GET)
    public String mainPage(Model model){
        model.addAttribute("city", new City());
        model.addAttribute("forecastList", forecastModel.getForecastList());
        return "homepage";
    }
    @RequestMapping(value = {"/", "homepage"}, method = RequestMethod.POST)
    public String addForecast(@ModelAttribute("city") City city, Model model){
        forecastModel.addForecastForCity(city);
        model.addAttribute("forecastList", forecastModel.getForecastList());
        return "homepage";
    }
}
