package com.inpowered.infrastructure.input;

public interface InputReader {
    /**
     * Reads an input from the user or another source.
     *
     * @param prompt The message to show to the user before reading input.
     * @return The input provided by the user.
     */
    String readInput(String prompt);
}