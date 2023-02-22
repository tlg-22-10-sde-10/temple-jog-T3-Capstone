package com.game.templejog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    Room room = new Room();
    @BeforeEach
    void setUp() {

         room = new Room(0, "test1","this is a test","west","south","north","");
    }

    @Test
    void positiveCheckDirection() {
        HashMap<String, String> directions = new HashMap<>();
        directions.put("west", room.getWest());
        directions.put("north", room.getNorth());
        directions.put("south", room.getSouth());
        directions.put("east", room.getEast());
        String dir = directions.get("west");
        assertEquals("west",dir);
    }
    @Test
    void negativeCheckDirection() {
        HashMap<String, String> directions = new HashMap<>();
        directions.put("west", room.getWest());
        directions.put("north", room.getNorth());
        directions.put("south", room.getSouth());
        directions.put("east", room.getEast());
        String dir = directions.get("east");
        assertEquals("",dir);
    }

}