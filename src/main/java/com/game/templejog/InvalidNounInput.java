package com.game.templejog;

public enum InvalidNounInput {
    BAD_LOOK("Invalid command. Please provide item name to look for or type 'help'."),
    BAD_GET("Invalid command. Please provide the item name trying to get or type 'help'."),
    BAD_USE("Invalid command. Please provide the item name to use or type 'help'."),
    BAD_SOUND("Invalid command. Please type 'sound on' or 'sound off' command or type 'help'"),
    BAD_NAV("Invalid command. Please provide a direction or type 'help'.");
    private final String warning_message;

    private InvalidNounInput(String msg){
        this.warning_message = msg;
    }
    public String getWarning(){
        return this.warning_message;
    }
}