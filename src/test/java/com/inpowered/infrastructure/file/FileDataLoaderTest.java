package com.inpowered.infrastructure.file;

import com.inpowered.domain.model.Gender;
import com.inpowered.domain.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.MockitoAnnotations.openMocks;

public class FileDataLoaderTest {
    @Mock
    private AddressBookFileLoader addressBookFileLoaderMock;

    private FileDataLoader fileDataLoader;
    private final String filePath = "src/main/resources/book/AddressBook.txt";

    @BeforeEach
    void setUp() {
        openMocks(this);
        fileDataLoader = new FileDataLoader(filePath) {
            @Override
            public List<Person> loadData() {
                return addressBookFileLoaderMock.loadDataFromFile(filePath);
            }
        };
    }

    @Test
    void testLoadData() {
        List<Person> mockData = getPeople();
        Mockito.when(addressBookFileLoaderMock.loadDataFromFile(filePath)).thenReturn(mockData);

        List<Person> result = fileDataLoader.loadData();

        Assertions.assertEquals(5, result.size());
        Assertions.assertEquals("Bill McKnight", result.get(0).getName());
        Assertions.assertEquals("Paul Robinson", result.get(1).getName());
        Assertions.assertEquals("Gemma Lane", result.get(2).getName());
        Assertions.assertEquals("Sarah Stone", result.get(3).getName());
        Assertions.assertEquals("Wes Jackson", result.get(4).getName());
        Mockito.verify(addressBookFileLoaderMock, Mockito.times(1)).loadDataFromFile(filePath);
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
