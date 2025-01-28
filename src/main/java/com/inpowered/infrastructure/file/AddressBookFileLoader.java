package com.inpowered.infrastructure.file;

import com.inpowered.application.exception.DataLoadException;
import com.inpowered.domain.model.Gender;
import com.inpowered.domain.model.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddressBookFileLoader {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy");

    public List<Person> loadDataFromFile(String filePath) {
        List<Person> people = new ArrayList<>();
        int numberOfColumnsPerLine = 3;

        try (InputStream fileStream = AddressBookFileLoader.class.getClassLoader().getResourceAsStream(filePath)) {
            if (fileStream == null) {
                throw new DataLoadException("File not found.");
            }
            String line;
            try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(fileStream))) {
                while ((line = fileReader.readLine()) != null) {
                    String[] parts = line.split(", ");
                    if (parts.length == numberOfColumnsPerLine) {
                        String name = parts[0].trim();
                        Gender gender = parts[1].trim().equalsIgnoreCase("Male") ? Gender.MALE : Gender.FEMALE;
                        LocalDate dateOfBirth = LocalDate.parse(parts[2].trim(), DATE_FORMATTER);
                        if (dateOfBirth.getYear() > 1999) {
                            dateOfBirth = dateOfBirth.minusYears(100);
                        }
                        people.add(new Person(name, gender, dateOfBirth));
                    } else {
                        throw new DataLoadException("Invalid data in the address book file or error reading file.");
                    }
                }
            }
        } catch (IOException e) {
            throw new DataLoadException("Error reading file.");
        }
        return Collections.unmodifiableList(people);
    }
}
