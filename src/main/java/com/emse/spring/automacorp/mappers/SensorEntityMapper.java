package com.emse.spring.automacorp.mappers;

import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.dto.SensorEntityDTO;

public class SensorEntityMapper {
    public static SensorEntityDTO mapToSensor(SensorEntity sensorEntity) {
        return new SensorEntityDTO(sensorEntity.getId(), sensorEntity.getName(), sensorEntity.getSensorType(),sensorEntity.getValue());
    }


}