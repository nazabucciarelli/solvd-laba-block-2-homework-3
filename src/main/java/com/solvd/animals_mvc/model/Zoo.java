package com.solvd.animals_mvc.model;

import java.util.Objects;

public class Zoo {
    private String name;
    private int customersCapacity;

    public Zoo(String name, int customersCapacity) {
        this.name = name;
        this.customersCapacity = customersCapacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCustomersCapacity() {
        return customersCapacity;
    }

    public void setCustomersCapacity(int customersCapacity) {
        this.customersCapacity = customersCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zoo zoo = (Zoo) o;
        return customersCapacity == zoo.customersCapacity && Objects.equals(name, zoo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, customersCapacity);
    }

    @Override
    public String toString() {
        return "Zoo{" +
                "name='" + name + '\'' +
                ", customerCapacity=" + customersCapacity +
                '}';
    }
}
