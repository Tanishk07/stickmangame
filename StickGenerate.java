package org.example;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


import java.util.ArrayList;

public class StickGenerate extends Canvas {
    private final int type;
    private final double xPos;
    private final double yPos;

    private final double cWidth;
    private final double cHeight;


    private final ArrayList<String> path = new ArrayList<>() {{
        add("Stick/1.png");
        add("Stick/2.png");
        add("Stick/3.png");
    }};

    StickGenerate(double cWidth,double cHeight ,double xPos,double yPos,int type){

        super(cWidth,cHeight);
        super.setLayoutX(0);
        super.setLayoutY(0);


        this.xPos=xPos;
        this.yPos=yPos;
        this.cWidth=cWidth;
        this.cHeight=cHeight;
        this.type=type;

    }
    public void drawStick(double rotation,double length){

        GraphicsContext gc = getGraphicsContext2D();
        Image stickImage = new Image(path.get(this.type));

        double sourceX = 16;
        double sourceY = 22;
        double sourceWidth = 850;
        double sourceHeight = 7;

        double destinationX = this.xPos;
        double destinationY = this.yPos;

        double destinationWidth = length;
        double destinationHeight = 7;

        gc.save();
        gc.clearRect(0, 0,cWidth,cHeight);
        gc.translate(destinationX, destinationY);
        gc.rotate(rotation);
        gc.drawImage(stickImage, sourceX, sourceY, length, sourceHeight, 0, 0, destinationWidth, destinationHeight);

        gc.restore();

    }

}
