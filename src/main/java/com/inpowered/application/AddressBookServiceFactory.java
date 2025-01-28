package com.inpowered.application;

import com.inpowered.domain.repository.DataLoader;
import com.inpowered.infrastructure.file.FileDataLoader;

public class AddressBookServiceFactory {
    public static AddressBookService createService(String filePath) {
        DataLoader dataLoader = new FileDataLoader(filePath);
        return new AddressBookServiceImpl(dataLoader);
    }
}
