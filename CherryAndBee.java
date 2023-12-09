package org.example;

import javafx.geometry.Bounds;

public class CherryAndBee {
    private double cherryXCord;
    private double beeXCord;

    private boolean cherryStatus;
    private boolean beeStatus;

    private int cherryYAxis;
    private int beeYAxis;

    private double beeYCord;
    private double cherryYCord;

    private Bounds cherryBound;
    private Bounds beeBound;

    public CherryAndBee(double cherryXCord, double beeXCord, boolean cherryStatus, boolean beeStatus, int cherryYAxis, int beeYAxis, double beeYCord, double cherryYCord, Bounds cherryBound, Bounds beeBound) {
        this.cherryXCord = cherryXCord;
        this.beeXCord = beeXCord;
        this.cherryStatus = cherryStatus;
        this.beeStatus = beeStatus;
        this.cherryYAxis = cherryYAxis;
        this.beeYAxis = beeYAxis;
        this.beeYCord = beeYCord;
        this.cherryYCord = cherryYCord;
        this.cherryBound = cherryBound;
        this.beeBound = beeBound;
    }

    public void CherryAndBeeSet(double cherryXCord, double beeXCord, boolean cherryStatus, boolean beeStatus, int cherryYAxis, int beeYAxis, double beeYCord, double cherryYCord, Bounds cherryBound, Bounds beeBound) {
        this.cherryXCord = cherryXCord;
        this.beeXCord = beeXCord;
        this.cherryStatus = cherryStatus;
        this.beeStatus = beeStatus;
        this.cherryYAxis = cherryYAxis;
        this.beeYAxis = beeYAxis;
        this.beeYCord = beeYCord;
        this.cherryYCord = cherryYCord;
        this.cherryBound = cherryBound;
        this.beeBound = beeBound;
    }

    public double getCherryXCord() {
        return cherryXCord;
    }

    public void setCherryXCord(double cherryXCord) {
        this.cherryXCord = cherryXCord;
    }

    public double getBeeXCord() {
        return beeXCord;
    }

    public void setBeeXCord(double beeXCord) {
        this.beeXCord = beeXCord;
    }

    public boolean isCherryStatus() {
        return cherryStatus;
    }

    public void setCherryStatus(boolean cherryStatus) {
        this.cherryStatus = cherryStatus;
    }

    public boolean isBeeStatus() {
        return beeStatus;
    }

    public void setBeeStatus(boolean beeStatus) {
        this.beeStatus = beeStatus;
    }

    public int getCherryYAxis() {
        return cherryYAxis;
    }

    public void setCherryYAxis(int cherryYAxis) {
        this.cherryYAxis = cherryYAxis;
    }

    public int getBeeYAxis() {
        return beeYAxis;
    }

    public void setBeeYAxis(int beeYAxis) {
        this.beeYAxis = beeYAxis;
    }

    public double getBeeYCord() {
        return beeYCord;
    }

    public void setBeeYCord(double beeYCord) {
        this.beeYCord = beeYCord;
    }

    public double getCherryYCord() {
        return cherryYCord;
    }

    public void setCherryYCord(double cherryYCord) {
        this.cherryYCord = cherryYCord;
    }

    public Bounds getCherryBound() {
        return cherryBound;
    }

    public void setCherryBound(Bounds cherryBound) {
        this.cherryBound = cherryBound;
    }

    public Bounds getBeeBound() {
        return beeBound;
    }

    public void setBeeBound(Bounds beeBound) {
        this.beeBound = beeBound;
    }
}