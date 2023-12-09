package org.example;


import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.effect.DropShadow;
import javafx.stage.Screen;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import javafx.application.Platform;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import java.io.File;



public class Main extends Application {
    private static final long TIMEOUT_MILLIS = 5000;
    private static final long serialVersionUID = 783409273021633180L;

    FileManager fileManager;

    private boolean responsivenessCheckPassed = false;
    private ScheduledExecutorService executorService;
    private  double sceneWidth ;
    private  double sceneHeight ;

    private MusicAndSound mS;

    private Stage startStage;
    private Stage gameStage;

    private double xFactor;
    private double yFactor;
    private double areaFactor;

    private double stickLength;

    private static final double BACKGROUND_SPEED = 5;

    private boolean music;
    private boolean sound;

    private boolean handleCloseRequestStatus;
    private int mode;

    private Text easyText;
    private Text mediumText;
    private Text hardText;

    private Button easyButton;
    private Button mediumButton;
    private Button hardButton;

    private ScoreBoard scoreBoard;

    private int playingCharacter;

    private boolean stickAlready;


    private Tower startTower;
    private Tower interTower;
    private Tower endTower;

    private ArrayList<Double> towerXCord ;
    private ArrayList<Double> towerWidth;


    private StickGenerate stickGenerate;

    private CharacterAnimation gameCharacter;

    private int pillarType;


    private Timeline startPillarTimeline;
    private Timeline midPillarTimeline;
    private Timeline endPillarTimeline;

    private Timeline stickBeginTimeline;
    private Timeline stickFallTimeline;

    private Timeline backgroundTimeline;

    private ImageView cherryView;
    private ImageView beeView;

    private double cherryXCord;
    private double beeXCord;

    private boolean cherryStatus;
    private boolean beeStatus;

    private int cherryYAxis;
    private int beeYAxis;

    private double beeYCord;
    private double cherryYCord;

    private CherryAndBee cb;

    private Pane inGameScreenPaneRoot;

    private IndividualUser currentUser ;

    private CherryCount cherryCount;
    private LifeCount lifeCount;
    private InGameScoreCount scoreCount;

    private boolean cherryPick;
    private boolean beeCaught;

    private int uiNum;

    private boolean fallDown;
    private UserData userData;


    private boolean resumeGame;

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        handleCloseRequestStatus=true;

        fileManager=FileManager.getInstance();
        fileManager.setFilePath("serializedData.ser");



        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        this.sceneWidth=bounds.getWidth();
        this.sceneHeight=bounds.getHeight()-20;
        //System.out.println(sceneHeight);
        // -->796
        //System.out.println(sceneWidth);
        // --->1536

        xFactor=sceneWidth/1536;
        yFactor=sceneHeight/796;
        areaFactor=xFactor*yFactor;

        this.startStage=primaryStage;
        this.gameStage=new Stage();

