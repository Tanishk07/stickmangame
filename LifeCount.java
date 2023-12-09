package org.example;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class LifeCount extends HBox {
    private final ImageView firstHeartView;


    private final ImageView secondHeartView;

    private final ImageView thirdHeartView;


    private final Image fullHeartImage;

    public void setHeartCount(int heartCount) {
        this.heartCount = heartCount;
    }

    private final Image halfHeartImage;

    private final Image emptyHeartImage;


    private final Text textHeart;

    private int heartCount;

    public int getHeartCount() {
        return heartCount;
    }

    private int textContent;

    private final double areaFactor;

    private Timeline blinkTimeline;
    private Timeline textChangeTimeline;

    LifeCount(double xFactor,double yFactor,double areaFactor){

        this.setPadding(new Insets(0, 0, 0, 0));
        this.setSpacing(4);

        this.areaFactor=areaFactor;

        fullHeartImage = new Image("Lifes/hud_heartFull.png");
        halfHeartImage = new Image("Lifes/hud_heartHalf.png");
        emptyHeartImage = new Image("Lifes/hud_heartEmpty.png");


        firstHeartView = new ImageView(fullHeartImage);
        firstHeartView.setFitWidth(40*xFactor); // Set the width based on your requirement
        firstHeartView.setPreserveRatio(true);
        firstHeartView.setSmooth(true);


        secondHeartView = new ImageView(fullHeartImage);
        secondHeartView.setFitWidth(40*xFactor); // Set the width based on your requirement
        secondHeartView.setPreserveRatio(true);
        secondHeartView.setSmooth(true);


        thirdHeartView = new ImageView(fullHeartImage);
        thirdHeartView.setFitWidth(40*xFactor); // Set the width based on your requirement
        thirdHeartView.setPreserveRatio(true);
        thirdHeartView.setSmooth(true);

        heartCount=3;
        textContent=heartCount;

        textHeart= new Text("x "+String.valueOf(textContent));
        textHeart.setStyle("-fx-font-family: 'Soup of Justice'; -fx-font-size: "+20*areaFactor+"; -fx-fill: #401C0BB3;");

        this.getChildren().addAll(firstHeartView,secondHeartView,thirdHeartView,createSpacer(15*xFactor),textHeart);
        this.setAlignment(Pos.CENTER);

    }

    public void addHeart(){
        if(heartCount==0){
            addHeartTimeLine(firstHeartView,textHeart);
        }
        else if(heartCount==1){
            addHeartTimeLine(secondHeartView,textHeart);
        }
        else if(heartCount==2){
            addHeartTimeLine(thirdHeartView,textHeart);
        }

    }

    public void heartDisplay(){
        if(heartCount==0){
            firstHeartView.setImage(emptyHeartImage);
            secondHeartView.setImage(emptyHeartImage);
            thirdHeartView.setImage(emptyHeartImage);
        }
        else if(heartCount==1){
            firstHeartView.setImage(fullHeartImage);
            secondHeartView.setImage(emptyHeartImage);
            thirdHeartView.setImage(emptyHeartImage);
        }
        else if(heartCount==2){
            firstHeartView.setImage(fullHeartImage);
            secondHeartView.setImage(fullHeartImage);
            thirdHeartView.setImage(emptyHeartImage);
        }
        else if(heartCount==3){
            firstHeartView.setImage(fullHeartImage);
            secondHeartView.setImage(fullHeartImage);
            thirdHeartView.setImage(fullHeartImage);
        }
        else if(heartCount>=4){
            firstHeartView.setImage(fullHeartImage);
            secondHeartView.setImage(fullHeartImage);
            thirdHeartView.setImage(fullHeartImage);
        }
        else{
            firstHeartView.setImage(emptyHeartImage);
            secondHeartView.setImage(emptyHeartImage);
            thirdHeartView.setImage(emptyHeartImage);
        }
        textHeart.setText("x "+String.valueOf(heartCount));
        textHeart.setStyle("-fx-font-family: 'Soup of Justice'; -fx-font-size: "+20*areaFactor+"; -fx-fill: #401C0BB3;");


    }
    private void addHeartTimeLine(ImageView imageView ,Text text){
        heartCount=heartCount+1;
        imageView.setImage(halfHeartImage);

        blinkTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(imageView.imageProperty(), emptyHeartImage)),
                new KeyFrame(Duration.seconds(0.15), new KeyValue(imageView.imageProperty(), halfHeartImage)),
                new KeyFrame(Duration.seconds(0.30), new KeyValue(imageView.imageProperty(), emptyHeartImage))

        );
        blinkTimeline.setCycleCount(7);
        blinkTimeline.setOnFinished(event -> {
            imageView.setImage(fullHeartImage);
        });

        textChangeTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.0), event -> {
                    text.setStyle("-fx-font-family: 'Soup of Justice'; -fx-font-size: "+15*areaFactor+"; -fx-fill: #a52a2a;");
                    text.setText("+ "+String.valueOf('1'));
                }),
                new KeyFrame(Duration.seconds(0.30), event -> {
                    text.setStyle("-fx-font-family: 'Soup of Justice'; -fx-font-size: "+15*areaFactor+"; -fx-fill: #a52a2a;");
                    text.setText("+ "+String.valueOf('1'));
                })
        );
        textChangeTimeline.setOnFinished(event -> {
            text.setText("x "+String.valueOf(heartCount));
            text.setStyle("-fx-font-family: 'Soup of Justice'; -fx-font-size: "+20*areaFactor+"; -fx-fill: #401C0BB3;");
        });
        textChangeTimeline.setCycleCount(7);

        blinkTimeline.play();
        textChangeTimeline.play();

    }

    private void removeHeartTimeline(ImageView imageView ,Text text){
        heartCount=heartCount-1;
        imageView.setImage(halfHeartImage);

        Timeline blinkTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(imageView.imageProperty(), fullHeartImage)),
                new KeyFrame(Duration.seconds(0.15), new KeyValue(imageView.imageProperty(), halfHeartImage)),
                new KeyFrame(Duration.seconds(0.30), new KeyValue(imageView.imageProperty(), fullHeartImage))

        );
        blinkTimeline.setCycleCount(7);
        blinkTimeline.setOnFinished(event -> {
            imageView.setImage(emptyHeartImage);
        });

        Timeline textChangeTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.0), event -> {
                    text.setStyle("-fx-font-family: 'Soup of Justice'; -fx-font-size: "+15*areaFactor+"; -fx-fill: #a52a2a;");
                    text.setText("- "+String.valueOf('1'));
                }),
                new KeyFrame(Duration.seconds(0.30), event -> {
                    text.setStyle("-fx-font-family: 'Soup of Justice'; -fx-font-size: "+15*areaFactor+"; -fx-fill: #a52a2a;");
                    text.setText("- "+String.valueOf('1'));
                })
        );
        textChangeTimeline.setOnFinished(event -> {
            text.setText("x "+String.valueOf(heartCount));
            text.setStyle("-fx-font-family: 'Soup of Justice'; -fx-font-size: "+20*areaFactor+"; -fx-fill: #401C0BB3;");
        });
        textChangeTimeline.setCycleCount(7);

        blinkTimeline.play();
        textChangeTimeline.play();

    }
    public void removeHeart(){
        if(heartCount==1){
            removeHeartTimeline(firstHeartView,textHeart);
        }
        else if(heartCount==2){
            removeHeartTimeline(secondHeartView,textHeart);
        }
        else if(heartCount==3){
            removeHeartTimeline(thirdHeartView,textHeart);
        }
    }


    private Region createSpacer(double width) {
        Region spacer = new Region();
        spacer.setPrefWidth(width);
        return spacer;
    }

    public void pauseBlinkAnimation(){
        blinkTimeline.pause();
    }
    public void pauseTextChangeAnimation(){
        textChangeTimeline.pause();
    }
    public void playBlinkAnimation(){
        blinkTimeline.play();
    }
    public void playTextChangeAnimation(){
        textChangeTimeline.play();
    }

}