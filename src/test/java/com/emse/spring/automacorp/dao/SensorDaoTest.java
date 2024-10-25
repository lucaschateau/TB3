package com.emse.spring.automacorp.dao;

import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.model.SensorType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Optional;

@SpringBootTest
public class SensorDaoTest {

    @Autowired
    private SensorDao sensorDao;

    @Test
    public void shouldFindSensorById() {
        SensorEntity sensor = new SensorEntity();
        sensor.setId(-10L);
        sensor.setSensorType(SensorType.TEMPERATURE);
        sensor.setValue(21.3);
        sensor.setName("Temperature room 2");
        sensorDao.save(sensor);

        Optional<SensorEntity> sensorOpt = sensorDao.findById(-9L);
        Assertions.assertThat(sensorOpt).isPresent();
        SensorEntity foundSensor = sensorOpt.get();

        Assertions.assertThat(foundSensor.getSensorType()).isEqualTo(SensorType.STATUS);
        Assertions.assertThat(foundSensor.getValue()).isEqualTo(1);
    }
}
