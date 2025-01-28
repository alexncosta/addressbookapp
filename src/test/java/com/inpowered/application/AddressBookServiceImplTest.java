package com.inpowered.application;

import com.inpowered.application.AddressBookServiceImpl;
import com.inpowered.application.exception.PersonNotFoundException;
import com.inpowered.domain.model.Gender;
import com.inpowered.domain.model.Person;
import com.inpowered.domain.repository.DataLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddressBookServiceImplTest {
    private AddressBookServiceImpl addressBookService;
    private DataLoader dataLoader;

    @BeforeEach
    void setUp() {
        dataLoader = mock(DataLoader.class);
        addressBookService = new AddressBookServiceImpl(dataLoader);
    }

    @Test
    void testCountMalesInAddressBook() {
        List<Person> people = getPeople();

        long maleCount = addressBookService.countMalesInAddressBook(people);

        Assertions.assertEquals(3, maleCount);
    }

    @Test
    void testOldestPersonInAddressBook() {
        List<Person> people = getPeople();

        Optional<String> oldestPerson = addressBookService.oldestPersonInAddressBook(people);

        Assertions.assertTrue(oldestPerson.isPresent());
        Assertions.assertEquals("Wes Jackson", oldestPerson.get());
    }

    @Test
    void testAgeDifferenceBetweenTwoPersons() {
        List<Person> people = getPeople();

        String ageDifference = addressBookService.ageDifferenceBetweenTwoPersons(people, "Bill McKnight", "Paul Robinson");

        Assertions.assertEquals("2862", ageDifference);
    }

    @Test
    void testAgeDifferenceThrowsExceptionWhenPersonNotFound() {
        List<Person> people = getPeople();

        Assertions.assertThrows(PersonNotFoundException.class, () ->
                addressBookService.ageDifferenceBetweenTwoPersons(people, "John Connor", "Sarah Connor"));
    }

    @Test
    void testFindPersonByName() {
        List<Person> people = getPeople();

        Optional<Person> person = addressBookService.findPersonByName(people, "Bill McKnight");

        Assertions.assertTrue(person.isPresent());
        Assertions.assertEquals("Bill McKnight", person.get().getName());
    }

    @Test
    void testFindPersonByNameNotFound() {
        List<Person> people = getPeople();

        Optional<Person> person = addressBookService.findPersonByName(people, "Clark Kent");

        Assertions.assertTrue(person.isEmpty());
    }

    @Test
    void testLoadDataFromAddressBook() {
        List<Person> mockData = getPeople();

        when(dataLoader.loadData()).thenReturn(mockData);

        List<Person> loadedData = addressBookService.loadDataFromAddressBook();

        Assertions.assertEquals(5, loadedData.size());
        Assertions.assertEquals("Bill McKnight", loadedData.get(0).getName());
        Assertions.assertEquals("Paul Robinson", loadedData.get(1).getName());
        Assertions.assertEquals("Gemma Lane", loadedData.get(2).getName());
        Assertions.assertEquals("Sarah Stone", loadedData.get(3).getName());
        Assertions.assertEquals("Wes Jackson", loadedData.get(4).getName());
    }

    private static List<Person> getPeople() {
        return List.of(
                new Person("Bill McKnight", Gender.MALE, LocalDate.of(1977, 3, 16)),
                new Person("Paul Robinson", Gender.MALE, LocalDate.of(1985, 1, 15)),
                new Person("Gemma Lane", Gender.FEMALE, LocalDate.of(1991, 11, 20)),
                new Person("Sarah Stone", Gender.FEMALE, LocalDate.of(1980, 9, 20)),
                new Person("Wes Jackson", Gender.MALE, LocalDate.of(1974, 8, 14))
        );
    }
}
