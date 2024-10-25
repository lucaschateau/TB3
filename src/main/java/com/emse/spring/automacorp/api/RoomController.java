package com.emse.spring.automacorp.api;

import com.emse.spring.automacorp.mappers.RoomEntityMapper;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.dto.RoomEntityDTO;
import com.emse.spring.automacorp.dao.RoomDao;
import com.emse.spring.automacorp.dao.WindowDao;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/rooms")
@Transactional
public class RoomController {
    private final RoomDao roomDao;
    private final WindowDao windowDao;

    public RoomController(RoomDao roomDao, WindowDao windowDao) {
        this.roomDao = roomDao;
        this.windowDao = windowDao;
    }

    @GetMapping
    public List<RoomEntityDTO> findALll() {
        return roomDao.findAll().stream().map((RoomEntityMapper::mapToRoom)).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<RoomEntityDTO> create(@RequestBody RoomCommand room) { //
        RoomEntity entity = new RoomEntity(room.floor(),room.name(), room.currentTemperature());
        RoomEntity saved = roomDao.save(entity);
        return ResponseEntity.ok(RoomEntityMapper.mapToRoom(saved));
    }

    @PutMapping(path = "/{id}") //
    public ResponseEntity<RoomEntityDTO> update(@PathVariable Long id, @RequestBody RoomCommand room) {
        RoomEntity entity = roomDao.findById(id).orElse(null);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        entity.setCurrentTemperature(room.currentTemperature());
        entity.setName(room.name());
        entity.setFloor(room.floor());
        return ResponseEntity.ok(RoomEntityMapper.mapToRoom(entity));
    }

    @GetMapping(path = "/{id}")
    public RoomEntityDTO findById(@PathVariable Long id) {
        return roomDao.findById(id).map(RoomEntityMapper::mapToRoom).orElse(null); //
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        windowDao.deleteWindowByRoomId(id);
        roomDao.deleteById(id);
    }

    @PutMapping(path = "/{id}/openWindows")
    public ResponseEntity<RoomEntityDTO> openWindows(@PathVariable Long id) {
        RoomEntity entity = roomDao.findById(id).orElse(null);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        entity.getWindows().forEach((windowEntity -> {
            SensorEntity windowStatus = windowEntity.getStatus();
            windowStatus.setValue(1.0);
            windowEntity.setStatus(windowStatus);
        }));
        return ResponseEntity.ok(RoomEntityMapper.mapToRoom(entity));
    }

    @PutMapping(path = "/{id}/closeWindows")
    public ResponseEntity<RoomEntityDTO> closeWindows(@PathVariable Long id) {
        RoomEntity entity = roomDao.findById(id).orElse(null);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        entity.getWindows().forEach((windowEntity -> {
            SensorEntity windowStatus = windowEntity.getStatus();
            windowStatus.setValue(0.0);
            windowEntity.setStatus(windowStatus);
        }));
        return ResponseEntity.ok(RoomEntityMapper.mapToRoom(entity));
    }

    public record RoomCommand(String name, SensorEntity currentTemperature, int floor) {}
}