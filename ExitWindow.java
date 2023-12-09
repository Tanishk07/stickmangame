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

public class ExitWindow extends VBox{
    private Button back;
    private Button resume;
    private Button exit;
    ExitWindow(double eWidth,double eHeight,double areaFactor,double xFactor,double yFactor){
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


        Text backText = new Text("PAUSE");
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


        Text resumeText = new Text("RESUME");
        resumeText.setStyle("-fx-font-family: 'Soup of Justice';-fx-font-size: "+ 40*areaFactor+ ";-fx-fill: #A6AE9C;");
        resumeText.setEffect(eWindowLevelShadow);
        resume = new Button();
        resume.setGraphic(resumeText);
        resume.setStyle("-fx-background-color: transparent;");
        resume.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> resume.setEffect(eWindowShadow));
        resume.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> resume.setEffect(null));

        // Add buttons to the VBox
        getChildren().addAll(back, exit, resume);

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
}
