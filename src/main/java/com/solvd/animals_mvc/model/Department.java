package com.solvd.animals_mvc.model;

import java.util.Objects;

public class Department {
    private String name;
    private Long zooId;

    public Department(String name, Long zooId) {
        this.name = name;
        this.zooId = zooId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        Department that = (Department) o;
        return Objects.equals(name, that.name) && Objects.equals(zooId, that.zooId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, zooId);
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", zooId=" + zooId +
                '}';
    }
}
