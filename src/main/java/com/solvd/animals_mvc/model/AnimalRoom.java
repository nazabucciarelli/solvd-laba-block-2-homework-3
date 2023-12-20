package com.solvd.animals_mvc.model;

import java.util.Objects;

public class AnimalRoom {
    private String name;
    private int capacity;
    private Long zooId;

    public AnimalRoom(String name, int capacity, Long zooId) {
        this.name = name;
        this.capacity = capacity;
        this.zooId = zooId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Long getZooId() {
        return zooId;
    }

    public void setZooId(Long zooId) {
        this.zooId = zooId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalRoom that = (AnimalRoom) o;
        return capacity == that.capacity && Objects.equals(name, that.name) &&
                Objects.equals(zooId, that.zooId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, capacity, zooId);
    }

    @Override
    public String toString() {
        return "AnimalRoom{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", zooId=" + zooId +
                '}';
    }
}
