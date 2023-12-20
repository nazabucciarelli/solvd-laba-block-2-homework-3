package com.solvd.animals_mvc.model;

import com.solvd.animals_mvc.exception.EmptyFieldException;
import com.solvd.animals_mvc.exception.NegativeValueException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Person {
    private int age;
    private String firstName;
    private String lastName;
    private Long genderId;
    private Long countryId;
    private String phoneNumber;

    public Person(int age, String firstName, String lastName, Long genderId,
                  Long countryId, String phoneNumber) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.genderId = genderId;
        this.countryId = countryId;
        this.phoneNumber = phoneNumber;
    }

    public Long getGenderId() {
        return genderId;
    }

    public void setGenderId(Long genderId) {
        this.genderId = genderId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws NegativeValueException {
        if (age < 0) {
            throw new NegativeValueException("Age cannot be negative");
        }
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.isBlank()) {
            throw new EmptyFieldException("Field first name cannot be empty");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.isBlank()) {
            throw new EmptyFieldException("Field last name cannot be empty");
        }
        this.lastName = lastName;
    }

    public Long getGender() {
        return genderId;
    }

    public void setGender(Long genderId) {
        this.genderId = genderId;
    }

    public Long getCountry() {
        return countryId;
    }

    public void setCountry(Long countryId) {
        this.countryId = countryId;
    }
}
