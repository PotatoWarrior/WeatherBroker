package com.entities;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "forecastList")
@XmlSeeAlso(ForecastEntity.class)
@XmlAccessorType(XmlAccessType.NONE)
public class ForecastList {
    private List<ForecastEntity> forecastEntities;

    @XmlElement(name = "forecast")
    public List<ForecastEntity> getForecastEntities() {
        return forecastEntities;
    }

    public void setForecastEntities(List<ForecastEntity> forecastEntities) {
        this.forecastEntities = forecastEntities;
    }
}
