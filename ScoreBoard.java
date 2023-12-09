package org.example;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.layout.Region;
import javafx.geometry.Pos;
import javafx.scene.text.Font;

public class ScoreBoard extends ScrollPane {

    private VBox vbox;

    public ScoreBoard(double xPos, double yPos, double sWidth, double sHeight,double areaFactor,double xFactor,double yFactor) {

        Font.loadFont(getClass().getResourceAsStream("/Fonts/soupofjustice.ttf"), 14);
        this.setLayoutX(xPos);
        this.setLayoutY(yPos);
        this.setWidth(sWidth);
        this.setHeight(sHeight);


        vbox = new VBox(0); // 10 is the spacing between nodes
        vbox.setPadding(new Insets(10*xFactor,30*yFactor,0*xFactor,30*yFactor));
        vbox.getChildren().addAll();
        vbox.setPrefWidth(sWidth);
        vbox.setPrefHeight(sHeight);
        vbox.setStyle("-fx-background-color: '#EAE8E780';") ;


        this.setContent(vbox);
        this.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        this.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        this.setStyle("-fx-background-color: transparent;-fx-background: transparent;");



    }

    public void addUserScore(String s, int pos,int score,double areaFactor,double xFactor,double yFactor) {

        Image userImage = new Image("UserInterfaces/face.png");
        ImageView userImageView = new ImageView(userImage);
        userImageView.setFitWidth(50*xFactor); // Set the width based on your requirement
        userImageView.setFitHeight(50*yFactor);// Set the height based on your requirement
        userImageView.setPreserveRatio(true);
        userImageView.setSmooth(true);
        userImageView.setOpacity(0.7);

        Image goldMedal = new Image("Medals/gold.png");
        ImageView goldMedalView = new ImageView(goldMedal);
        goldMedalView.setFitWidth(40*xFactor); // Set the width based on your requirement
        goldMedalView.setPreserveRatio(true);
        goldMedalView.setSmooth(true);
        goldMedalView.setOpacity(0.7);

        Image silverMedal = new Image("Medals/silver.png");
        ImageView silverMedalView = new ImageView(silverMedal);
        silverMedalView.setFitWidth(40*xFactor); // Set the width based on your requirement
        silverMedalView.setPreserveRatio(true);
        silverMedalView.setSmooth(true);
        silverMedalView.setOpacity(0.7);

        Image bronzeMedal = new Image("Medals/bronze.png");
        ImageView bronzeMedalView = new ImageView(bronzeMedal);
        bronzeMedalView.setFitWidth(40*xFactor); // Set the width based on your requirement
        bronzeMedalView.setPreserveRatio(true);
        bronzeMedalView.setSmooth(true);
        bronzeMedalView.setOpacity(0.7);


        Image noMedal = new Image("Medals/bronze.png");
        ImageView noMedalView = new ImageView(noMedal);
        noMedalView.setFitWidth(40*xFactor); // Set the width based on your requirement
        noMedalView.setPreserveRatio(true);
        noMedalView.setSmooth(true);
        noMedalView.setOpacity(0.1);

        Text userName = new Text(s);
        userName.setStyle("-fx-font-family: 'Soup of Justice'; -fx-font-size: "+25*areaFactor+"; -fx-fill: #401C0BB3;"); // Set the font size based on your requirement


        Text userNum= new Text(String.valueOf(pos)+".");
        userNum.setStyle("-fx-font-family: 'Soup of Justice'; -fx-font-size: "+25*areaFactor+"; -fx-fill: #401C0BB3;");


        Text userScore= new Text(String.valueOf(score));
        userScore.setStyle("-fx-font-family: 'Soup of Justice'; -fx-font-size: "+25*areaFactor+"; -fx-fill: #401C0BB3;");

        HBox hbox = new HBox(10*xFactor); // 10 is the spacing between nodes
        hbox.setPadding(new Insets(10*xFactor,10*yFactor,10*xFactor,10*yFactor));
        hbox.setStyle("-fx-background-color: transparent;");
        hbox.setAlignment(Pos.CENTER_LEFT);


        if (pos == 1) {
            hbox.getChildren().addAll(userImageView,createSpacer(10*xFactor),userNum,createSpacer(50*xFactor), userName,createSpacer(50*xFactor),userScore,createSpacer(70*xFactor), goldMedalView);
        } else if (pos == 2) {
            hbox.getChildren().addAll(userImageView,createSpacer(10*xFactor),userNum,createSpacer(50*xFactor), userName,createSpacer(50*xFactor),userScore,createSpacer(70*xFactor), silverMedalView);
        } else if (pos == 3) {
            hbox.getChildren().addAll(userImageView,createSpacer(10*xFactor),userNum,createSpacer(50*xFactor), userName,createSpacer(50*xFactor),userScore,createSpacer(70*xFactor), bronzeMedalView);
        } else if (pos > 3) {
            hbox.getChildren().addAll(userImageView,createSpacer(10*xFactor),userNum,createSpacer(50*xFactor),userName,createSpacer(50*xFactor),userScore,createSpacer(70*xFactor),noMedalView);
        }

        vbox.getChildren().add(hbox);
    }

    private Region createSpacer(double width) {
        Region spacer = new Region();
        spacer.setPrefWidth(width);
        return spacer;
    }

    public void clear(){
        vbox.getChildren().clear();
    }
}
