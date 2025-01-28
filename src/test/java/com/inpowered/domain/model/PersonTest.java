package com.inpowered.domain.model;

import com.inpowered.application.exception.PersonIllegalArgumentException;
import com.inpowered.application.exception.PersonNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class PersonTest {
    @Test
    void shouldCreatePersonSuccessfully() {
        String name = "Alice Chain";
        Gender gender = Gender.FEMALE;
        LocalDate dateOfBirth = LocalDate.of(1999, 3, 17);

        Person person = new Person(name, gender, dateOfBirth);

        Assertions.assertNotNull(person);
        Assertions.assertEquals(name, person.getName());
        Assertions.assertEquals(gender, person.getGender());
        Assertions.assertEquals(dateOfBirth, person.getDateOfBirth());
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        String name = null;
        Gender gender = Gender.MALE;
        LocalDate dateOfBirth = LocalDate.of(1988, 11, 22);

        PersonIllegalArgumentException exception =
                Assertions.assertThrows(PersonIllegalArgumentException.class, () ->
                        new Person(name, gender, dateOfBirth));
        Assertions.assertTrue(exception.getMessage().contains("Name cannot be null or empty."));
    }

    @Test
    void shouldThrowExceptionWhenNameIsEmpty() {
        String name = "";
        Gender gender = Gender.MALE;
        LocalDate dateOfBirth = LocalDate.of(1990, 1, 1);

        PersonIllegalArgumentException exception =
                Assertions.assertThrows(PersonIllegalArgumentException.class, () ->
                        new Person(name, gender, dateOfBirth));
        Assertions.assertTrue(exception.getMessage().contains("Name cannot be null or empty."));
    }

    @Test
    void shouldThrowExceptionWhenGenderIsNull() {
        String name = "Paul Newman";
        Gender gender = null;
        LocalDate dateOfBirth = LocalDate.of(2001, 6, 29);

        PersonIllegalArgumentException exception =
                Assertions.assertThrows(PersonIllegalArgumentException.class, () ->
                        new Person(name, gender, dateOfBirth));
        Assertions.assertTrue(exception.getMessage().contains("Gender cannot be null or empty."));
    }

    @Test
    void shouldThrowExceptionWhenDateOfBirthIsNull() {
        String name = "Mary Jane";
        Gender gender = Gender.FEMALE;
        LocalDate dateOfBirth = null;

        PersonIllegalArgumentException exception =
                Assertions.assertThrows(PersonIllegalArgumentException.class, () ->
                        new Person(name, gender, dateOfBirth));
        Assertions.assertTrue(exception.getMessage().contains("Date of birth cannot be null."));
    }
}
