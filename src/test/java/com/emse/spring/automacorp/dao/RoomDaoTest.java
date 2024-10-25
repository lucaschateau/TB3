package com.emse.spring.automacorp.dao;

import com.emse.spring.automacorp.model.RoomEntity;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoomDaoTest {

    @Autowired
    private RoomDao roomDao;

    @Test
    @Transactional
    public void shouldFindRoomById() {
        RoomEntity room = roomDao.getReferenceById(-9L); // (1)
        Assertions.assertThat(room.getName()).isEqualTo("Room2"); // (2)
        Assertions.assertThat(room.getFloor()).isEqualTo(1); // (3)
        Assertions.assertThat(room.getTargetTemperature().getValue()).isEqualTo(20.0); // (4)
    }
}
