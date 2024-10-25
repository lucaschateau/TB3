package com.emse.spring.automacorp.mappers;

import com.emse.spring.automacorp.dto.WindowEntityDTO;
import com.emse.spring.automacorp.dto.RoomEntityDTO;
import com.emse.spring.automacorp.model.RoomEntity;


import java.util.List;

public class RoomEntityMapper {
    public static RoomEntityDTO mapToRoom(RoomEntity roomEntity) {
        List<WindowEntityDTO> windows = roomEntity.getWindows().stream().map(WindowEntityMapper::mapToWindow).toList();
        return new RoomEntityDTO(roomEntity.getId(), roomEntity.getName(), roomEntity.getFloor(), roomEntity.getCurrentTemperature().getValue(), roomEntity.getTargetTemperature().getValue(), windows);
    }
}