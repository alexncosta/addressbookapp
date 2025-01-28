package com.inpowered.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class PersonIdTest {
    @Test
    void shouldCreatePersonIdWithProvidedUUID() {
        UUID uuid = UUID.randomUUID();

        PersonId personId = new PersonId(uuid);

        Assertions.assertNotNull(personId);
        Assertions.assertEquals(uuid, personId.value());
    }

    @Test
    void shouldThrowExceptionWhenUUIDIsNull() {
        UUID uuidNull = null;

        NullPointerException exception =
                Assertions.assertThrows(NullPointerException.class, () -> new PersonId(uuidNull));

        Assertions.assertEquals("Person ID cannot be null.", exception.getMessage());
    }

    @Test
    void shouldGeneratePersonIdWithRandomUUID() {
        PersonId personId = new PersonId();

        Assertions.assertNotNull(personId);
        Assertions.assertNotNull(personId.value());
    }

    @Test
    void shouldGenerateUniqueRandomUUIDs() {
        PersonId firstPersonId = new PersonId();
        PersonId secondPersonId = new PersonId();

        Assertions.assertNotNull(firstPersonId.value());
        Assertions.assertNotNull(secondPersonId.value());
        Assertions.assertNotEquals(firstPersonId.value(), secondPersonId.value());
    }
}
