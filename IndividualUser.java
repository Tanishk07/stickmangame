package org.example;

import java.io.Serializable;

public class IndividualUser implements Serializable {
    private String userName;
    private int userScore;
    private GameState userGameState;

    private int highestScore;

    public int getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(int highestScore) {
        this.highestScore = highestScore;
    }

    public IndividualUser(String userName, int userScore) {
        this.userName = userName;
        this.userScore = userScore;
        this.userGameState=null;
        this.highestScore=0;
    }

    public void setData(int userScore) {
        this.userScore = userScore;
    }

    public GameState getUserGameState() {
        return userGameState;
    }

    public void setUserGameState(GameState userGameState) {
        this.userGameState = userGameState;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserScore() {
        return userScore;
    }
}
