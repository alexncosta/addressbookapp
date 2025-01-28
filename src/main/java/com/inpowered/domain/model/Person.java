package com.inpowered.domain.model;

import com.inpowered.application.exception.PersonIllegalArgumentException;

import java.time.LocalDate;

/**
 * Represents a person with name, gender, and date of birth.
 */
public class Person {
    PersonId personId;
    final String name;
    final Gender gender;
    final LocalDate dateOfBirth;

    public Person(String name, Gender gender, LocalDate dateOfBirth) {
        validateInput(name, gender, dateOfBirth);
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    private void validateInput(String name, Gender gender, LocalDate dateOfBirth) {
        StringBuilder sb = new StringBuilder();
        if (name == null || name.isBlank()) {
            sb.append("Name cannot be null or empty.");
        }

        if (gender == null || gender.getGender().isBlank()) {
            sb.append("Gender cannot be null or empty.");
        }

        if (dateOfBirth == null) {
            sb.append("Date of birth cannot be null.");
        }

        if (!sb.isEmpty()) {
            throw new PersonIllegalArgumentException(sb.toString());
        }
    }

    public PersonId getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}