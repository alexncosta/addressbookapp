package com.inpowered.application;

import com.inpowered.application.exception.PersonNotFoundException;
import com.inpowered.domain.model.Gender;
import com.inpowered.domain.model.Person;
import com.inpowered.domain.repository.DataLoader;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class AddressBookServiceImpl implements AddressBookService {
    /**
     * Date formatter for parsing the birth dates.
     */
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy");
    private final DataLoader fileDataLoader;

    public AddressBookServiceImpl(DataLoader fileDataLoader) {
        this.fileDataLoader = fileDataLoader;
    }

    @Override
    public long countMalesInAddressBook(List<Person> people) {
        List<Person> immutablePeople = List.copyOf(people);
        return immutablePeople.stream()
                .filter(person -> person.getGender() == Gender.MALE)
                .count();
    }

    @Override
    public Optional<String> oldestPersonInAddressBook(List<Person> people) {
        List<Person> immutablePeople = List.copyOf(people);
        return immutablePeople.stream()
                .min(Comparator.comparing(Person::getDateOfBirth))
                .map(Person::getName);
    }

    @Override
    public String ageDifferenceBetweenTwoPersons(List<Person> people, String firstPerson, String secondPerson) {
        List<Person> immutablePeople = List.copyOf(people);
        var firstPersonSearched = findPersonByName(immutablePeople, firstPerson);
        var secondPersonSearched = findPersonByName(immutablePeople, secondPerson);
        if (firstPersonSearched.isPresent() && secondPersonSearched.isPresent()) {
            long daysDifference =
                    ChronoUnit.DAYS.between(
                            firstPersonSearched.get().getDateOfBirth(),
                            secondPersonSearched.get().getDateOfBirth());
            return String.valueOf(daysDifference);
        } else {
            throw new PersonNotFoundException(
                    String.format("%s or %s not found in the address book.", firstPerson, secondPerson));
        }
    }

    @Override
    public Optional<Person> findPersonByName(List<Person> people, String name) {
        List<Person> immutablePeople = List.copyOf(people);
        return immutablePeople.stream()
                .filter(person -> person.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    @Override
    public List<Person> loadDataFromAddressBook() {
        return Collections.unmodifiableList(fileDataLoader.loadData());
    }
}
