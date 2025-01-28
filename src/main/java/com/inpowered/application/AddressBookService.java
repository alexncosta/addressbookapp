package com.inpowered.application;

import com.inpowered.domain.model.Person;

import java.util.List;
import java.util.Optional;

public interface AddressBookService {
    long countMalesInAddressBook(List<Person> people);
    Optional<String> oldestPersonInAddressBook(List<Person> people);
    String ageDifferenceBetweenTwoPersons(List<Person> people, String firstPerson, String secondPerson);
    Optional<Person> findPersonByName(List<Person> people, String name);
    List<Person> loadDataFromAddressBook();
}
