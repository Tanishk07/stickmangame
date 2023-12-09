package org.example;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;



public class InGameScoreCount extends Region {
    private Text scoreText;
    private int scoreCount;

    public int getScoreCount() {
        return scoreCount;
    }

    public void setScoreCount(int scoreCount) {
        this.scoreCount = scoreCount;
        scoreText.setText(String.valueOf(scoreCount));
    }

    private double areaFactor;

    InGameScoreCount(double xFactor,double yFactor,double areaFactor,double width,double height){
        scoreCount=0;
        this.areaFactor=areaFactor;
        this.setWidth(width);
        this.setHeight(height);

        ImageView backgroundImageView = new ImageView(new Image("UserInterfaces/tableOld.png"));
        backgroundImageView.setPreserveRatio(true);
        backgroundImageView.fitWidthProperty().bind(widthProperty());
        backgroundImageView.fitHeightProperty().bind(heightProperty());
        backgroundImageView.setOpacity(0.8);


        scoreText=new Text( String.valueOf(scoreCount));
        scoreText.setStyle("-fx-font-family: 'Soup of Justice'; -fx-font-size: "+30*areaFactor+"; -fx-fill: #e1e6e1;");
        scoreText.setLayoutX(width/2-22*xFactor);
        scoreText.setLayoutY(height/2-7*yFactor);



        this.getChildren().addAll(backgroundImageView,scoreText);
    }

    public void addScore(){
        scoreCount=scoreCount+100;
        scoreText.setText(String.valueOf(scoreCount));
    }
    public void addHalfScore(){
        scoreCount=scoreCount+50;
        scoreText.setText(String.valueOf(scoreCount));
    }


}
