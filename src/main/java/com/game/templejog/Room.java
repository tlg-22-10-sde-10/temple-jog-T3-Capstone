package com.game.templejog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Room {
//    TODO private fields
    Integer number;
    String name;
    String description, west, south, north, east;
    List<String> items = new ArrayList<>();
    List<String> encounters_to = new ArrayList<>();
    List<String> encounters_from = new ArrayList<>();
    Boolean hasBeenVisited;
    Boolean isLocked;
    String sound;
    String curLocation;

    public Room() {}
    public Room(Integer number, String name, String description, String west, String south, String north, String east, List<String> items, List<String> encounters_to, List<String> encounters_from, Boolean hasBeenVisited, String sound,String curLocation) {
        this.number = number;
        this.name = name;
        this.description = description;
        this.west = west;
        this.south = south;
        this.north = north;
        this.east = east;
        this.encounters_to = encounters_to;
        this.encounters_from = encounters_from;
        this.hasBeenVisited = hasBeenVisited;
        this.items = items;
        this.sound = sound;
        this.curLocation = curLocation;
    }

    public Room(Integer number, String name, String description, String west, String south, String north, String east){
        this.number = number;
        this.name = name;
        this.description = description;
        this.west = west;
        this.south = south;
        this.north = north;
        this.east = east;


    }


    //  HELPER METHODS
    public String checkDirection(String noun) {
        HashMap<String, String> directions = new HashMap<>();
        directions.put("west", getWest());
        directions.put("north", getNorth());
        directions.put("south", getSouth());
        directions.put("east", getEast());
        String dir = directions.get(noun);
        return dir;
    }
    public Boolean directionBlockedByDoor(){
        return getEncounters_to().contains("locked door");
    }

    public Boolean removeEncounter(String targetEncounter){
        if( getEncounters_to().contains(targetEncounter) ){
            Boolean removedEncounterTo = getEncounters_to().remove(targetEncounter);
            return removedEncounterTo;
        }
        return false;
    }

//  ACCESSOR METHODS

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }


    public String getWest() {
        return west;
    }


    public String getSouth() {
        return south;
    }


    public String getNorth() {
        return north;
    }


    public String getEast() {
        return east;
    }


    public List<String> getItems() {
        return items;
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


    public boolean getHasBeenVisited() {
        return hasBeenVisited;
    }

    public void setHasBeenVisited(Boolean hasBeenVisited) {
        this.hasBeenVisited = hasBeenVisited;
    }

    public Boolean getIsLocked() { return isLocked; }


    public String getSound() {
        return sound;
    }

    public String getCurLocation() {
        return curLocation;
    }

}