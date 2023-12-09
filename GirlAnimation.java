package org.example;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.transform.Scale;
import javafx.util.Duration;
public class GirlAnimation extends CharacterAnimationBase{
    private ImageView idleImageView = new ImageView();
    private ImageView walkImageView = new ImageView();
    private ImageView runImageView = new ImageView();
    private int currentIdleImageIndex = 0;
    private int currentWalkImageIndex =0;
    private int currentRunImageIndex =0;
    private Image[] idleImagesChar;
    private Image[] walkImagesChar;
    private Image[] runImagesChar;

    private double width;
    private double height;

    private double xStart;

    private Timeline idleTimeline;
    private Timeline runTimeline;

    private double yPos;

    private double xFactor;
    private double yFactor;

    public void setReverse() {
        this.reverse = !reverse;
    }

    private boolean reverse;

    private Scale scale;

    private int heartCount1;

    public int getHeartCount1() {
        return heartCount1;
    }

    public void setHeartCount1(int heartCount1) {
        this.heartCount1 = heartCount1;
    }

    GirlAnimation(double xPos,double yPos,double xFactor,double yFactor,double areaFactor){

        this.setLayoutX(xPos);
        this.setLayoutY(yPos);

        this.reverse=false;
        this.scale=new Scale(1, -1);

        this.xStart=xPos;
        this.yPos=yPos;

        this.heartCount1=3;

        this.xFactor=xFactor;
        this.yFactor=yFactor;

        idleImagesChar= new Image[16];
        runImagesChar = new Image[20];
        walkImagesChar= new Image[20];

        for(int i=1;i<=16;i++){
            idleImagesChar[i-1]=new Image("Character/Girl_Character/Idle ("+i+").png");
        }
        for(int i=1;i<=20;i++){
            runImagesChar[i-1]=new Image("Character/Girl_Character/Run ("+i+").png");
        }
        for(int i=1;i<=20;i++){
            walkImagesChar[i-1]=new Image("Character/Girl_Character/Walk ("+i+").png");
        }

        this.getChildren().addAll(idleImageView,runImageView);
        width=75*xFactor;
        height=50*yFactor;

        idleTimeline = createImageAnimation();
        idleTimeline.play();
    }
    @Override
    public Timeline createImageAnimation() {
        Duration duration = Duration.millis(70); // Set the duration for each image

        Timeline timeline = new Timeline(
                new KeyFrame(duration, e -> {
                    // Update the image in the ImageView
                    idleImageView.setImage(idleImagesChar[currentIdleImageIndex]);
                    idleImageView.setFitWidth(width);
                    idleImageView.setPreserveRatio(true);
                    idleImageView.setSmooth(true);
                    currentIdleImageIndex = (currentIdleImageIndex + 1) % idleImagesChar.length;
                }, new KeyValue(idleImageView.opacityProperty(), 1.0))
        );

        timeline.setCycleCount(Timeline.INDEFINITE); // Repeat the animation indefinitely
        return timeline;
    }

