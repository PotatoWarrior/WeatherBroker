package com.listener.dao;

import com.entities.ForecastEntity;
import com.listener.exceptions.SaveException;

public interface ForecastDao {
    void save(ForecastEntity entity) throws SaveException;
}
