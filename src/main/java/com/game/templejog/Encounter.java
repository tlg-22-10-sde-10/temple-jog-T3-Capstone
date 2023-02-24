package com.game.templejog;

import java.util.List;

/**
 * Created by dev0 on 2/2/23.
 */
public class Encounter {
    String name,type,description,success, image;
    List<String> weakness;
    Integer failure,outcome;

    public Encounter(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }


    public String getDescription() {
        return description;
    }


    public String getSuccess() {
        return success;
    }


    public List<String> getWeakness() {
        return weakness;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}