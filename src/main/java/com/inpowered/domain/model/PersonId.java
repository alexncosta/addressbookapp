package com.inpowered.domain.model;

import java.util.UUID;

public record PersonId(UUID value) {
    public PersonId {
        if (value == null) {
            throw new NullPointerException("Person ID cannot be null.");
        }
    }

    public PersonId() {
        this(UUID.randomUUID());
    }
}
