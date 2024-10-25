package com.emse.spring.automacorp.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name="SP_ROOM")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer floor;

    @Column(nullable = false)
    private String name;

    @OneToOne
    private SensorEntity currentTemperature;

    @OneToOne
    private SensorEntity targetTemperature;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WindowEntity> windows;

    // Constructor avec les champs non nullables
    public RoomEntity(Integer floor, String name, SensorEntity currentTemperature) {
        this.floor = floor;
        this.name = name;
        this.currentTemperature = currentTemperature;
    }


    public RoomEntity() {}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SensorEntity getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(SensorEntity currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public SensorEntity getTargetTemperature() {
        return targetTemperature;
    }

    public void setTargetTemperature(SensorEntity targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public List<WindowEntity> getWindows() {
        return windows;
    }

    public void setWindows(List<WindowEntity> windows) {
        this.windows = windows;
    }
}
