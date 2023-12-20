package com.solvd.animals_mvc.model;

import java.util.Objects;

public class Customer extends Person {
    private int timesVisitingZoo;
    private Long personId;

    public Customer(int age, String firstName, String lastName, Long genderId,
                    Long countryId, int timesVisitingZoo, Long personId, String
                            phoneNumber) {
        super(age, firstName, lastName, genderId, countryId, phoneNumber);
        this.timesVisitingZoo = timesVisitingZoo;
        this.personId = personId;
    }

    public int getTimesVisitingZoo() {
        return timesVisitingZoo;
    }

    public void setTimesVisitingZoo(int timesVisitingZoo) {
        this.timesVisitingZoo = timesVisitingZoo;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return timesVisitingZoo == customer.timesVisitingZoo &&
                Objects.equals(personId, customer.personId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timesVisitingZoo, personId);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "timesVisitingZoo=" + timesVisitingZoo +
                ", personId=" + personId +
                '}';
    }
}
