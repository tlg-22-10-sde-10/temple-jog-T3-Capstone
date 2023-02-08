package com.game.templejog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Room {
    Integer number;
    String name, description, west, south, north, east;
    List<String> items = new ArrayList<>();
    List<String> encounters_to = new ArrayList<>();
    List<String> encounters_from = new ArrayList<>();
    Boolean hasBeenVisited;
    String sound;

    public Room() {
    }

    public Room(Integer number, String name, String description, String west, String south, String north, String east, Boolean hasBeenVisited, List<String> items, String sound) {
        this.number = number;
        this.name = name;
        this.description = description;
        this.west = west;
        this.south = south;
        this.north = north;
        this.east = east;
        this.hasBeenVisited = hasBeenVisited;
        this.items = items;
        this.sound = sound;
    }

    public String checkDirection(String noun) {
        HashMap<String, String> directions = new HashMap<>();
        directions.put("west", getWest());
        directions.put("north", getNorth());
        directions.put("south", getSouth());
        directions.put("east", getEast());

        String dir = directions.get(noun);
        return dir;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWest() {
        return west;
    }

    public void setWest(String west) {
        this.west = west;
    }

    public String getSouth() {
        return south;
    }

    public void setSouth(String south) {
        this.south = south;
    }

    public String getNorth() {
        return north;
    }

    public void setNorth(String north) {
        this.north = north;
    }

    public String getEast() {
        return east;
    }

    public void setEast(String east) {
        this.east = east;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public List<String> getEncounters_to() {
        return encounters_to;
    }

    public void setEncounters_to(List<String> encounters_to) {
        this.encounters_to = encounters_to;
    }

    public List<String> getEncounters_from() {
        return encounters_from;
    }

    public void setEncounters_from(List<String> encounters_from) {
        this.encounters_from = encounters_from;
    }

    public boolean getHasBeenVisited() {
        return hasBeenVisited;
    }

    public void setHasBeenVisited(Boolean hasBeenVisited) {
        this.hasBeenVisited = hasBeenVisited;
    }

    public String getSound() {
        return sound;
    }
    public void setSound(String sound) {
        this.sound = sound;
    }
}
