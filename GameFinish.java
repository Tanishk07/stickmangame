package org.example;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameFinish extends VBox{
    private  int score;
    private  String sName;
    private Button back;
    private Button resume;
    private Button exit;
    GameFinish(double eWidth,double eHeight,double areaFactor,double xFactor,double yFactor,String sName,int score,int highestScore){
        this.sName=sName;
        this.score=score;
        Font.loadFont(getClass().getResourceAsStream("/Fonts/soupofjustice.ttf"), 14);


        Image exitBackgroundImage = new Image("Userinterfaces/bg.png");
        double aspectRatio = exitBackgroundImage.getHeight()/exitBackgroundImage.getWidth() ;
        double newWidth = eWidth;
        double newHeight = eWidth * aspectRatio;

        if (newHeight > eHeight) {
            newHeight = eHeight;
            newWidth = eHeight * aspectRatio;
        }

        BackgroundSize backgroundSize = new BackgroundSize(newWidth, newHeight, false, false, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(
                exitBackgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize
        );
        Background background = new Background(backgroundImage);

        this.setAlignment(Pos.CENTER);
        this.setBackground(background);
        this.setSpacing(areaFactor*10);

        this.setPrefHeight(newHeight);
        this.setPrefWidth(newWidth);
        this.setPadding(new Insets(10*xFactor,30*yFactor,0*xFactor,30*yFactor));


        DropShadow eWindowLevelShadow = new DropShadow();
        eWindowLevelShadow.setRadius(1);
        eWindowLevelShadow.setOffsetX(2);
        eWindowLevelShadow.setOffsetY(2);
        eWindowLevelShadow.setColor(Color.BLACK);

        DropShadow eWindowShadow = new DropShadow();
        eWindowShadow.setRadius(2);
        eWindowShadow.setOffsetX(3);
        eWindowShadow.setOffsetY(3);
        eWindowShadow.setColor(Color.BLACK);

        Image userImage = new Image("UserInterfaces/face.png");
        ImageView userImageView = new ImageView(userImage);
        userImageView.setFitWidth(50*xFactor); // Set the width based on your requirement
        userImageView.setFitHeight(50*yFactor);// Set the height based on your requirement
        userImageView.setPreserveRatio(true);
        userImageView.setSmooth(true);
        userImageView.setOpacity(0.7);

        Text userName = new Text(sName);
        userName.setStyle("-fx-font-family: 'Soup of Justice'; -fx-font-size: "+30*areaFactor+"; -fx-fill: #401C0BB3;"); // Set the font size based on your requirement

        Text userScore= new Text(String.valueOf(score));
        userScore.setStyle("-fx-font-family: 'Soup of Justice'; -fx-font-size: "+30*areaFactor+"; -fx-fill: #401C0BB3;");

        Text userHighestScore= new Text("HIGH SCORE -"+String.valueOf(highestScore));
        userHighestScore.setStyle("-fx-font-family: 'Soup of Justice'; -fx-font-size: "+40*areaFactor+"; -fx-fill: #A6AE9C;");
        userHighestScore.setEffect(eWindowLevelShadow);

        HBox hbox = new HBox(10*xFactor); // 10 is the spacing between nodes
        HBox hbox1 = new HBox(10*xFactor); // 10 is the spacing between nodes
        hbox1.setPadding(new Insets(10*xFactor,10*yFactor,10*xFactor,10*yFactor));
        hbox1.setStyle("-fx-background-color: #A6AE9C;");
        hbox1.setAlignment(Pos.CENTER_LEFT);
        hbox.setStyle("-fx-background-color: transparent;");



        Text backText = new Text("BACK");
        backText.setStyle("-fx-font-family: 'Soup of Justice';-fx-font-size: "+ 40*areaFactor+ ";-fx-fill: #A6AE9C;");
        backText.setEffect(eWindowLevelShadow);
        back = new Button();
        back.setGraphic(backText);
        back.setStyle("-fx-background-color: transparent;");
        back.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> back.setEffect(eWindowShadow));
        back.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> back.setEffect(null));


        Text exitText = new Text("EXIT");
        exitText.setStyle("-fx-font-family: 'Soup of Justice';-fx-font-size: "+ 40*areaFactor+ ";-fx-fill: #A6AE9C;");
        exitText.setEffect(eWindowLevelShadow);
        exit = new Button();
        exit.setGraphic(exitText);
        exit.setStyle("-fx-background-color: transparent;");
        exit.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> exit.setEffect(eWindowShadow));
        exit.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> exit.setEffect(null));


        Text resumeText = new Text("NEXT GAME");
        resumeText.setStyle("-fx-font-family: 'Soup of Justice';-fx-font-size: "+ 40*areaFactor+ ";-fx-fill: #A6AE9C;");
        resumeText.setEffect(eWindowLevelShadow);
        resume = new Button();
        resume.setGraphic(resumeText);
        resume.setStyle("-fx-background-color: transparent;");
        resume.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> resume.setEffect(eWindowShadow));
        resume.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> resume.setEffect(null));

        // Add buttons to the VBox

        hbox1.getChildren().addAll(createSpacer(10*xFactor),userImageView,createSpacer(20*xFactor), userName,createSpacer(20*xFactor),userScore);
        hbox.getChildren().addAll(createSpacer(80*xFactor),hbox1,createSpacer(80*xFactor));
        getChildren().addAll(userHighestScore,hbox,resume,back, exit);
    }
    public void setOnBackButtonPressed(EventHandler<ActionEvent> handler) {
        // Allow external classes to register event handlers for button1
        back.setOnAction(handler);
    }
    public void setOnResumeButtonPressed(EventHandler<ActionEvent> handler) {
        // Allow external classes to register event handlers for button1
        resume.setOnAction(handler);
    }
    public void setOnExitButtonPressed(EventHandler<ActionEvent> handler) {
        // Allow external classes to register event handlers for button1
        exit.setOnAction(handler);
    }

    private Region createSpacer(double width) {
        Region spacer = new Region();
        spacer.setPrefWidth(width);
        return spacer;
    }
}
