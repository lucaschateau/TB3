package com.emse.spring.automacorp.api;

import com.emse.spring.automacorp.model.SensorType;
import com.emse.spring.automacorp.mappers.SensorEntityMapper;
import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.dto.SensorEntityDTO;
import com.emse.spring.automacorp.dao.SensorDao;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/sensors")
@Transactional
public class SensorController {
    private final SensorDao sensorDao;

    public SensorController(SensorDao sensorDao) {
        this.sensorDao = sensorDao;
    }

    @GetMapping //
    public List<SensorEntityDTO> findAll() {
        return sensorDao.findAll()
                .stream()
                .map(SensorEntityMapper::mapToSensor)
                .sorted(Comparator.comparing((SensorEntityDTO::name)))
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public SensorEntityDTO findById(@PathVariable Long id) {
        return sensorDao.findById(id).map(SensorEntityMapper::mapToSensor).orElse(null); //
    }

    @PostMapping //
    public ResponseEntity<SensorEntityDTO> create(@RequestBody SensorCommand sensor) { //
        SensorEntity entity = new SensorEntity(sensor.sensorType(), sensor.name());
        entity.setValue(sensor.value());
        SensorEntity saved = sensorDao.save(entity);
        return ResponseEntity.ok(SensorEntityMapper.mapToSensor(saved));
    }


    @PutMapping(path = "/{id}") //
    public ResponseEntity<SensorEntityDTO> update(@PathVariable Long id, @RequestBody SensorCommand sensor) {
        SensorEntity entity = sensorDao.findById(id).orElse(null);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        entity.setValue(sensor.value());
        entity.setName(sensor.name());
        entity.setSensorType(sensor.sensorType());
        // (11)
        return ResponseEntity.ok(SensorEntityMapper.mapToSensor(entity));
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        sensorDao.deleteById(id);
    }

    public record SensorCommand(SensorType sensorType, String name, Double value) {}
}