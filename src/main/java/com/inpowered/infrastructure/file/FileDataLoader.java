package com.inpowered.infrastructure.file;

import com.inpowered.domain.model.Person;
import com.inpowered.domain.repository.DataLoader;

import java.util.List;

public class FileDataLoader implements DataLoader {
    private final String filePath;

    public FileDataLoader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Person> loadData() {
        return new AddressBookFileLoader().loadDataFromFile(filePath);
    }
}
