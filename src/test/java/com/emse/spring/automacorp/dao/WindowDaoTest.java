package com.emse.spring.automacorp.dao;

import com.emse.spring.automacorp.model.RoomEntity;
import com.emse.spring.automacorp.model.WindowEntity;
import com.emse.spring.automacorp.dao.RoomDao;
import com.emse.spring.automacorp.dao.WindowDao;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest //
class WindowDaoTest {
    @Autowired //
    private WindowDao windowDao;

    @Autowired
    private RoomDao roomDao;

    @Test
    @Transactional
    public void shouldFindAWindowById() {
        WindowEntity window = windowDao.getReferenceById(-10L); //
        Assertions.assertThat(window.getName()).isEqualTo("Window 1");
        Assertions.assertThat(window.getStatus().getValue()).isEqualTo(1.0);
    }

    @Test
    public void shouldFindRoomsWithOpenWindows() {
        List<WindowEntity> result = windowDao.findRoomsWithOpenWindows(-10L);
        Assertions.assertThat(result)
                .hasSize(1)
                .extracting("id", "name")
                .containsExactly(Tuple.tuple(-10L, "Window 1"));
    }

    @Test
    @Transactional
    public void shouldCloseOrOpenAllWindowsByRoomId() {

        Long roomId = -9L;

        windowDao.closeOrOpenAllWindowsByRoomId(roomId, false);

        List<WindowEntity> windowsAfterClose = windowDao.findWindowByRoomName("Room2");
        Assertions.assertThat(windowsAfterClose)
                .hasSize(2)
                .allMatch(window -> window.getStatus().getValue() == 0.0);

        windowDao.closeOrOpenAllWindowsByRoomId(roomId, true);

        List<WindowEntity> windowsAfterOpen = windowDao.findWindowByRoomName("Room2");
        Assertions.assertThat(windowsAfterOpen)
                .hasSize(2)
                .allMatch(window -> window.getStatus().getValue() > 0.0);
    }


    @Test
    public void shouldNotFindRoomsWithOpenWindows() {
        List<WindowEntity> result = windowDao.findRoomsWithOpenWindows(-9L);
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    public void shouldFindWindowByRoomName() {

        String roomName = "Room1";

        List<WindowEntity> windows = windowDao.findWindowByRoomName(roomName);

        Assertions.assertThat(windows)
                .hasSize(2)
                .extracting("id", "name")
                .containsExactlyInAnyOrder(
                        Tuple.tuple(-10L, "Window 1"),
                        Tuple.tuple(-9L, "Window 2")
                );
    }

    @Test
    @Transactional
    public void shouldDeleteWindowByRoomId() {

        Long roomId = -10L; // ID of Room1

        int deletedCount = windowDao.deleteWindowByRoomId(roomId);

        Assertions.assertThat(deletedCount).isEqualTo(2);

        List<WindowEntity> windows = windowDao.findWindowByRoomName("Room1");
        Assertions.assertThat(windows).isEmpty();
    }

    @Test
    @Transactional
    public void shouldDeleteWindowsRoom() {
        RoomEntity room = roomDao.getById(-10L);
        List<Long> roomIds = room.getWindows().stream().map(WindowEntity::getId).collect(Collectors.toList());
        Assertions.assertThat(roomIds).hasSize(2);

        windowDao.deleteWindowByRoomId(-10L);
        List<WindowEntity> result = windowDao.findAllById(roomIds);
        Assertions.assertThat(result).isEmpty();

    }



}