package com.inpowered.infrastructure.input;

import java.util.Scanner;

public class ConsoleInputReader implements InputReader {
    private final Scanner scanner = new Scanner(System.in);
    @Override
    public String readInput(String prompt) {
        System.out.print(prompt + "\r\n");
        return scanner.nextLine();
    }
}