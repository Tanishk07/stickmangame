package org.example;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class InGameBackground extends Region {
    private double bWidth;
    private double bHeight;
    private double areaFactor;
    private double xFactor;
    private double yFactor;
    private int type;

    private ArrayList<ImageView> forestList;
    private ArrayList<Double> forestPosList;

    private ArrayList<ImageView> grassList;
    private ArrayList<Double> grassPosList;

    private ArrayList<ImageView> desertList;
    private ArrayList<Double> desertPosList;

    private ArrayList<ImageView> fallList;
    private ArrayList<Double> fallPosList;
    InGameBackground(double bWidth,double bHeight,double areaFactor,double xFactor,double yFactor,int type){
        Font.loadFont(getClass().getResourceAsStream("/Fonts/soupofjustice.ttf"), 14);

        this.bWidth=bWidth;
        this.bHeight=bHeight;
        this.areaFactor=areaFactor;
        this.xFactor=xFactor;
        this.yFactor=yFactor;
        this.type=type;

        if(type==1){
            forestBackground();
        }
        else if(type==2){
            desertBackground();
        }
        else if(type==3){
            fallBackground();

        }
        else if(type==4){
            grassBackground();
        }
    }
    void forestBackground() {
        Image backgroundForest = new Image("Background/backgroundColorForest.png");

        ImageView backgroundForestView = new ImageView(backgroundForest);
        backgroundForestView.setFitHeight(bHeight);
        backgroundForestView.setPreserveRatio(true);

        ImageView backgroundForestView1 = new ImageView(backgroundForest);
        backgroundForestView1.setFitHeight(bHeight);
        backgroundForestView1.setPreserveRatio(true);

        ImageView backgroundForestView2 = new ImageView(backgroundForest);
        backgroundForestView2.setFitHeight(bHeight);
        backgroundForestView2.setPreserveRatio(true);

        double aspectRatio = backgroundForest.getWidth() / backgroundForest.getHeight();
        double widthOfFirstImage = backgroundForestView.getFitHeight() * aspectRatio;

        backgroundForestView.setLayoutX(0);
        backgroundForestView.setLayoutY(0);

        backgroundForestView1.setLayoutX(widthOfFirstImage);
        backgroundForestView1.setLayoutY(0);

        backgroundForestView2.setLayoutX(2*widthOfFirstImage);
        backgroundForestView2.setLayoutY(0);

        this.forestList = new ArrayList<>();
        forestList.add(backgroundForestView);
        forestList.add(backgroundForestView1);
        forestList.add(backgroundForestView2);

        this.forestPosList=new ArrayList<>();
        forestPosList.add(0.0);
        forestPosList.add(widthOfFirstImage);
        forestPosList.add(2*widthOfFirstImage);


        this.getChildren().addAll(backgroundForestView, backgroundForestView1, backgroundForestView2);
    }


    void desertBackground(){
        Image backgroundDesert = new Image("Background/backgroundColorDesert.png");

        ImageView backgroundDesertView = new ImageView(backgroundDesert);
        backgroundDesertView.setFitHeight(bHeight);
        backgroundDesertView.setPreserveRatio(true);

        ImageView backgroundDesertView1 = new ImageView(backgroundDesert);
        backgroundDesertView1.setFitHeight(bHeight);
        backgroundDesertView1.setPreserveRatio(true);

        ImageView backgroundDesertView2 = new ImageView(backgroundDesert);
        backgroundDesertView2.setFitHeight(bHeight);
        backgroundDesertView2.setPreserveRatio(true);

        double aspectRatio = backgroundDesert.getWidth() / backgroundDesert.getHeight();
        double widthOfFirstImage = backgroundDesertView.getFitHeight() * aspectRatio;

        backgroundDesertView.setLayoutX(0);
        backgroundDesertView.setLayoutY(0);

        backgroundDesertView1.setLayoutX(widthOfFirstImage);
        backgroundDesertView1.setLayoutY(0);

        backgroundDesertView2.setLayoutX(2*widthOfFirstImage);
        backgroundDesertView2.setLayoutY(0);

        this.desertList = new ArrayList<>();
        desertList.add(backgroundDesertView);
        desertList.add(backgroundDesertView1);
        desertList.add(backgroundDesertView2);

        this.desertPosList=new ArrayList<>();
        desertPosList.add(0.0);
        desertPosList.add(widthOfFirstImage);
        desertPosList.add(2*widthOfFirstImage);


        this.getChildren().addAll(backgroundDesertView, backgroundDesertView1, backgroundDesertView2);
    }
    void fallBackground(){
        Image backgroundFall= new Image("Background/backgroundColorFall.png");

        ImageView backgroundFallView = new ImageView(backgroundFall);
        backgroundFallView.setFitHeight(bHeight);
        backgroundFallView.setPreserveRatio(true);

        ImageView backgroundFallView1 = new ImageView(backgroundFall);
        backgroundFallView1.setFitHeight(bHeight);
        backgroundFallView1.setPreserveRatio(true);

        ImageView backgroundFallView2 = new ImageView(backgroundFall);
        backgroundFallView2.setFitHeight(bHeight);
        backgroundFallView2.setPreserveRatio(true);

        double aspectRatio = backgroundFall.getWidth() / backgroundFall.getHeight();
        double widthOfFirstImage = backgroundFallView.getFitHeight() * aspectRatio;

        backgroundFallView.setLayoutX(0);
        backgroundFallView.setLayoutY(0);

        backgroundFallView1.setLayoutX(widthOfFirstImage);
        backgroundFallView1.setLayoutY(0);

        backgroundFallView2.setLayoutX(2*widthOfFirstImage);
        backgroundFallView2.setLayoutY(0);

        this.fallList = new ArrayList<>();
        fallList.add(backgroundFallView);
        fallList.add(backgroundFallView1);
        fallList.add(backgroundFallView2);

        this.fallPosList=new ArrayList<>();
        fallPosList.add(0.0);
        fallPosList.add(widthOfFirstImage);
        fallPosList.add(2*widthOfFirstImage);


        this.getChildren().addAll(backgroundFallView, backgroundFallView1, backgroundFallView2);
    }
    void grassBackground(){
        Image backgroundGrass = new Image("Background/backgroundColorGrass.png");

        ImageView backgroundGrassView = new ImageView(backgroundGrass);
        backgroundGrassView.setFitHeight(bHeight);
        backgroundGrassView.setPreserveRatio(true);

        ImageView backgroundGrassView1 = new ImageView(backgroundGrass);
        backgroundGrassView1.setFitHeight(bHeight);
        backgroundGrassView1.setPreserveRatio(true);

        ImageView backgroundGrassView2 = new ImageView(backgroundGrass);
        backgroundGrassView2.setFitHeight(bHeight);
        backgroundGrassView2.setPreserveRatio(true);

        double aspectRatio = backgroundGrass.getWidth() / backgroundGrass.getHeight();
        double widthOfFirstImage = backgroundGrassView.getFitHeight() * aspectRatio;

        backgroundGrassView.setLayoutX(0);
        backgroundGrassView.setLayoutY(0);

        backgroundGrassView1.setLayoutX(widthOfFirstImage);
        backgroundGrassView1.setLayoutY(0);

        backgroundGrassView2.setLayoutX(2*widthOfFirstImage);
        backgroundGrassView2.setLayoutY(0);

        this.grassList = new ArrayList<>();
        grassList.add(backgroundGrassView);
        grassList.add(backgroundGrassView1);
        grassList.add(backgroundGrassView2);

        this.grassPosList=new ArrayList<>();
        grassPosList.add(0.0);
        grassPosList.add(widthOfFirstImage);
        grassPosList.add(2*widthOfFirstImage);


        this.getChildren().addAll(backgroundGrassView, backgroundGrassView1, backgroundGrassView2);
    }

    void backgroundMove(double time, int type){

        ArrayList<ImageView> backgroundListImageView;
        ArrayList<Double> backgroundListPos;

        if(type==1){
          backgroundListImageView=forestList;
          backgroundListPos=forestPosList;
        }
        else if(type==2){
            backgroundListImageView=desertList;
            backgroundListPos=desertPosList;
        }
        else if(type == 3){
            backgroundListImageView=fallList;
            backgroundListPos=fallPosList;
        }
        else if(type == 4){
            backgroundListImageView=grassList;
            backgroundListPos=grassPosList;
        }
        else{
            backgroundListImageView=forestList;
            backgroundListPos=forestPosList;
        }

        double dis = time;
        double imageWidth = backgroundListPos.get(1)-backgroundListPos.get(0);

        for(int i=0;i<backgroundListPos.size();i++){
            backgroundListPos.set(i,backgroundListPos.get(i)-dis);
        }

        if(backgroundListPos.get(0)>-imageWidth){
            updateListImageView(backgroundListImageView,backgroundListPos);
        }
        else{
            this.getChildren().remove(backgroundListImageView.get(0));
            this.getChildren().add(backgroundListImageView.get(0));

            double temp1=backgroundListPos.get(1);
            double temp2=backgroundListPos.get(2);

            backgroundListPos.set(0,temp1);
            backgroundListPos.set(1,temp2);
            backgroundListPos.set(2,temp2+imageWidth);

            updateListImageView(backgroundListImageView,backgroundListPos);


        }
    }
    void updateListImageView(ArrayList<ImageView> backgroundListImageView,ArrayList<Double> backgroundListPos){
        for(int i=0;i<backgroundListImageView.size();i++){
            backgroundListImageView.get(i).setLayoutX(backgroundListPos.get(i));
        }
    }
}
