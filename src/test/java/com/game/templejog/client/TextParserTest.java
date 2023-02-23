package com.game.templejog.client;

import org.junit.jupiter.api.Test;

import static com.game.templejog.client.TextParser.parseText;
import static org.junit.jupiter.api.Assertions.*;

class TextParserTest {

    @Test
    void parseText_goSynonym_test() {
        String[] test = parseText("walk south");
        assertEquals("go", test[0]);
    }

    @Test
    void parseText_getSynonym_test() {
        String[] test = parseText("obtain item");
        assertEquals("get", test[0]);
    }
}