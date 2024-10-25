package com.emse.spring.automacorp.fakeentity;

import com.emse.spring.automacorp.enums.SensorType;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.model.WindowEntity;

import java.util.List;

import static com.emse.spring.automacorp.enums.SensorType.TEMPERATURE;

public class FakeEntityBuilder {

    public static RoomEntity createRoomEntity(Long id, String name) {
        // Sensor is recreated before each test
        RoomEntity entity = new RoomEntity(
                1,
                name,
                createSensorEntity(1L, "Current Temperature", TEMPERATURE, 23.2));

        entity.setTargetTemperature(createSensorEntity(1L, "Temp", TEMPERATURE, 26.4));
        entity.setId(id);
        entity.setWindows(List.of(
                createWindowEntity(id * 10 + 1L, "Window1" + name, entity),
                createWindowEntity(id * 10 + 2L, "Window2" + name, entity)
        ));
        return entity;
    }

    public static WindowEntity createWindowEntity(Long id, String name, RoomEntity roomEntity) {
        // Sensor is recreated before each test
        WindowEntity windowEntity = new WindowEntity(
                name,
                createSensorEntity(id * 10 + 1L, "Status" + id, SensorType.STATUS, 0.0),
                roomEntity
        );
        windowEntity.setId(id);
        return windowEntity;
    }

    public static SensorEntity createSensorEntity(Long id, String name, SensorType type, Double value) {
        // Sensor is recreated before each test
        SensorEntity sensorEntity = new SensorEntity(type, name);
        sensorEntity.setId(id);
        sensorEntity.setValue(value);
        return sensorEntity;
    }
}