package com.emse.spring.automacorp.dto;

import com.emse.spring.automacorp.enums.WindowStatus;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.SensorEntity;

public record WindowEntityDTO(Long id, String name, WindowStatus windowStatus, Long roomId) {
}