    @Override
    public void createRunAnimation(double xEnd, double gap,double stickLength, Timeline backgroundTimeline, EventHandler<ActionEvent> onAnimationComplete, EventHandler<ActionEvent> onEvent, CherryAndBee cb) {
        double a = 5;
        idleTimeline.stop();
        idleImageView.setOpacity(0);
        runImageView.setOpacity(1);


        Duration duration = Duration.millis(50);
        double[] mutableXStart = {this.xStart};
        double[] mutableXEnd = {xEnd};


        final Timeline[] timeline = {null};

        timeline[0] = new Timeline(
                new KeyFrame(duration, e -> {
                    runImageView.setImage(runImagesChar[currentRunImageIndex]);
                    if (reverse) {
                        this.setLayoutY(this.yPos + 165 * yFactor);
                        runImageView.getTransforms().clear();
                        runImageView.getTransforms().add(scale);

                    } else {
                        runImageView.getTransforms().clear();
                        if (mutableXStart[0] >= stickLength + this.xStart) {
                            this.setLayoutY(this.yPos);
                        } else {
                            this.setLayoutY(this.yPos - 7 * xFactor);
                        }
                    }
                    if ((mutableXStart[0] > stickLength + this.xStart && stickLength+68*xFactor<gap-this.xStart) || (reverse && mutableXStart[0] >= gap)||((mutableXStart[0] > stickLength + this.xStart && stickLength+68*xFactor>xEnd-xStart))) {
                        runImageView.setOpacity(0);
                        idleImageView.setOpacity(1);
                        backgroundTimeline.stop();
                        fallAnimation(mutableXStart[0]);
                        timeline[0].stop();
                        onEvent.handle(new CustomActionEvent(this, this, 3));
                    }
                    runImageView.setFitWidth(width);
                    runImageView.setPreserveRatio(true);
                    runImageView.setSmooth(true);
                    this.setLayoutX(mutableXStart[0]);
                    mutableXStart[0] = mutableXStart[0] + a;
                    if (cb.isCherryStatus()) {
                        if (splitBoundIntersect(this.getBoundsInParent(),0.9 ,0.3,cb.getCherryBound())) {
                            onEvent.handle(new CustomActionEvent(this, this, 1));

                        }
                    }
                    if (cb.isBeeStatus()) {
                        if (splitBoundIntersect(this.getBoundsInParent(),0.9 ,0.3,cb.getBeeBound())) {
                            onEvent.handle(new CustomActionEvent(this, this, 2));

                        }
                    }
                    if(this.heartCount1==0){
                        timeline[0].stop();
                        backgroundTimeline.stop();
                        this.setLayoutY(this.yPos);
                        runImageView.setOpacity(0);
                        idleImageView.setOpacity(1);
                        onEvent.handle(new CustomActionEvent(this, this, 5));
                    }

                    if (mutableXStart[0] >= mutableXEnd[0] - 54 * xFactor  && (stickLength+68*xFactor<xEnd-xStart)) {
                        timeline[0].stop();
                        backgroundTimeline.stop();
                        this.setLayoutY(this.yPos);
                        runImageView.setOpacity(0);
                        idleImageView.setOpacity(1);
                        onAnimationComplete.handle(new ActionEvent(this, null));
                        onEvent.handle(new CustomActionEvent(this, this, 4));
                    }
                    currentRunImageIndex = (currentRunImageIndex + 1) % runImagesChar.length;

                }, new KeyValue(runImageView.opacityProperty(), 1.0))
        );

        timeline[0].setCycleCount(Timeline.INDEFINITE);
        timeline[0].play();
        backgroundTimeline.play();
        this.runTimeline = timeline[0];

    }

    public void fallAnimation(double x){
        Duration duration = Duration.millis(70); // Set the duration for each image
        double a =20;
        double[] mutableXStart = {this.yPos};
        Timeline timeline = new Timeline(
                new KeyFrame(duration, e -> {
                    // Update the image in the ImageView
                    idleImageView.setImage(idleImagesChar[currentIdleImageIndex]);
                    idleImageView.setFitWidth(width);
                    idleImageView.setPreserveRatio(true);
                    mutableXStart[0]=mutableXStart[0]+a;
                    this.setLayoutX(x);
                    this.setLayoutY(mutableXStart[0]);
                    idleImageView.setSmooth(true);
                    currentIdleImageIndex = (currentIdleImageIndex + 1) % idleImagesChar.length;
                }, new KeyValue(idleImageView.opacityProperty(), 1.0))
        );

        timeline.setCycleCount(20);
        timeline.setOnFinished(event -> {
            this.setLayoutY(this.yPos);
            this.setLayoutX(this.xStart);
        });

        timeline.play();
    }

    public void pauseRunAnimation() {
        this.runTimeline.pause();
    }

    public void pauseIdleAnimation() {
        this.idleTimeline.pause();
    }

    public void playRunAnimation() {
        this.runTimeline.play();
    }

    public void playIdleAnimation() {
        this.idleTimeline.play();
    }



    public boolean splitBoundIntersect(Bounds originalBounds, double ratio1, double ratio2, Bounds secondBound) {

        double splitWidth = originalBounds.getWidth() * ratio1;

        Bounds firstBounds1 = new BoundingBox(
                originalBounds.getMinX(),
                originalBounds.getMinY(),
                splitWidth,
                originalBounds.getHeight()
        );


        double splitWidth2 = originalBounds.getWidth() * ratio2;


        Bounds secondBounds2 = new BoundingBox(
                firstBounds1.getMinX() + splitWidth2,
                firstBounds1.getMinY(),
                firstBounds1.getWidth() - splitWidth2,
                firstBounds1.getHeight()
        );


        return secondBounds2.intersects(secondBound);
    }



}
