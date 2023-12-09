package org.example;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class StartCharacterAnimation extends Pane {
    private ImageView imageView = new ImageView();
    private int currentImageIndex = 0;
    private Image[] imagesChar;
    StartCharacterAnimation(double width,double height){
        this.getChildren().add(imageView);
        imagesChar=new Image[45];
        for(int i=1;i<=15;i++){
            imagesChar[i-1]=new Image("Character/Boy_Character/Jump ("+i+").png");
            //System.out.println("Character/flatboy/Jump ("+i+").png");
        }
        for(int i=1;i<=15;i++){
            imagesChar[14+i]=new Image("Character/Boy_Character/Run ("+i+").png");
            //System.out.println("Character/flatboy/Run ("+i+").png");
        }
        for(int i=1;i<=15;i++){
            imagesChar[29+i]=new Image("Character/Boy_Character/Walk ("+i+").png");
            //System.out.println("Character/flatboy/Walk ("+i+").png");
        }


        Timeline timeline = createImageAnimation(width,height);
        timeline.play();
    }
    private Timeline createImageAnimation(double width,double height) {
        Duration duration = Duration.millis(70); // Set the duration for each image

        Timeline timeline = new Timeline(
                new KeyFrame(duration, e -> {
                    // Update the image in the ImageView
                    imageView.setImage(imagesChar[currentImageIndex]);
                    imageView.setFitWidth(width);
                    imageView.setPreserveRatio(true);
                    imageView.setSmooth(true);
                    currentImageIndex = (currentImageIndex + 1) % imagesChar.length;
                }, new KeyValue(imageView.opacityProperty(), 1.0))
        );

        timeline.setCycleCount(Timeline.INDEFINITE); // Repeat the animation indefinitely
        return timeline;
    }
}
