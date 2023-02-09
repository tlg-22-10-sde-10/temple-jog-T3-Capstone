package com.game.templejog.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleInterfaceTest {


    ConsoleInterface console = new ConsoleInterface();

    @Test
    public void testThatDisplayResultDoesNotThrowAnExceptionForAStringThatIsExactly78CharsLong() throws InterruptedException {
        /* If a line is exactly 78 characters long then when it appends spacing before/after to center it,
        *  it should not try to do a String.format("%0s",line) it should do String.format("%s",line) to catch
        *  the condition.
        */
        String testString = "876543210987654321098765432109876543210987654321098765432109876543210987654321"; // 78 characters
        assertEquals(0, console.displayResult(testString,0)); // display result if run fully will return 0
    }
}