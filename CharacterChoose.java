package org.example;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class CharacterChoose extends VBox {
    Button boyButton;
    Button girlButton;
    Button hatButton;
    CharacterChoose(double eWidth,double eHeight ,double xFactor,double yFactor,double areaFactor){

        Font.loadFont(getClass().getResourceAsStream("/Fonts/soupofjustice.ttf"), 14);


        Image characterChooseImage = new Image("Userinterfaces/bg_rotated_flipped.png");

        BackgroundSize backgroundSize = new BackgroundSize(eWidth, eHeight, false, false, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(
                characterChooseImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize
        );
        Background background = new Background(backgroundImage);


        this.setPrefWidth(eWidth);
        this.setPrefHeight(eHeight);
        this.setAlignment(Pos.CENTER);
        this.setBackground(background);
        this.setPadding(new Insets(20,20,0,0));
        this.setOpacity(1);

        HBox horizontalContent=new HBox(10*areaFactor);
        horizontalContent.setAlignment(Pos.CENTER);
        Label headerLabel = new Label("CHOOSE YOUR CHARACTER .....");
        headerLabel.setStyle("-fx-font-size: "+40*areaFactor+";" +
                "-fx-font-family: 'Soup of Justice';" +
                "-fx-text-fill: #FBEEE6 ;");



        DropShadow characteChooseShadow = new DropShadow();
        characteChooseShadow.setRadius(1);
        characteChooseShadow.setOffsetX(2);
        characteChooseShadow.setOffsetY(2);
        characteChooseShadow.setColor(Color.BLACK);

        DropShadow characterChooseWindowShadow = new DropShadow();
        characterChooseWindowShadow.setRadius(2);
        characterChooseWindowShadow.setOffsetX(3);
        characterChooseWindowShadow.setOffsetY(3);
        characterChooseWindowShadow.setColor(Color.BLACK);

        VBox boyVbox=new VBox();
        boyVbox.setPadding(new Insets(20, 0, 20, 0));
        Image boyImage = new Image("Character/Boy_Character/Idle (1).png");
        ImageView boyImageView = new ImageView(boyImage);
        boyImageView.setFitWidth(180*xFactor); // Set the width based on your requirement
        boyImageView.setPreserveRatio(true);
        boyImageView.setSmooth(true);
        boyButton = new Button();
        boyButton.setGraphic(boyImageView);
        boyButton.setStyle("-fx-background-color: transparent;");
        boyButton.setEffect(characterChooseWindowShadow);
        boyButton.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> boyButton.setEffect(characteChooseShadow));
        boyButton.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> boyButton.setEffect(null));
        Label boyLabel = new Label("BOY");
        boyLabel.setStyle("-fx-font-size: "+30*areaFactor+";" +
                "-fx-font-family: 'Soup of Justice';" +
                "-fx-text-fill: #EAEDED ;");
        HBox fix1=new HBox(0);
        fix1.getChildren().addAll(createSpacer(15*areaFactor),boyLabel,createSpacer(10*areaFactor));
        boyVbox.setAlignment(Pos.CENTER);
        boyVbox.setSpacing(0);
        boyVbox.getChildren().addAll(boyButton,fix1);


        VBox girlVbox=new VBox();
        Image girlImage = new Image("Character/Girl_Character/Idle (1).png");
        ImageView girlImageView = new ImageView(girlImage);
        girlImageView.setFitWidth(120*xFactor); // Set the width based on your requirement
        girlImageView.setPreserveRatio(true);
        girlImageView.setSmooth(true);
        girlButton = new Button();
        girlButton.setGraphic(girlImageView);
        girlButton.setStyle("-fx-background-color: transparent;");
        girlButton.setEffect(characterChooseWindowShadow);
        girlButton.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> girlButton.setEffect(characteChooseShadow));
        girlButton.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> girlButton.setEffect(null));
        Label girlLabel = new Label("GIRL");
        girlLabel.setStyle("-fx-font-size: "+30*areaFactor+";" +
                "-fx-font-family: 'Soup of Justice';" +
                "-fx-text-fill: #EAEDED ;");
        HBox fix2=new HBox(0);
        fix2.getChildren().addAll(createSpacer(50*areaFactor),girlLabel,createSpacer(10*areaFactor));
        girlVbox.setAlignment(Pos.CENTER);
        girlVbox.getChildren().addAll(girlButton,fix2);

        VBox hatVbox=new VBox();
        Image hatImage = new Image("Character/Hat_Character/Idle (1).png");
        ImageView hatImageView = new ImageView(hatImage);
        hatImageView.setFitWidth(180*xFactor); // Set the width based on your requirement
        hatImageView.setPreserveRatio(true);
        hatImageView.setSmooth(true);
        hatButton = new Button();
        hatButton.setGraphic(hatImageView);
        hatButton.setStyle("-fx-background-color: transparent;");
        hatButton.setEffect(characterChooseWindowShadow);
        hatButton.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> hatButton.setEffect(characteChooseShadow));
        hatButton.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> hatButton.setEffect(null));
        Label hatLabel = new Label("HAT BOY");
        hatLabel.setStyle("-fx-font-size: "+30*areaFactor+";" +
                "-fx-font-family: 'Soup of Justice';" +
                "-fx-text-fill: #EAEDED ;");
        HBox fix3=new HBox(0);
        fix3.getChildren().addAll(createSpacer(70*areaFactor),hatLabel,createSpacer(10*areaFactor));
        hatVbox.setAlignment(Pos.CENTER);
        hatVbox.getChildren().addAll(hatButton,fix3);

        horizontalContent.setSpacing(20*areaFactor);
        horizontalContent.getChildren().addAll(boyVbox,girlVbox,hatVbox);

        this.getChildren().addAll(headerLabel,horizontalContent);
    }

    public void setOnBoyButtonPressed(EventHandler<ActionEvent> handler) {
        // Allow external classes to register event handlers for button1
        boyButton.setOnAction(handler);
    }
    public void setOnGirlButtonPressed(EventHandler<ActionEvent> handler) {
        // Allow external classes to register event handlers for button1
        girlButton.setOnAction(handler);
    }
    public void setOnHatButtonPressed(EventHandler<ActionEvent> handler) {
        // Allow external classes to register event handlers for button1
        hatButton.setOnAction(handler);
    }
    private Region createSpacer(double width) {
        Region spacer = new Region();
        spacer.setPrefWidth(width);
        return spacer;
    }
}