        initialScreen();
    }
    public void initialScreen(){

        mS=new MusicAndSound();
        mS.backgroundMusicFunc();
        Pane root = new Pane();

        Font.loadFont(getClass().getResourceAsStream("/Fonts/soupofjustice.ttf"), 14);

        music=true;
        sound=true;
        mode =0;

        cherryXCord=0;
        cherryYCord=0;
        beeXCord=0;
        beeYCord=0;


        try {
            userData=fileManager.fileGetData();
        }
        catch (Exception e) {

            userData = new UserData();
            userData.addUserIn(new IndividualUser("TANISHQ", 400));
            userData.addUserIn(new IndividualUser("SAMEER", 300));
            userData.addUserIn(new IndividualUser("VIRAT", 200));
        }

        currentUser=null;

        //Background Image
        Image backgroundForest = new Image("/Background/StartBackground3.jpg");
        ImageView backgroundForestView = new ImageView(backgroundForest);

        backgroundForestView.setOpacity(0.8);
        backgroundForestView.setX(0);
        backgroundForestView.setY(0);
        backgroundForestView.setFitWidth(sceneWidth);
        backgroundForestView.setPreserveRatio(true);
        backgroundForestView.setSmooth(true);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(10);
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);
        dropShadow.setColor(Color.BLACK);

        //StartCharacterAnimation
        StartCharacterAnimation characterAnimationTry=new StartCharacterAnimation(400*xFactor,200*yFactor);
        characterAnimationTry.setLayoutX(100*xFactor);
        characterAnimationTry.setLayoutY(sceneHeight/2.3);



        //UserNameInput
        /*
        Image userNameInputImage = new Image("UserInterface/tableOld.png");
        ImageView userNameInputImageView = new ImageView(userNameInputImage);


*/
        Label staticTextLabel = new Label("Enter Your UserName");
        staticTextLabel.setStyle("-fx-font-size: "+20*areaFactor+";" +
                "-fx-font-family: 'Soup of Justice';" +
                "-fx-text-fill: #000000 ;");
        staticTextLabel.setLayoutX(sceneWidth/(1.3));
        staticTextLabel.setLayoutY(sceneHeight/3-15*yFactor);

        TextField userNameField = new TextField();
        userNameField.setLayoutX(sceneWidth/(1.3));
        userNameField.setLayoutY(sceneHeight/3+10*yFactor);
        userNameField.setPrefWidth(sceneWidth/5);
        userNameField.setPrefHeight(sceneHeight/12);
        userNameField.setBackground(null);
        userNameField.setStyle("-fx-background-color:transparent;" +
                "-fx-background-image: url('UserInterfaces/tableOld.png'); " +
                "-fx-background-size: 100% 100%; " +
                "-fx-background-position: center; " +
                "-fx-background-repeat: no-repeat;"+
                "-fx-text-fill: #B2BEB5  ;"+
                "-fx-prompt-text-fill: #B2BEB5 ;"+
                "-fx-font-family: 'Soup of Justice';" +
                "-fx-font-size: "+30*areaFactor+";"
        );
        userNameField.setPromptText("USER_NAME");

        DropShadow enterTextShadow = new DropShadow();
        enterTextShadow.setRadius(0.5);
        enterTextShadow.setOffsetX(1);
        enterTextShadow.setOffsetY(1);
        enterTextShadow.setColor(Color.BLACK);

        DropShadow enterButtonShadow = new DropShadow();
        enterButtonShadow.setRadius(2);
        enterButtonShadow.setOffsetX(3);
        enterButtonShadow.setOffsetY(3);
        enterButtonShadow.setColor(Color.BLACK);

        DropShadow enterButtonPressedShadow = new DropShadow();
        enterButtonPressedShadow.setRadius(3);
        enterButtonPressedShadow.setOffsetX(4);
        enterButtonPressedShadow.setOffsetY(4);
        enterButtonPressedShadow.setColor(Color.BLACK);

        Text userNameEnterText = new Text("ENTER");
        userNameEnterText.setStyle("-fx-font-family: 'Soup of Justice';-fx-font-size: "+ 24*areaFactor+ ";-fx-fill: #e1e6e1;");
        userNameEnterText.setEffect(enterTextShadow);
        Button userNameEnter=new Button();
        userNameEnter.setGraphic(userNameEnterText);
        userNameEnter.setLayoutX(sceneWidth/(1.3)+110*xFactor);
        userNameEnter.setLayoutY(sceneHeight/3+80*yFactor);

        Image enterButtonBackgroundImage=new Image("UserInterfaces/GreenTable.png");
        BackgroundImage backgroundImage = new BackgroundImage(
                enterButtonBackgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(100, BackgroundSize.AUTO, true, false, false, true)); // Set width to AUTO

        userNameEnter.setBackground(new Background(backgroundImage));
        userNameEnter.setEffect(enterButtonShadow);

        userNameEnter.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> userNameEnter.setEffect(enterButtonPressedShadow));
        userNameEnter.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> userNameEnter.setEffect(enterButtonShadow));

        Label userNameAlreadyExist = new Label("* User Name Alreday Exist");
        userNameAlreadyExist.setStyle("-fx-font-size: "+18*areaFactor+";" +
                "-fx-font-family: 'Soup of Justice';" +
                "-fx-text-fill: #880808 ;");
        userNameAlreadyExist.setLayoutX(sceneWidth/(1.3)+25*xFactor);
        userNameAlreadyExist.setLayoutY(sceneHeight/3+120*yFactor);
        userNameAlreadyExist.setOpacity(0);

        userNameEnter.setOnAction(event -> userEnterButtonPressed(userNameEnter,userNameField,userNameAlreadyExist,0));












        //StartButton
        DropShadow startShadow = new DropShadow();
        startShadow.setRadius(3);
        startShadow.setOffsetX(2);
        startShadow.setOffsetY(2);
        startShadow.setColor(Color.GREEN);
        Text startText = new Text("START");
        startText.setStyle("-fx-font-family: 'Soup of Justice';-fx-font-size :"+ 120*areaFactor+";");
        startText.setEffect(dropShadow);
        Image startTextImage = new Image("UserInterfaces/grassFive.jpeg");
        startText.setFill(new ImagePattern(startTextImage));
        Button startButton = new Button();
        startButton.setGraphic(startText);
        startButton.setLayoutX(sceneWidth/2-170*xFactor); // X-coordinate
        startButton.setLayoutY(sceneHeight*0.10);
        startButton.setStyle("-fx-background-color: transparent;");
        startButton.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> startButton.setEffect(startShadow));
        startButton.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> startButton.setEffect(null));
        startButton.setOnAction(event -> startGame(root,userNameEnter,userNameField,userNameAlreadyExist,1));

        //ResumeButton
        DropShadow resumeShadow = new DropShadow();
        resumeShadow.setRadius(3);
        resumeShadow.setOffsetX(2);
        resumeShadow.setOffsetY(2);
        resumeShadow.setColor(Color.BROWN);
        Text resumeText = new Text("RESUME");
        resumeText.setStyle("-fx-font-family: 'Soup of Justice';-fx-font-size:"+ 60*areaFactor+ ";");// Set shadow color
        resumeText.setEffect(dropShadow);
        Image resumeTextImage = new Image("UserInterfaces/bg.png");
        resumeText.setFill(new ImagePattern(resumeTextImage));
        Button resumeButton = new Button();
        resumeButton.setGraphic(resumeText);
        resumeButton.setLayoutX((sceneWidth/2)-120*xFactor); // X-coordinate
        resumeButton.setLayoutY(sceneHeight*0.25);
        resumeButton.setStyle("-fx-background-color: transparent;");
        resumeButton.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> resumeButton.setEffect(resumeShadow));
        resumeButton.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> resumeButton.setEffect(null));
        resumeButton.setOnAction(event -> resumeGameStateFunc(root,userNameEnter,userNameField,userNameAlreadyExist,1));

        //ScoreBoard
        Image scoreBoardHeaderImage = new Image("UserInterfaces/down.png");
        ImageView scoreBoardHeaderImageView = new ImageView(scoreBoardHeaderImage);
        scoreBoardHeaderImageView.setFitWidth(sceneWidth/2-250*xFactor); // Set the width based on your requirement
        scoreBoardHeaderImageView.setPreserveRatio(true); // Preserve the aspect ratio
        scoreBoardHeaderImageView.setSmooth(true); // Enable smooth image rendering
        scoreBoardHeaderImageView.setLayoutX(sceneWidth/2-250*xFactor);
        scoreBoardHeaderImageView.setLayoutY(sceneHeight*0.485);
        scoreBoardHeaderImageView.setOpacity(0.7);

        scoreBoard=new ScoreBoard(sceneWidth/2-250*xFactor,sceneHeight*0.58,sceneWidth/2-250*xFactor,sceneHeight*0.39,areaFactor,xFactor,yFactor);


        userData.sortArr();
        userInScoreBoard();


        //Level
        HBox levelOuterContainer =new HBox();
        levelOuterContainer.setPadding(new Insets(10*xFactor,10*yFactor,10*xFactor,10*yFactor));
        levelOuterContainer.setStyle("-fx-background-color: transparent;");
        levelOuterContainer.setAlignment(Pos.CENTER_LEFT);
        levelOuterContainer.setLayoutX(sceneWidth/4);
        levelOuterContainer.setLayoutY(sceneHeight*0.35);

        DropShadow levelShadow = new DropShadow();
        levelShadow.setRadius(1);
        levelShadow.setOffsetX(2);
        levelShadow.setOffsetY(2);
        levelShadow.setColor(Color.BLACK);

        Text levelText = new Text("LEVEL");
        levelText.setStyle("-fx-font-family: 'Soup of Justice';-fx-font-size: "+ 50*areaFactor+ ";-fx-fill:#947D0E");
        levelText.setEffect(levelShadow);
        Image levelTextImage = new Image("UserInterfaces/greyThree.jpeg");
        levelText.setFill(new ImagePattern(levelTextImage));

        HBox levelContainer =new HBox();
        levelContainer.setPadding(new Insets(10*xFactor,10*yFactor,10*xFactor,10*yFactor));
        levelContainer.setStyle("-fx-background-color: #FBF5F380;");
        levelContainer.setAlignment(Pos.CENTER_LEFT);


        easyText = new Text("Easy");
        easyText.setStyle("-fx-font-family: 'Soup of Justice';-fx-font-size: "+ 40*areaFactor+ ";-fx-fill: #A6AE9C;");
        easyText.setEffect(levelShadow);
        easyButton = new Button();
        easyButton.setGraphic(easyText);
        easyButton.setStyle("-fx-background-color: transparent;");
        easyButton.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> easyButton.setEffect(levelShadow));
        easyButton.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> easyButton.setEffect(null));
        easyButton.setOnAction(event -> levelDecide(0));

        mediumText = new Text("Medium");
        mediumText.setStyle("-fx-font-family: 'Soup of Justice';-fx-font-size:  "+ 40*areaFactor+ ";-fx-fill: #A6AE9C;");
        mediumText.setEffect(levelShadow);
        mediumButton = new Button();
        mediumButton.setGraphic(mediumText);
        mediumButton.setStyle("-fx-background-color: transparent;");
        mediumButton.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> mediumButton.setEffect(levelShadow));
        mediumButton.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> mediumButton.setEffect(null));
        mediumButton.setOnAction(event -> levelDecide(1));

        hardText = new Text("Hard");
        hardText.setStyle("-fx-font-family: 'Soup of Justice';-fx-font-size:  "+ 40*areaFactor+ ";-fx-fill: #A6AE9C;");
        hardText.setEffect(levelShadow);
        hardButton = new Button();
        hardButton.setStyle("-fx-background-color: transparent;");
        hardButton.setGraphic(hardText);
        hardButton.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> hardButton.setEffect(levelShadow));
        hardButton.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> hardButton.setEffect(null));
        hardButton.setOnAction(event -> levelDecide(2));

        levelContainer.getChildren().addAll(createSpacer(5*xFactor),easyButton,createSpacer(20*xFactor),mediumButton,createSpacer(20*xFactor),hardButton);
        levelOuterContainer.getChildren().addAll(levelText,createSpacer(45*xFactor),levelContainer);


        DropShadow soundShadow = new DropShadow();
        soundShadow.setRadius(3);
        soundShadow.setOffsetX(2);
        soundShadow.setOffsetY(2);
        soundShadow.setColor(Color.GREEN);


        //Sound On
        Image imageSoundOn = new Image("UserInterfaces/sound.png");
        ImageView imageSoundOnView = new ImageView(imageSoundOn);
        imageSoundOnView.setFitWidth(65*xFactor);
        imageSoundOnView.setFitHeight(65*yFactor);
        Button buttonSound = new Button("",imageSoundOnView);
        buttonSound.setStyle("-fx-background-color: transparent;");
        buttonSound.setLayoutX(sceneWidth-90*xFactor); // X-coordinate
        buttonSound.setLayoutY(sceneHeight-200*yFactor);
        buttonSound.setPrefWidth(65*xFactor);
        buttonSound.setPrefHeight(65*yFactor);
        buttonSound.setOnAction(event -> soundButtonPressed(buttonSound));
        buttonSound.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> buttonSound.setEffect(soundShadow));
        buttonSound.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> buttonSound.setEffect(null));



        //Music On
        Image imageMusicOn = new Image("UserInterfaces/music.png");
        ImageView imageMusicOnView = new ImageView(imageMusicOn);
        imageMusicOnView.setFitWidth(65*xFactor);
        imageMusicOnView.setFitHeight(65*yFactor);
        Button buttonMusic = new Button("",imageMusicOnView);
        buttonMusic.setStyle("-fx-background-color: transparent;");
        buttonMusic.setLayoutX(sceneWidth-90*xFactor); // X-coordinate
        buttonMusic.setLayoutY(sceneHeight-120*yFactor);
        buttonMusic.setPrefWidth(65*xFactor);
        buttonMusic.setPrefHeight(65*yFactor);
        buttonMusic.setOnAction(event -> musicButtonPressed(buttonMusic));
        buttonMusic.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> buttonMusic.setEffect(soundShadow));
        buttonMusic.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> buttonMusic.setEffect(null));




        root.getChildren().addAll(backgroundForestView);
        root.getChildren().addAll(buttonSound,buttonMusic,startButton,resumeButton,characterAnimationTry,staticTextLabel,userNameField,userNameEnter,userNameAlreadyExist,levelOuterContainer,scoreBoard,scoreBoardHeaderImageView);
        Scene scene = new Scene(root, sceneWidth, sceneHeight);

        gameStage.hide();
        startStage.setScene(scene);
        startStage.show();
    }
    void startGame(Pane root,Button buttonE,TextField textfieldE,Label labelE,int i1){
        mS.buttonPressSound();
        resumeGame=false;
        if(currentUser==null){
            userEnterButtonPressed(buttonE,textfieldE,labelE,i1);
        }
        Region rootTransparent = new Region();
        rootTransparent.setLayoutX(0);
        rootTransparent.setLayoutY(0);
        rootTransparent.setPrefWidth(sceneWidth);
        rootTransparent.setPrefHeight(sceneHeight);
        rootTransparent.setStyle("-fx-background-color: rgba(242, 243, 245,0.9);");

        CharacterChoose characterChooseWindow = new CharacterChoose(sceneWidth / 1.9, sceneHeight / 2.3,  xFactor, yFactor,areaFactor);
        characterChooseWindow.setLayoutX(sceneWidth / 4 );
        characterChooseWindow.setLayoutY(sceneHeight / 3.5);


        characterChooseWindow.setOnBoyButtonPressed(event1 -> setCurrentCharacter(root,rootTransparent,characterChooseWindow,0));
        characterChooseWindow.setOnGirlButtonPressed(event1 -> setCurrentCharacter(root,rootTransparent,characterChooseWindow,1));
        characterChooseWindow.setOnHatButtonPressed(event1 -> setCurrentCharacter(root,rootTransparent,characterChooseWindow,2));

        root.getChildren().addAll(rootTransparent, characterChooseWindow);

    }

    void setCurrentCharacter(Pane root ,Region region,CharacterChoose characterChoose,int where){
        mS.characterChooseFunc();
        playingCharacter=where;
        root.getChildren().removeAll(region,characterChoose);
        inGameScreen();
    }

    void userEnterButtonPressed(Button buttonE,TextField textfieldE,Label labelE,int i1){
        mS.enterPressSound();
        String userName=textfieldE.getText();
        String finalUserName="";
        if(i1==1){
            finalUserName="USER_NAME";
        }

        for(int i=0;i<userName.length();i++){
            finalUserName=finalUserName+Character.toUpperCase(userName.charAt(i));
        }


        if(userData.checkUserName(finalUserName)==-1){
            labelE.setOpacity(0);
            currentUser = new IndividualUser(finalUserName,0);
            userData.addUserIn(currentUser);
            userInScoreBoard();
        }
        else{
            currentUser=userData.getArr().get(userData.checkUserName(finalUserName));
            labelE.setOpacity(1);
        }
    }

    void userInScoreBoard(){
        scoreBoard.clear();
        int tempPos=0;
        for(IndividualUser iu : userData.getArr()){
            tempPos=tempPos+1;
            scoreBoard.addUserScore(iu.getUserName(),tempPos,iu.getUserScore(),areaFactor,xFactor,yFactor);
        }
    }

    void soundButtonPressed(Button b1){
        Image imageSound;
        ImageView imageSoundView;
        sound=!sound;
        mS.soundAllow=!mS.soundAllow;

        if(sound){
            imageSound =new Image("UserInterfaces/sound.png");
            imageSoundView = new ImageView(imageSound);
            imageSoundView.setFitWidth(65*xFactor);
            imageSoundView.setFitHeight(65*yFactor);
            b1.setGraphic(imageSoundView);
            b1.setStyle("-fx-background-color: transparent;");
            b1.setLayoutX(sceneWidth-90*xFactor); // X-coordinate
            b1.setLayoutY(sceneHeight-200*yFactor);
            b1.setPrefWidth(65*xFactor);
            b1.setPrefHeight(65*yFactor);
        }
        else{
            imageSound =new Image("UserInterfaces/sound_off.png");
            imageSoundView = new ImageView(imageSound);
            imageSoundView.setFitWidth(65*xFactor);
            imageSoundView.setFitHeight(65*yFactor);
            b1.setGraphic(imageSoundView);
            b1.setStyle("-fx-background-color: transparent;");
            b1.setLayoutX(sceneWidth-90*xFactor); // X-coordinate
            b1.setLayoutY(sceneHeight-200*yFactor);
            b1.setPrefWidth(65*xFactor);
            b1.setPrefHeight(65*yFactor);
        }

    }
    void musicButtonPressed(Button b1){
        Image musicSound;
        ImageView imageMusicView;
        music=!music;
        mS.musicAllow=!mS.musicAllow;

        if(music){
            mS.backgroundMusicPlay();
            musicSound =new Image("UserInterfaces/music.png");
            imageMusicView = new ImageView(musicSound);
            imageMusicView.setFitWidth(65*xFactor);
            imageMusicView.setFitHeight(65*yFactor);
            b1.setGraphic(imageMusicView);
            b1.setStyle("-fx-background-color: transparent;");
            b1.setLayoutX(sceneWidth-90*xFactor); // X-coordinate
            b1.setLayoutY(sceneHeight-120*yFactor);
            b1.setPrefWidth(65*xFactor);
            b1.setPrefHeight(65*yFactor);
        }
        else{
            mS.backgroundMusicPause();
            musicSound =new Image("UserInterfaces/music_off.png");
            imageMusicView = new ImageView(musicSound);
            imageMusicView.setFitWidth(65*xFactor);
            imageMusicView.setFitHeight(65*yFactor);
            b1.setGraphic(imageMusicView);
            b1.setStyle("-fx-background-color: transparent;");
            b1.setLayoutX(sceneWidth-90*xFactor); // X-coordinate
            b1.setLayoutY(sceneHeight-120*yFactor);
            b1.setPrefWidth(65*xFactor);
            b1.setPrefHeight(65*yFactor);
        }
    }

    void levelDecide(int valueOfButton){
        mS.buttonPressSound();
        DropShadow levelShadow = new DropShadow();
        levelShadow.setRadius(1);
        levelShadow.setOffsetX(2);
        levelShadow.setOffsetY(2);
        levelShadow.setColor(Color.BLACK);

        mode=valueOfButton;
        if(mode==0){
            easyText.setStyle("-fx-font-family: 'Soup of Justice';-fx-font-size: "+40*areaFactor+";-fx-fill: #BE682F;");
            easyText.setEffect(levelShadow);
            easyButton.setGraphic(easyText);
            easyButton.setStyle("-fx-background-color: transparent;");

            mediumText.setStyle("-fx-font-family: 'Soup of Justice';-fx-font-size:"+40*areaFactor+";-fx-fill: #A6AE9C;");
            mediumText.setEffect(levelShadow);
            mediumButton.setGraphic(mediumText);
            mediumButton.setStyle("-fx-background-color: transparent;");


            hardText.setStyle("-fx-font-family: 'Soup of Justice';-fx-font-size: "+40*areaFactor+";-fx-fill: #A6AE9C;");
            hardText.setEffect(levelShadow);
            hardButton.setStyle("-fx-background-color: transparent;");
            hardButton.setGraphic(hardText);

        }
        else if(mode==1){
            easyText.setStyle("-fx-font-family: 'Soup of Justice';-fx-font-size: "+40*areaFactor+";-fx-fill: #A6AE9C;");
            easyText.setEffect(levelShadow);
            easyButton.setGraphic(easyText);
            easyButton.setStyle("-fx-background-color: transparent;");

            mediumText.setStyle("-fx-font-family: 'Soup of Justice';-fx-font-size: "+40*areaFactor+";-fx-fill: #BE682F;");
            mediumText.setEffect(levelShadow);
            mediumButton.setGraphic(mediumText);
            mediumButton.setStyle("-fx-background-color: transparent;");


            hardText.setStyle("-fx-font-family: 'Soup of Justice';-fx-font-size: "+40*areaFactor+";-fx-fill: #A6AE9C;");
            hardText.setEffect(levelShadow);
            hardButton.setStyle("-fx-background-color: transparent;");
            hardButton.setGraphic(hardText);
        }
        else if(mode==2){
            easyText.setStyle("-fx-font-family: 'Soup of Justice';-fx-font-size: "+40*areaFactor+";-fx-fill: #A6AE9C;");
            easyText.setEffect(levelShadow);
            easyButton.setGraphic(easyText);
            easyButton.setStyle("-fx-background-color: transparent;");

            mediumText.setStyle("-fx-font-family: 'Soup of Justice';-fx-font-size: "+40*areaFactor+";-fx-fill: #A6AE9C;");
            mediumText.setEffect(levelShadow);
            mediumButton.setGraphic(mediumText);
            mediumButton.setStyle("-fx-background-color: transparent;");


            hardText.setStyle("-fx-font-family: 'Soup of Justice';-fx-font-size: "+40*areaFactor+";-fx-fill: #BE682F;");
            hardText.setEffect(levelShadow);
            hardButton.setStyle("-fx-background-color: transparent;");
            hardButton.setGraphic(hardText);
        }

    }
    private Region createSpacer(double width) {
        Region spacer = new Region();
        spacer.setPrefWidth(width);
        return spacer;
    }


    public void inGameScreen(){
        mS.fallEndFunc();
        inGameScreenPaneRoot = new Pane();
        Random random = new Random();




        Image cherry = new Image("Rewards/CherryBoard.png");
        cherryView = new ImageView(cherry);
        cherryView.setFitWidth(30*xFactor); // Set the width based on your requirement
        cherryView.setPreserveRatio(true);
        cherryView.setSmooth(true);
        cherryView.setOpacity(0);

        Image bee = new Image("Enemy/bee.png");
        beeView = new ImageView(bee);
        beeView.setFitWidth(30*xFactor); // Set the width based on your requirement
        beeView.setPreserveRatio(true);
        beeView.setSmooth(true);
        beeView.setOpacity(0);

        //LifeCount
        lifeCount=new LifeCount(xFactor,yFactor,areaFactor);
        lifeCount.setLayoutX(sceneWidth-200*xFactor);
        lifeCount.setLayoutY(30*yFactor);


        //CherryCount
        cherryCount=new CherryCount(xFactor,yFactor,areaFactor);
        cherryCount.setLayoutX(sceneWidth-115*xFactor);
        cherryCount.setLayoutY(90*yFactor);


        //ScoreCount
        scoreCount=new InGameScoreCount(xFactor,yFactor,areaFactor,200*xFactor,100*yFactor);
        scoreCount.setLayoutX(sceneWidth/2-90*xFactor);
        scoreCount.setLayoutY(80*yFactor);


        if(resumeGame){
            lifeCount.setHeartCount((currentUser.getUserGameState()).getLifeCountStore());
            cherryCount.setCherryCount((currentUser.getUserGameState()).getCherryCountStore());
            scoreCount.setScoreCount((currentUser.getUserGameState()).getScoreCountStore());
            uiNum=(currentUser.getUserGameState()).getUiNumStore();
            lifeCount.heartDisplay();
            cherryCount.cherryDisplay();
        }
        else{
            uiNum= random.nextInt(8) ;
        }

        InGameUIMap uiMap=new InGameUIMap();
        uiMap.getUi(uiNum);
        int inGameBackgroundType = uiMap.getCurrentBackground() ;
        pillarType=uiMap.getCurrentPillar();
        int stickType =uiMap.getCurrentSticks();



        double pillarHeight=500*yFactor;
        double pillarYCord=445*yFactor;

        double initialPillarEnd=640*xFactor;

        int randomFirstPillar=random.nextInt(5)+1;
        int randomSecondPillar=random.nextInt(5)+1;


        double firstPillarXCord=initialPillarEnd-(24+46*randomFirstPillar);
        double secondPillarXCord=random.nextInt((int)(300*xFactor))+710*xFactor;


        double firstPillarWidth=(24+46*randomFirstPillar);
        double secondPillarWidth=(24+46*randomSecondPillar);


        int randomThirdPillar=random.nextInt(5)+1;
        double thirdPillarXCord=random.nextInt((int)(300*xFactor))+710*xFactor;
        double thirdPillarWidth=(24+46*randomThirdPillar);


        Tower firstTower ;
        Tower secondTower;
        Tower thirdTower;

        if(resumeGame){
            towerXCord=(currentUser.getUserGameState()).getTowerXCoordinateStore();
            towerWidth=(currentUser.getUserGameState()).getTowerWidthsStore();

            firstTower=(currentUser.getUserGameState()).getStartTower();
            secondTower=(currentUser.getUserGameState()).getInterTower();
            thirdTower=(currentUser.getUserGameState()).getEndTower();
        }
        else{
            towerXCord=new ArrayList();
            towerWidth=new ArrayList();


            towerXCord.add(firstPillarXCord);
            towerXCord.add(secondPillarXCord);


            towerWidth.add(firstPillarWidth);
            towerWidth.add(secondPillarWidth);


            towerXCord.add(thirdPillarXCord);
            towerWidth.add(thirdPillarWidth);


            firstTower = new Tower(towerWidth.get(0),pillarHeight,towerXCord.get(0),pillarYCord,pillarType,xFactor,yFactor,areaFactor);
            secondTower= new Tower(towerWidth.get(1),pillarHeight,towerXCord.get(1),pillarYCord,pillarType,xFactor,yFactor,areaFactor);
            thirdTower = new Tower(towerWidth.get(2),pillarHeight,towerXCord.get(2),pillarYCord,pillarType,xFactor,yFactor,areaFactor);

        }










        InGameBackground backgroundOfGame= new InGameBackground(sceneWidth,sceneHeight,areaFactor,xFactor,yFactor,inGameBackgroundType);







        int rBee=random.nextInt(5);
        int rCherry=random.nextInt(5);

        beeStatus=false;
        cherryStatus=false;

        beeStatus= rBee % 3 == 0;
        cherryStatus= rCherry % 2 == 0;

        int dist=(int)((towerXCord.get(1)-initialPillarEnd)/(2));

        if(cherryStatus){
            int xPos1=random.nextInt(dist);
            int yAxis1=random.nextInt(20);
            if(yAxis1%3==0){
                cherryYAxis=2;
                cherryYCord=470*yFactor;
                cherryView.setLayoutY(cherryYCord);
            }
            else {
                cherryYAxis=1;
                cherryYCord=390*yFactor;
                cherryView.setLayoutY(cherryYCord);
            }
            cherryXCord=xPos1+initialPillarEnd+dist/2;
            cherryView.setLayoutX(cherryXCord);
            cherryView.setOpacity(1);
        }
        else{
            cherryView.setOpacity(0);
        }
        if(beeStatus){
            int xPos2=random.nextInt(dist);
            int yAxis2=random.nextInt(20);
            if(yAxis2%3==0){

                beeYCord=470*yFactor;
                beeView.setLayoutY(beeYCord);
            }
            else {
                beeYAxis=1;
                beeYCord=390*yFactor;
                beeView.setLayoutY(beeYCord);
            }
            beeXCord=xPos2+initialPillarEnd+dist/2;
            beeView.setLayoutX(beeXCord);
            beeView.setOpacity(1);
        }
        else{
            beeView.setOpacity(0);
        }

        cb=new CherryAndBee(cherryXCord,beeXCord,cherryStatus,beeStatus,cherryYAxis,beeYAxis,beeYCord,cherryYCord,cherryView.getBoundsInParent(),beeView.getBoundsInParent());

        startTower=firstTower;
        interTower=secondTower;
        endTower=thirdTower;
        endTower.setOpacity(0);




        stickGenerate = new StickGenerate(sceneWidth,sceneHeight,635*xFactor,438*yFactor,stickType);
        stickAlready=false;

        gameStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                handleCloseRequest(event);
            }
        });

        inGameScreenPaneRoot.getChildren().addAll(backgroundOfGame);
        inGameScreenPaneRoot.getChildren().addAll(startTower,interTower,endTower);

        double characterGirlX= (double) 189 /195;
        double characterGirlY= (double) 362 /355;

        double characterHatX= (double) 180 /195;
        double characterHatY= (double) 362 /355;

        double generalCharacterX= 590*xFactor;
        double generalCharacterY= 355*yFactor;



        inGameScreenPaneRoot.getChildren().addAll(stickGenerate);
        inGameScreenPaneRoot.getChildren().addAll(lifeCount,cherryCount,scoreCount);


        if(playingCharacter==0){
            gameCharacter=new BoyAnimation(generalCharacterX,generalCharacterY,xFactor,yFactor,areaFactor);
            inGameScreenPaneRoot.getChildren().add((BoyAnimation)gameCharacter);
        }
        else if(playingCharacter==1){
            gameCharacter=new GirlAnimation(generalCharacterX*characterGirlX,characterGirlY*generalCharacterY,xFactor,yFactor,areaFactor);
            inGameScreenPaneRoot.getChildren().add((GirlAnimation)gameCharacter);
        }
        else if((playingCharacter==2)){
            gameCharacter=new HatBoyAnimation(characterHatX*generalCharacterX,characterHatY*generalCharacterY,xFactor,yFactor,areaFactor);
            inGameScreenPaneRoot.getChildren().add((HatBoyAnimation)gameCharacter);
        }

        inGameScreenPaneRoot.getChildren().addAll(cherryView,beeView);
                //startButton1);

        Scene scene = new Scene(inGameScreenPaneRoot, sceneWidth, sceneHeight);


        backgroundTimeline = new Timeline(
                new KeyFrame(Duration.millis(30 ), event -> {
                    backgroundOfGame.backgroundMove(2.00 ,inGameBackgroundType);
                })
        );

        backgroundTimeline.setCycleCount(Timeline.INDEFINITE);

        this.stickLength = 0.0 ;
        scene.setOnKeyPressed(event -> handleKeyPress(event,stickGenerate));
        scene.setOnKeyReleased(event -> handleKeyRelease(event,stickGenerate));

        startStage.hide();
        gameStage.setScene(scene);
        gameStage.setTitle("Game Stage");
        gameStage.show();
    }

    private void pillarMoveTime(){
        mS.towerUpdateFunc();

        inGameScreenPaneRoot.getChildren().remove(stickGenerate);

        stickLength=0;


        double characterGirlX= (double) 189 /195;
        double characterGirlY= (double) 362 /355;

        double characterHatX= (double) 180 /195;
        double characterHatY= (double) 362 /355;

        double generalCharacterX= 590*xFactor;
        double generalCharacterY= 355*yFactor;

        final Timeline[] timeline = {null,null,null};

        final double[] right = {640*xFactor};
        final double[] center ={towerXCord.get(1)+towerWidth.get(1)-640*xFactor};
        final double[] left = {sceneWidth-towerXCord.get(2)};

        final int[] tell={4};

        if(right[0]/10>center[0]/5 && right[0]/10>left[0]/10){
            tell[0]=0;
        }
        else if(center[0]/5>right[0]/10 && center[0]/5>left[0]/10){
            tell[0]=1;
        }
        else if(left[0]/10>right[0]/10 && left[0]/10>center[0]/5){
            tell[0]=2;
        }


        timeline[0] = new Timeline(
                new KeyFrame(Duration.millis(30), event1 -> {
                    right[0] = right[0] - 10;
                    startTower.setLayoutX(right[0]);
                    startTower.setOpacity(right[0]/640*xFactor);
                    if(right[0]<=0){
                        timeline[0].stop();
                        if(tell[0]==0){
                            orderPillar();
                        }
                    }
                })

        );
        timeline[0].setCycleCount(Timeline.INDEFINITE);

        timeline[1] = new Timeline(
                new KeyFrame(Duration.millis(30), event1 -> {
                    center[0] = center[0] - 5;
                    interTower.setLayoutX(center[0]+640*xFactor-towerWidth.get(1));
                    if(cherryStatus && !cherryPick){
                        cherryView.setOpacity(center[0]/(towerXCord.get(1)+towerWidth.get(1)-640*xFactor));
                    }
                    if(beeStatus){
                        beeView.setOpacity(center[0]/(towerXCord.get(1)+towerWidth.get(1)-640*xFactor));
                    }
                    if(playingCharacter==0 && !fallDown){
                        ((BoyAnimation)gameCharacter).setLayoutX(center[0]+generalCharacterX);
                    }
                    else if(playingCharacter==1 && !fallDown){
                        ((GirlAnimation)gameCharacter).setLayoutX(center[0]+generalCharacterX*characterGirlX);
                    }
                    else if(playingCharacter==2 && !fallDown){
                        ((HatBoyAnimation)gameCharacter).setLayoutX(center[0]+generalCharacterX*characterHatX);
                    }
                    if(center[0]<=0){
                        timeline[1].stop();
                        if(tell[0]==1){
                            orderPillar();
                            if(playingCharacter==0 && !fallDown){
                                ((BoyAnimation)gameCharacter).setLayoutX(generalCharacterX);
                            }
                            else if(playingCharacter==1 && !fallDown){
                                ((GirlAnimation)gameCharacter).setLayoutX(generalCharacterX*characterGirlX);
                            }
                            else if(playingCharacter==2 && !fallDown){
                                ((HatBoyAnimation)gameCharacter).setLayoutX(generalCharacterX*characterHatX);
                            }
                        }
                    }
                })
        );
        timeline[1].setCycleCount(Timeline.INDEFINITE);

        timeline[2] = new Timeline(
                new KeyFrame(Duration.millis(30), event1 -> {
                    left[0] = left[0] - 10;
                    endTower.setLayoutX(left[0]+towerXCord.get(2));
                    endTower.setOpacity(1-(left[0]/(sceneWidth-towerXCord.get(2))));
                    if(left[0]<=0){
                        timeline[2].stop();
                        if(tell[0]==2){
                            orderPillar();
                        }
                    }
                })
        );
        timeline[2].setCycleCount(Timeline.INDEFINITE);

        timeline[0].play();
        timeline[1].play();
        timeline[2].play();

        startPillarTimeline=timeline[0];
        midPillarTimeline=timeline[1];
        endPillarTimeline=timeline[2];




    }

    private void orderPillar(){
        mS.towerUpdateFunc();
        Random random =new Random();

        double pillarHeight=345*yFactor;
        double pillarYCord=445*yFactor;

        int randomThirdPillar=random.nextInt(5)+1;
        double thirdPillarXCord=random.nextInt((int)(300*xFactor))+710*xFactor;
        double thirdPillarWidth=(24+46*randomThirdPillar);




        startTower=null;
        startTower=interTower;
        interTower=endTower;
        endTower = new Tower(thirdPillarWidth,pillarHeight,thirdPillarXCord,pillarYCord,pillarType,xFactor,yFactor,areaFactor);
        endTower.setOpacity(0);
        inGameScreenPaneRoot.getChildren().add(endTower);



        towerWidth.set(0,towerWidth.get(1));
        towerWidth.set(1,towerWidth.get(2));
        towerWidth.set(2,thirdPillarWidth);

        towerXCord.set(0,towerXCord.get(1));
        towerXCord.set(1,towerXCord.get(2));
        towerXCord.set(2,thirdPillarXCord);


        int rBee=random.nextInt(5);
        int rCherry=random.nextInt(5);

        beeStatus=false;
        cherryStatus=false;

        beeStatus= rBee % 3 == 0;
        cherryStatus= rCherry % 2 == 0;

        double initialPillarEnd=640;

        int dist=(int)((towerXCord.get(1)-initialPillarEnd)/(2));

        if(cherryStatus){
            int xPos1=random.nextInt(dist);
            int yAxis1=random.nextInt(20);
            if(yAxis1%3==0){
                cherryYAxis=2;
                cherryYCord=470*yFactor;
                cherryView.setLayoutY(cherryYCord);
            }
            else {
                cherryYAxis=1;
                cherryYCord=390*yFactor;
                cherryView.setLayoutY(cherryYCord);
            }
            cherryXCord=xPos1+initialPillarEnd+dist/2;
            cherryView.setLayoutX(cherryXCord);
            cherryView.setOpacity(1);
        }
        else{
            cherryView.setOpacity(0);
        }
        if(beeStatus){
            int xPos2=random.nextInt(dist);
            int yAxis2=random.nextInt(20);
            if(yAxis2%3==0){

                beeYCord=470*yFactor;
                beeView.setLayoutY(beeYCord);
            }
            else {
                beeYAxis=1;
                beeYCord=390*yFactor;
                beeView.setLayoutY(beeYCord);
            }
            beeXCord=xPos2+initialPillarEnd+dist/2;
            beeView.setLayoutX(beeXCord);
            beeView.setOpacity(1);
        }
        else{
            beeView.setOpacity(0);
        }
        cb.CherryAndBeeSet(cherryXCord,beeXCord,cherryStatus,beeStatus,cherryYAxis,beeYAxis,beeYCord,cherryYCord,cherryView.getBoundsInParent(),beeView.getBoundsInParent());

        stickGenerate = new StickGenerate(sceneWidth,sceneHeight,635*xFactor,437*yFactor,0);
        stickAlready=false;
        inGameScreenPaneRoot.getChildren().add(stickGenerate);
    }

    private void handleKeyPress(KeyEvent event,StickGenerate stickGenerate) {
        if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) {
            if(!stickAlready) {
                mS.stickCreateFunc();
                this.stickLength = this.stickLength + 8;
                stickGenerate.drawStick(-90, this.stickLength);
            }
        }
        else if( event.getCode() == KeyCode.S || event.getCode() == KeyCode.SPACE ){
            mS.toggleFunc();
            if(playingCharacter==0){
                ((BoyAnimation)gameCharacter).setReverse();
            }
            else if(playingCharacter==1){
                ((GirlAnimation)gameCharacter).setReverse();
            }
            else if(playingCharacter==2){
                ((HatBoyAnimation)gameCharacter).setReverse();
            }
        }
    }

    private void handleKeyRelease(KeyEvent event, StickGenerate stickGenerate) {
        mS.stickFallFunc();
        final double[] start = {-90};
        final Timeline[] timeline = {null};

        if (event.getCode() == KeyCode.W && !stickAlready) {
            final double[] startArray = {start[0]};  // Use a separate array

            timeline[0] = new Timeline(
                    new KeyFrame(Duration.millis(30), event1 -> {
                        startArray[0] = startArray[0] + 3;
                        stickGenerate.drawStick(startArray[0], this.stickLength);
                        if (startArray[0] >= 0) {
                            stopStick(timeline[0],backgroundTimeline);
                        }
                    })
            );
            timeline[0].setCycleCount(Timeline.INDEFINITE);
            timeline[0].play();

            stickAlready = true;
            stickFallTimeline=timeline[0];
        }
    }



    private void stopStick(Timeline timeline,Timeline backgroundTimeline) {
        mS.stickFallFunc();
        double db1=stickLength+640*xFactor-(towerXCord.get(1)+towerWidth.get(1)/2);
        if(db1<=10 && db1>=-10){
            scoreCount.addHalfScore();
            Text halfText=new Text("+50");
            halfText.setLayoutX(towerXCord.get(1)+towerWidth.get(1)/2);
            halfText.setLayoutY(400*yFactor);
            halfText.setStyle("-fx-font-family: 'Soup of Justice'; -fx-font-size: "+5*areaFactor+"; -fx-fill: #880808;");
            inGameScreenPaneRoot.getChildren().add(halfText);

            ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), halfText);
            scaleTransition.setToX(4*areaFactor);  // final X scale (1 is original size)
            scaleTransition.setToY(4*areaFactor);
            scaleTransition.play();

            PauseTransition pauseTransition = new PauseTransition(Duration.seconds(1));
            pauseTransition.setOnFinished(event -> {
                inGameScreenPaneRoot.getChildren().remove(halfText);
            });

            pauseTransition.play();
        }
        timeline.stop();
        cherryPick=false;
        beeCaught=false;
        fallDown=false;
        if(playingCharacter==0){
            ((BoyAnimation)gameCharacter).createRunAnimation(towerWidth.get(1)+towerXCord.get(1),towerXCord.get(1),stickLength,backgroundTimeline,event -> pillarMoveTime(),this::collisonOccur,cb);
        }
        else if(playingCharacter==1){
            ((GirlAnimation)gameCharacter).createRunAnimation(towerWidth.get(1)+towerXCord.get(1),towerXCord.get(1),stickLength,backgroundTimeline,event -> pillarMoveTime(),this::collisonOccur,cb);
        }
        else if(playingCharacter==2){
            ((HatBoyAnimation)gameCharacter).createRunAnimation(towerWidth.get(1)+towerXCord.get(1),towerXCord.get(1),stickLength,backgroundTimeline,event -> pillarMoveTime(),this::collisonOccur,cb);
        }
    }

    public void collisonOccur(ActionEvent event1){
        int userData = ((CustomActionEvent) event1).getUserData();
        if(userData==1 && !cherryPick){
            mS.cherryEncounterFunc();
            cherryPick=true;
            cherryCount.addCherry();
            mS.cherryCountFunc();
            cherryView.setOpacity(0);
            if(cherryCount.getCherryCount()>=5){
                if(lifeCount.getHeartCount()<3){
                    lifeCount.addHeart();
                    cherryCount.removeCherry();
                    if(playingCharacter==0){
                        ((BoyAnimation)gameCharacter).setHeartCount1(lifeCount.getHeartCount());
                    }
                    else if(playingCharacter==1){
                        ((GirlAnimation)gameCharacter).setHeartCount1(lifeCount.getHeartCount());
                    }
                    else if (playingCharacter==2) {
                        ((HatBoyAnimation)gameCharacter).setHeartCount1(lifeCount.getHeartCount());
                    }
                }
            }
        }
        else if(userData==2 && !beeCaught){
            mS.beeEncounterFunc();
            beeCaught=true;
            lifeCount.removeHeart();
            mS.healthCountFunc();
            if(playingCharacter==0){
                ((BoyAnimation)gameCharacter).setHeartCount1(lifeCount.getHeartCount());
            }
            else if(playingCharacter==1){
                ((GirlAnimation)gameCharacter).setHeartCount1(lifeCount.getHeartCount());
            }
            else if (playingCharacter==2) {
                ((HatBoyAnimation)gameCharacter).setHeartCount1(lifeCount.getHeartCount());
            }
            if(lifeCount.getHeartCount()<=0){
                afterGame();
            }
        }
        else if(userData==3 && !fallDown){
            mS.fallEndFunc();
            fallDown=true;
            lifeCount.removeHeart();
            mS.healthCountFunc();
            if(playingCharacter==0){
                ((BoyAnimation)gameCharacter).setHeartCount1(lifeCount.getHeartCount());
            }
            else if(playingCharacter==1){
                ((GirlAnimation)gameCharacter).setHeartCount1(lifeCount.getHeartCount());
            }
            else if (playingCharacter==2) {
                ((HatBoyAnimation)gameCharacter).setHeartCount1(lifeCount.getHeartCount());
            }
            if(lifeCount.getHeartCount()<=0){
                afterGame();
            }
            else{
                pillarMoveTime();
            }
        }
        else if(userData==4){
            mS.enterPressSound();
            scoreCount.addScore();
        }
        else if(userData==5){
            afterGame();
        }
    }


    public void afterGame(){
        if(currentUser.getHighestScore()<scoreCount.getScoreCount()){
            currentUser.setHighestScore(scoreCount.getScoreCount());
        }
        fileManager.setUserData(userData);
        mS.fallEndFunc();

        userData.sortArr();
        userInScoreBoard();
        currentUser.setUserGameState(null);
        handleCloseRequestStatus=false;

        Region rootTransparent = new Region();
        rootTransparent.setLayoutX(0);
        rootTransparent.setLayoutY(0);
        rootTransparent.setPrefWidth(sceneWidth);
        rootTransparent.setPrefHeight(sceneHeight);
        rootTransparent.setStyle("-fx-background-color: rgba(242, 243, 245,0.5);");



        currentUser.setData(scoreCount.getScoreCount());


        GameFinish gameFinish = new GameFinish(sceneWidth / 2, sceneHeight / 2, areaFactor, xFactor, yFactor,currentUser.getUserName(),currentUser.getUserScore(),currentUser.getHighestScore());
        gameFinish.setLayoutX(sceneWidth / 2 - 200 * areaFactor);
        gameFinish.setLayoutY(sceneHeight / (3.5));


        gameFinish.setOnExitButtonPressed(event1 -> exitNewGame());
        gameFinish.setOnBackButtonPressed(event1 -> backNewGame());
        gameFinish.setOnResumeButtonPressed(event1 -> resumeNewGame(inGameScreenPaneRoot, rootTransparent, gameFinish));

        inGameScreenPaneRoot.getChildren().addAll(rootTransparent, gameFinish);

    }
    public void handleCloseRequest(WindowEvent event){

        event.consume();
        responsivenessCheckPassed=false;


        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> {
            if (responsivenessCheckPassed) {
                executorService.shutdownNow();
            }
            else {
                System.out.println("No user interaction. Closing application...");
                Platform.exit();
            }
        }, 10, TimeUnit.SECONDS);



        if(handleCloseRequestStatus) {
            mS.buttonPressSound();
            Region rootTransparent = new Region();
            rootTransparent.setLayoutX(0);
            rootTransparent.setLayoutY(0);
            rootTransparent.setPrefWidth(sceneWidth);
            rootTransparent.setPrefHeight(sceneHeight);
            rootTransparent.setStyle("-fx-background-color: rgba(242, 243, 245,0.5);");

            ExitWindow exitWindow = new ExitWindow(sceneWidth / 5, sceneHeight / 2, areaFactor, xFactor, yFactor);
            exitWindow.setLayoutX(sceneWidth / 2 - 150 * areaFactor);
            exitWindow.setLayoutY(sceneHeight / (3.5));


            exitWindow.setOnExitButtonPressed(event1 -> exitGame());
            exitWindow.setOnBackButtonPressed(event1 -> backGame());
            exitWindow.setOnResumeButtonPressed(event1 -> resumeGame(inGameScreenPaneRoot, rootTransparent, exitWindow));

            inGameScreenPaneRoot.getChildren().addAll(rootTransparent, exitWindow);
            handleCloseRequestStatus=false;
        }
    }

    public void exitGame() {
        mS.buttonPressSound();
        responsivenessCheckPassed=true;
        executorService.shutdownNow();
        Platform.exit();
        handleCloseRequestStatus = true;
    }

    public void backGame() {
        mS.buttonPressSound();
        responsivenessCheckPassed=true;
        executorService.shutdownNow();
        gameStage.hide();
        startStage.show();
        onGamePause();
        handleCloseRequestStatus = true;
    }

    public void resumeNewGame(Pane root, Region rootTransparent, GameFinish gameFinish) {
        mS.buttonPressSound();
        handleCloseRequestStatus=true;
        root.getChildren().removeAll(rootTransparent, gameFinish);
        inGameScreen();
    }


    public void exitNewGame() {
        mS.buttonPressSound();
        handleCloseRequestStatus=true;
        Platform.exit();
    }

    public void backNewGame() {
        mS.buttonPressSound();
        handleCloseRequestStatus=true;
        gameStage.hide();
        startStage.show();
    }

    public void resumeGame(Pane root, Region rootTransparent, ExitWindow exitWindow) {
        mS.buttonPressSound();
        responsivenessCheckPassed=true;
        executorService.shutdownNow();
        root.getChildren().removeAll(rootTransparent, exitWindow);
        handleCloseRequestStatus = true;
    }


    public void onGamePause(){
        mS.buttonPressSound();
        GameState gs=new GameState(currentUser,towerXCord,towerWidth,playingCharacter,cherryCount.getCherryCount(),lifeCount.getHeartCount(),scoreCount.getScoreCount(),uiNum,startTower,interTower,endTower);
        currentUser.setUserGameState(gs);
        fileManager.setUserData(userData);
        gameStage.hide();
        startStage.show();
    }

    public void resumeGameStateFunc(Pane root,Button buttonE,TextField textfieldE,Label labelE,int i1){
        mS.buttonPressSound();
        if(currentUser.getUserGameState()!=null){
            resumeGame=true;
            playingCharacter=(currentUser.getUserGameState()).getPlayingCharacterStore();
            inGameScreen();
        }
        else{
            startGame(root,buttonE,textfieldE,labelE,1);
        }

    }
}