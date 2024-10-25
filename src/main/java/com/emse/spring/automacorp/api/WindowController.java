package com.emse.spring.automacorp.api;

import com.emse.spring.automacorp.mappers.WindowEntityMapper;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.model.WindowEntity;
import com.emse.spring.automacorp.dto.WindowEntityDTO;
import com.emse.spring.automacorp.dao.WindowDao;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/windows")
@Transactional
public class WindowController {
    private final WindowDao windowDao;

    public WindowController(WindowDao windowDao) {
        this.windowDao = windowDao;
    }

    @GetMapping
    public List<WindowEntityDTO> findALll() {
        return windowDao.findAll().stream().map((WindowEntityMapper::mapToWindow)).collect(Collectors.toList());
    }

    @PostMapping //
    public ResponseEntity<WindowEntityDTO> create(@RequestBody WindowCommand window) { //
        WindowEntity entity = new WindowEntity(window.name(), window.windowStatus(), window.roomEntity());
        WindowEntity saved = windowDao.save(entity);
        return ResponseEntity.ok(WindowEntityMapper.mapToWindow(saved));
    }

    @PutMapping(path = "/{id}") //
    public ResponseEntity<WindowEntityDTO> update(@PathVariable Long id, @RequestBody WindowCommand window) {
        WindowEntity entity = windowDao.findById(id).orElse(null);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        entity.setStatus(window.windowStatus());
        entity.setName(window.name());
        entity.setRoom(window.roomEntity());
        return ResponseEntity.ok(WindowEntityMapper.mapToWindow(entity));
    }

    @GetMapping(path = "/{id}")
    public WindowEntityDTO findById(@PathVariable Long id) {
        return windowDao.findById(id).map(WindowEntityMapper::mapToWindow).orElse(null); //
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        windowDao.deleteById(id);
    }

    public record WindowCommand(String name, SensorEntity windowStatus, RoomEntity roomEntity) {}
}