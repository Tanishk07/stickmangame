package org.example;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.ArrayList;


public class Tower extends Canvas implements Serializable {
    private final int type;
    private double xFactor;
    private double yFactor;
    private double areaFactor;
    private final ArrayList<String> path = new ArrayList<>() {{
        add("Tiles/cake.png");
        add("Tiles/castle.png");
        add("Tiles/choco.png");
        add("Tiles/dirt.png");
        add("Tiles/grass.png");
        add("Tiles/sand.png");
        add("Tiles/snow.png");
        add("Tiles/stone.png");
    }};

    private final ArrayList<String> pathCenter = new ArrayList<>() {{
        add("Tiles/cakeCenter.png");
        add("Tiles/castleCenter.png");
        add("Tiles/chocoCenter.png");
        add("Tiles/dirtCenter.png");
        add("Tiles/grassCenter.png");
        add("Tiles/sandCenter.png");
        add("Tiles/snowCenter.png");
        add("Tiles/stoneCenter.png");
    }};
    Tower(double width,double height,double layoutX,double layoutY,int type,double xFactor,double yFactor,double areaFactor){
        super(width,height);
        this.type=type;
        this.xFactor=xFactor;
        this.yFactor=yFactor;
        this.areaFactor=areaFactor;
        super.setLayoutX(layoutX);
        super.setLayoutY(layoutY);
        drawTower();
    }

    private void drawTower() {
        GraphicsContext gc = getGraphicsContext2D();
        Image startPlatform = new Image(path.get(this.type));
        Image centerPlatform = new Image(pathCenter.get(this.type));

        double offSet = 3;
        gc.setFill(Color.web("#36454f"));
        gc.fillRect(offSet, offSet, getWidth() - (2 * offSet), getHeight() - (2 * offSet));

        gc.drawImage(startPlatform, 0, 0, 12, 70, 0, 0, 12, 70);
        gc.drawImage(startPlatform, 58, 0, 12, 70, getWidth() - 12, 0, 12, 70);

        double sourceX = 12;
        double sourceY = 0;
        double sourceWidth = 46;
        double sourceHeight = 70;

        double currentPos = 12;
        while (currentPos + 12 < getWidth()) {
            gc.drawImage(startPlatform, sourceX, sourceY, sourceWidth, sourceHeight, currentPos, 0, sourceWidth, sourceHeight);
            currentPos = currentPos + 46;
        }

        double centerHeight = 65;

        while (centerHeight + 70 <= getHeight()) {

            gc.drawImage(centerPlatform, 0, 0, 12, 70, 0, centerHeight, 12, 70);
            gc.drawImage(centerPlatform, 58, 0, 12, 70, getWidth() - 12, centerHeight, 12, 70);

            double sourceX1 = 12;
            double sourceY1 = 0;
            double sourceWidth1 = 46;
            double sourceHeight1 = 70;

            double currentPos1 = 12;
            while (currentPos1 + 12 < getWidth()) {
                gc.drawImage(centerPlatform, sourceX1, sourceY1, sourceWidth1, sourceHeight1, currentPos1, centerHeight, sourceWidth1, sourceHeight1);
                currentPos1 = currentPos1 + 46;
            }
            centerHeight = centerHeight + 70;
        }
        gc.setFill(Color.RED);
        gc.fillRect(getWidth()/2-7,0,14,5);
    }


}

