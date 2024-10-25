package com.emse.spring.automacorp.mappertest;

import com.emse.spring.automacorp.fakeentity.FakeEntityBuilder;
import com.emse.spring.automacorp.enums.WindowStatus;
import com.emse.spring.automacorp.mappers.RoomEntityMapper;
import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.dto.RoomEntityDTO;
import com.emse.spring.automacorp.dto.WindowEntityDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RoomMapperTest {

    @Test
    void shouldMapRoom() {
        // Arrange
        RoomEntity roomEntity = FakeEntityBuilder.createRoomEntity(11L, "Room");
        // There is no createBuildingEntity method in FakeEntityBuilder.

        // Act
        RoomEntityDTO room = RoomEntityMapper.mapToRoom(roomEntity);

        // Assert
        RoomEntityDTO expectedRoom = new RoomEntityDTO(
                11L,
                "Room",
                1,
                23.2,
                26.4,
                List.of(
                        new WindowEntityDTO(
                                111L,
                                "Window1Room",
                                WindowStatus.CLOSED,
                                11L
                        ),
                        new WindowEntityDTO(
                                112L,
                                "Window2Room",
                                WindowStatus.CLOSED,
                                11L
                        )
                )
        );
        Assertions.assertThat(room).usingRecursiveAssertion().isEqualTo(expectedRoom);
    }
}