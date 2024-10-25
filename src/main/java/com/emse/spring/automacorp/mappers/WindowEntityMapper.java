package com.emse.spring.automacorp.mappers;

import com.emse.spring.automacorp.dto.WindowEntityDTO;
import com.emse.spring.automacorp.model.WindowEntity;
import com.emse.spring.automacorp.enums.WindowStatus;

public class WindowEntityMapper {
    public static WindowEntityDTO mapToWindow(WindowEntity windowEntity) {
        WindowStatus windowStatus = windowEntity.getStatus().getValue() == 0 ? WindowStatus.CLOSED : WindowStatus.OPENED;
        return new WindowEntityDTO(windowEntity.getId(), windowEntity.getName(), windowStatus, windowEntity.getRoom().getId());
    }
}
