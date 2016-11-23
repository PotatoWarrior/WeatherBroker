package com.service.dao;

import com.entities.ForecastEntity;
import com.entities.ForecastEntityPK;

public interface ForecastDao {
    ForecastEntity getByPrimaryKey(ForecastEntityPK key);
}
