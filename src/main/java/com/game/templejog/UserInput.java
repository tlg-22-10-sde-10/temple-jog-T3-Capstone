package com.game.templejog;

public enum UserInput {
    START_GAME("Start a new Game? y/n"),
    TURN_MUSIC("Do you want the music on? [y/n]")
    ;

    private final String userMsg;
    private UserInput(String msg){this.userMsg = msg; }

    public String getUserMsg(){
        return this.getUserMsg();
    }
}
