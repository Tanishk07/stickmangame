package org.example;

import java.util.ArrayList;

public class InGameUIMap {

    private int currentPillar;
    private int currentBackground;
    private int currentSticks;

    private ArrayList<Integer> pillar;
    private ArrayList<Integer> background;
    private ArrayList<Integer> sticks;
    InGameUIMap(){

        pillar=new ArrayList<Integer>();
        background=new ArrayList<Integer>();
        sticks=new ArrayList<Integer>();

        pillar.add(0);
        pillar.add(1);
        pillar.add(2);
        pillar.add(3);
        pillar.add(4);
        pillar.add(5);
        pillar.add(6);
        pillar.add(7);

        background.add(1);
        background.add(2);
        background.add(3);
        background.add(4);
        background.add(1);
        background.add(2);
        background.add(3);
        background.add(4);

        sticks.add(0);
        sticks.add(1);
        sticks.add(2);
        sticks.add(0);
        sticks.add(1);
        sticks.add(2);
        sticks.add(0);
        sticks.add(1);


    }
    public void getUi(int num){
        currentPillar=pillar.get(num);
        currentBackground=background.get(num);
        currentSticks=sticks.get(num);
    }

    public int getCurrentPillar(){
        return currentPillar;
    }

    public int getCurrentBackground(){
        return currentBackground;
    }

    public int getCurrentSticks(){
        return currentSticks;
    }
}
