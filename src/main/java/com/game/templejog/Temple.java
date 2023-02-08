package com.game.templejog;

import java.util.List;
import java.util.Map;

public class Temple {
    private Map<String,Room> easymap;
    private Map<String,Encounter> encounters;
    private Map<String,Item> items;

    public Map<String, Room> getEasymap() {
        return easymap;
    }

    public void setEasymap(Map<String, Room> easymap) {
        this.easymap = easymap;
    }

    public Map<String, Encounter> getEncounters() {
        return encounters;
    }

    public void setEncounters(Map<String, Encounter> encounters) {
        this.encounters = encounters;
    }

    public Map<String, Item> getItems() {
        return items;
    }

    public void setItems(Map<String, Item> items) {
        this.items = items;
    }
}