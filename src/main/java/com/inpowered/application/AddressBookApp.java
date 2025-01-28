package com.inpowered.application;

import com.inpowered.domain.model.Person;
import com.inpowered.infrastructure.input.ConsoleInputReader;
import com.inpowered.infrastructure.input.InputReader;

import java.util.List;

public class AddressBookApp {
    private final AddressBookService addressBookService = AddressBookServiceFactory.createService("book/AddressBook.txt");

    public void run() {
        InputReader inputReader = new ConsoleInputReader();
        String firstPerson = inputReader.readInput("Enter the first person's name to calculate the difference age between tow persons:");
        String secondPerson = inputReader.readInput("Enter the second person's name to calculate the difference age between tow persons:");

        try {
            List<Person> addressBook = addressBookService.loadDataFromAddressBook();
            System.out.println("Number of Males: " + addressBookService.countMalesInAddressBook(addressBook));
            System.out.println("The oldest person: " + addressBookService.oldestPersonInAddressBook(addressBook).get());
            System.out.println("The age difference: " + addressBookService.ageDifferenceBetweenTwoPersons(addressBook, firstPerson, secondPerson));
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}