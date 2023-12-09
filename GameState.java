package org.example;


import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable {

    private IndividualUser playingUser;
    private ArrayList<Double> towerXCoordinateStore;
    private ArrayList<Double> towerWidthsStore;

    private int playingCharacterStore;

    private int cherryCountStore;
    private int lifeCountStore;
    private int scoreCountStore;

    private int uiNumStore;

    private Tower startTower;
    private Tower endTower;

    public Tower getStartTower() {
        return startTower;
    }

    public void setStartTower(Tower startTower) {
        this.startTower = startTower;
    }

    public Tower getEndTower() {
        return endTower;
    }

    public void setEndTower(Tower endTower) {
        this.endTower = endTower;
    }

    public Tower getInterTower() {
        return interTower;
    }

    public void setInterTower(Tower interTower) {
        this.interTower = interTower;
    }

    private Tower interTower;

    public GameState(IndividualUser playingUser, ArrayList<Double> towerXCoordinateStore, ArrayList<Double> towerWidthsStore, int playingCharacterStore, int cherryCountStore, int lifeCountStore, int scoreCountStore, int uiNumStore,Tower startTower, Tower interTower,Tower endTower) {
        this.playingUser = playingUser;
        this.towerXCoordinateStore = towerXCoordinateStore;
        this.towerWidthsStore = towerWidthsStore;
        this.playingCharacterStore = playingCharacterStore;
        this.cherryCountStore = cherryCountStore;
        this.lifeCountStore = lifeCountStore;
        this.scoreCountStore = scoreCountStore;
        this.uiNumStore = uiNumStore;
        this.startTower = startTower;
        this.endTower = endTower;
        this.interTower = interTower;
    }

    public IndividualUser getPlayingUser() {
        return playingUser;
    }

    public void setPlayingUser(IndividualUser playingUser) {
        this.playingUser = playingUser;
    }

    public ArrayList<Double> getTowerXCoordinateStore() {
        return towerXCoordinateStore;
    }

    public void setTowerXCoordinateStore(ArrayList<Double> towerXCoordinateStore) {
        this.towerXCoordinateStore = towerXCoordinateStore;
    }

    public ArrayList<Double> getTowerWidthsStore() {
        return towerWidthsStore;
    }

    public void setTowerWidthsStore(ArrayList<Double> towerWidthsStore) {
        this.towerWidthsStore = towerWidthsStore;
    }

    public int getPlayingCharacterStore() {
        return playingCharacterStore;
    }

    public void setPlayingCharacterStore(int playingCharacterStore) {
        this.playingCharacterStore = playingCharacterStore;
    }

    public int getCherryCountStore() {
        return cherryCountStore;
    }

    public void setCherryCountStore(int cherryCountStore) {
        this.cherryCountStore = cherryCountStore;
    }

    public int getLifeCountStore() {
        return lifeCountStore;
    }

    public void setLifeCountStore(int lifeCountStore) {
        this.lifeCountStore = lifeCountStore;
    }

    public int getScoreCountStore() {
        return scoreCountStore;
    }

    public void setScoreCountStore(int scoreCountStore) {
        this.scoreCountStore = scoreCountStore;
    }

    public int getUiNumStore() {
        return uiNumStore;
    }

    public void setUiNumStore(int uiNumStore) {
        this.uiNumStore = uiNumStore;
    }
}
