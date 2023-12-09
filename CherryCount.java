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

public class CherryCount extends HBox {
    private ImageView firstCherryView;

    private Image fullCherryImage;


    private Text textCherry;

    private int cherryCount;
    private double areaFactor;
    private Timeline blinkTimeline;
    private Timeline textChangeTimeline;

    public void setCherryCount(int cherryCount) {
        this.cherryCount = cherryCount;
    }

    public int getCherryCount() {
        return cherryCount;
    }

    CherryCount(double xFactor, double yFactor, double areaFactor){

        this.setPadding(new Insets(0, 0, 0, 0));
        this.setSpacing(4);

        this.areaFactor=areaFactor;

        fullCherryImage = new Image("Rewards/CherryBoard.png");



        firstCherryView = new ImageView(fullCherryImage);
        firstCherryView.setFitWidth(40*xFactor); // Set the width based on your requirement
        firstCherryView.setPreserveRatio(true);
        firstCherryView.setSmooth(true);



        cherryCount=0;


        textCherry= new Text("x "+String.valueOf(cherryCount));
        textCherry.setStyle("-fx-font-family: 'Soup of Justice'; -fx-font-size: "+20*areaFactor+"; -fx-fill: #401C0BB3;");

        this.getChildren().addAll(firstCherryView,createSpacer(15*xFactor),textCherry);
        this.setAlignment(Pos.CENTER);

    }
    public void cherryDisplay(){
        textCherry.setText("x "+String.valueOf(cherryCount));
        textCherry.setStyle("-fx-font-family: 'Soup of Justice'; -fx-font-size: "+20*areaFactor+"; -fx-fill: #401C0BB3;");
    }
    public void addCherry(){
            addCherryTimeLine(firstCherryView,textCherry);
    }
    public void removeCherry(){
        removeCherryTimeline(firstCherryView,textCherry);
    }

    private void addCherryTimeLine(ImageView imageView ,Text text){
        cherryCount=cherryCount+1;

        imageView.setOpacity(1.0);

        blinkTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(imageView.opacityProperty(), 0.0)),
                new KeyFrame(Duration.seconds(0.30), new KeyValue(imageView.opacityProperty(), 1.0)),
                new KeyFrame(Duration.seconds(0.60), new KeyValue(imageView.opacityProperty(), 0.0))

        );
        blinkTimeline.setCycleCount(5);
        blinkTimeline.setOnFinished(event -> {
            imageView.setOpacity(1.0);
        });

        textChangeTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.30), event -> {
                    text.setStyle("-fx-font-family: 'Soup of Justice'; -fx-font-size: "+15*areaFactor+"; -fx-fill: #a52a2a;");
                    text.setText("+ "+String.valueOf('1'));
                }),
                new KeyFrame(Duration.seconds(0.60), event -> {
                    text.setStyle("-fx-font-family: 'Soup of Justice'; -fx-font-size: "+15*areaFactor+"; -fx-fill: #a52a2a;");
                    text.setText("+ "+String.valueOf('1'));
                })
        );
        textChangeTimeline.setOnFinished(event -> {
            text.setText("x "+String.valueOf(cherryCount));
            text.setStyle("-fx-font-family: 'Soup of Justice'; -fx-font-size: "+20*areaFactor+"; -fx-fill: #401C0BB3;");
        });
        textChangeTimeline.setCycleCount(5);

        blinkTimeline.play();
        textChangeTimeline.play();

    }

    private void removeCherryTimeline(ImageView imageView ,Text text){
        cherryCount=cherryCount-5;
        imageView.setOpacity(1.0);

        Timeline blinkTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(imageView.opacityProperty(), 0.0)),
                new KeyFrame(Duration.seconds(0.30), new KeyValue(imageView.opacityProperty(), 1.0)),
                new KeyFrame(Duration.seconds(0.60), new KeyValue(imageView.opacityProperty(), 0.0))

        );
        blinkTimeline.setCycleCount(5);
        blinkTimeline.setOnFinished(event -> {
            imageView.setOpacity(1.0);
        });

        Timeline textChangeTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.30), event -> {
                    text.setStyle("-fx-font-family: 'Soup of Justice'; -fx-font-size: "+15*areaFactor+"; -fx-fill: #a52a2a;");
                    text.setText("- "+String.valueOf('5'));
                }),
                new KeyFrame(Duration.seconds(0.60), event -> {
                    text.setStyle("-fx-font-family: 'Soup of Justice'; -fx-font-size: "+15*areaFactor+"; -fx-fill: #a52a2a;");
                    text.setText("- "+String.valueOf('5'));
                })
        );
        textChangeTimeline.setOnFinished(event -> {

            text.setText("x "+String.valueOf(cherryCount));
            text.setStyle("-fx-font-family: 'Soup of Justice'; -fx-font-size: "+20*areaFactor+"; -fx-fill: #401C0BB3;");
        });
        textChangeTimeline.setCycleCount(5);

        blinkTimeline.play();
        textChangeTimeline.play();

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
