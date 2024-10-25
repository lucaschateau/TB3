package com.emse.spring.automacorp.dao;

import com.emse.spring.automacorp.model.WindowEntity;

import java.util.List;

public interface WindowDaoCustom {
    List<WindowEntity> findRoomsWithOpenWindows(Long roomId);
    List<WindowEntity> findWindowByRoomName(String roomName);
    int deleteWindowByRoomId(Long roomId);
    void closeOrOpenAllWindowsByRoomId(Long roomId, boolean isOpen);
}