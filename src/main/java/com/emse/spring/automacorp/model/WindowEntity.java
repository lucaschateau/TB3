package com.emse.spring.automacorp.model;

import jakarta.persistence.*;


@Entity
@Table(name="SP_WINDOW")
public class WindowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    private SensorEntity windowStatus;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private RoomEntity room;

    // Constructor avec les champs non nullables
    public WindowEntity(String name, SensorEntity windowStatus, RoomEntity room) {
        this.name = name;
        this.windowStatus = windowStatus;
        this.room = room;
    }

    // Constructeur par défaut
    public WindowEntity() {}


    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SensorEntity getStatus() {
        return windowStatus;
    }

    public void setStatus(SensorEntity status) {
        this.windowStatus = status;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }
}
