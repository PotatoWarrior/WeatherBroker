package com.service.dao;

import com.entities.ForecastEntity;
import com.entities.ForecastEntityPK;

import java.util.List;

public interface ForecastDao {
    ForecastEntity getByPrimaryKey(ForecastEntityPK key);
    List<ForecastEntity> getAll();
}
