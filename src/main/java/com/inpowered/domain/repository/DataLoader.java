package com.inpowered.domain.repository;

import com.inpowered.domain.model.Person;

import java.util.List;

public interface DataLoader {
    List<Person> loadData();
}
