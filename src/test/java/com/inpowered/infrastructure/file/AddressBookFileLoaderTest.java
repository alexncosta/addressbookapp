package com.inpowered.infrastructure.file;

import com.inpowered.application.exception.DataLoadException;
import com.inpowered.domain.model.Gender;
import com.inpowered.domain.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AddressBookFileLoaderTest {
    private AddressBookFileLoader loader;

    @BeforeEach
    void setUp() {
        loader = new AddressBookFileLoader();
    }

    @Test
    void testLoadDataFromFileSuccess() throws IOException {
        String filePath = "book/AddressBook.txt";
        InputStream fileStream =
                new ByteArrayInputStream("Bill McKnight, Male, 16/03/77\nPaul Robinson, Male, 15/01/85".getBytes());
        try (InputStream in = fileStream) {
            List<Person> result = loader.loadDataFromFile(filePath);
            Assertions.assertEquals(5, result.size());
            Assertions.assertEquals("Bill McKnight", result.get(0).getName());
            Assertions.assertEquals(Gender.MALE, result.get(0).getGender());
            Assertions.assertEquals("1977-03-16", result.get(0).getDateOfBirth().toString());
            Assertions.assertEquals("Paul Robinson", result.get(1).getName());
            Assertions.assertEquals(Gender.MALE, result.get(1).getGender());
            Assertions.assertEquals("1985-01-15", result.get(1).getDateOfBirth().toString());
        }
    }

    @Test
    void testLoadDataFromFileWithExceptionFileNotFound() {
        String filePath = "invalidFile.txt";
        DataLoadException thrown = Assertions.assertThrows(DataLoadException.class, () -> {
            loader.loadDataFromFile(filePath);
        });
        Assertions.assertEquals("File not found.", thrown.getMessage());
    }

    @Test
    void testLoadDataFromFileInvalidData() throws IOException {
        String filePath = "invalidFile.txt";
        InputStream fileStream = new ByteArrayInputStream("Bill McKnight, Male, 16/03/77\nInvalid Data".getBytes());

        try (InputStream in = fileStream) {
            DataLoadException thrown = Assertions.assertThrows(DataLoadException.class, () -> {
                loader.loadDataFromFile(filePath);
            });
            Assertions.assertEquals("File not found.", thrown.getMessage());
        }
    }
}
