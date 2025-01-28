package com.inpowered.infrastructure.input;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

public class ConsoleInputReaderTest {
    @Test
    public void testReadInput() {
        String simulatedUserInput = "test input";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        ConsoleInputReader reader = new ConsoleInputReader();
        String input = reader.readInput("Enter some text: ");

        Assertions.assertEquals(simulatedUserInput, input, "The readInput method should return the user's input");
    }
}
