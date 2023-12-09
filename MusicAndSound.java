package org.example;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
public class MusicAndSound{

    public  boolean musicAllow=true;
    public  boolean soundAllow=true;

    AudioClip backgroundMusic;

    MediaPlayer buttonPressPlayer;
    MediaPlayer enterPressPlayer;

    MediaPlayer stickFallPlayer;
    MediaPlayer beeEncounterPlayer;
    MediaPlayer cherryEncounterPlayer;
    MediaPlayer heartCountSoundPlayer;
    MediaPlayer cherryCountSoundPlayer;
    MediaPlayer updateTowerPlayer;
    MediaPlayer chooseCharacterSoundPlayer;
    MediaPlayer stickGenerateSoundPlayer;
    MediaPlayer toggleSoundPlayer;
    MediaPlayer fallEndPlayer;

    MusicAndSound() {
        try {
            Media buttonPress = new Media(getClass().getResource("/Audio/zapTwoTone.mp3").toExternalForm());
            buttonPressPlayer = new MediaPlayer(buttonPress);

            Media enterPress = new Media(getClass().getResource("/Audio/zap1.mp3").toExternalForm());
            enterPressPlayer = new MediaPlayer(enterPress);


            Media stickFall = new Media(getClass().getResource("/Audio/pepSound4.mp3").toExternalForm());
            stickFallPlayer = new MediaPlayer(stickFall);

            Media beeEncounter = new Media(getClass().getResource("/Audio/powerUp4.mp3").toExternalForm());
            beeEncounterPlayer = new MediaPlayer(beeEncounter);

            Media cherryEncounter = new Media(getClass().getResource("/Audio/powerUp8.mp3").toExternalForm());
            cherryEncounterPlayer = new MediaPlayer(cherryEncounter);

            Media heartCountSound = new Media(getClass().getResource("/Audio/pepSound2.mp3").toExternalForm());
            heartCountSoundPlayer = new MediaPlayer(heartCountSound);

            Media cherryCountSound = new Media(getClass().getResource("/Audio/pepSound1.mp3").toExternalForm());
            cherryCountSoundPlayer = new MediaPlayer(cherryCountSound);

            Media updateTower = new Media(getClass().getResource("/Audio/laser8.mp3").toExternalForm());
            updateTowerPlayer = new MediaPlayer(updateTower);

            Media chooseCharacterSound = new Media(getClass().getResource("/Audio/zap2.mp3").toExternalForm());
            chooseCharacterSoundPlayer = new MediaPlayer(chooseCharacterSound);

            Media stickGenerateSound = new Media(getClass().getResource("/Audio/highUp.mp3").toExternalForm());
            stickGenerateSoundPlayer = new MediaPlayer(stickGenerateSound);

            Media toggleSound = new Media(getClass().getResource("/Audio/phaseJump5.mp3").toExternalForm());
            toggleSoundPlayer = new MediaPlayer(toggleSound);

            Media fallEnd = new Media(getClass().getResource("/Audio/highDown.mp3").toExternalForm());
            fallEndPlayer = new MediaPlayer(fallEnd);

            addEndOfMediaListener(buttonPressPlayer);
            addEndOfMediaListener(enterPressPlayer);
            addEndOfMediaListener(stickFallPlayer);
            addEndOfMediaListener(beeEncounterPlayer);
            addEndOfMediaListener(cherryEncounterPlayer);
            addEndOfMediaListener(heartCountSoundPlayer);
            addEndOfMediaListener(cherryCountSoundPlayer);
            addEndOfMediaListener(updateTowerPlayer);
            addEndOfMediaListener(chooseCharacterSoundPlayer);
            addEndOfMediaListener(stickGenerateSoundPlayer);
            addEndOfMediaListener(toggleSoundPlayer);
            addEndOfMediaListener(fallEndPlayer);
        }
        catch(Exception e){
            System.err.println("Error loading media files: " + e.getMessage());
            e.printStackTrace();
        }

    }
    private void addEndOfMediaListener(MediaPlayer player) {
        player.setOnEndOfMedia(() -> player.stop());
    }

    public  void buttonPressSound(){
        if(soundAllow){
            buttonPressPlayer.play();
        }
    }
    public  void enterPressSound(){
        if(soundAllow){
            enterPressPlayer.play();
        }
    }
    public  void stickFallFunc(){
        if(soundAllow){
            stickFallPlayer.play();
        }
    }
    public  void beeEncounterFunc(){
        if(soundAllow){
            beeEncounterPlayer.play();
        }
    }
    public  void cherryEncounterFunc(){
        if(soundAllow){
            cherryEncounterPlayer.play();
        }
    }
    public  void healthCountFunc(){
        if(soundAllow){
            heartCountSoundPlayer.play();
        }
    }
    public  void cherryCountFunc(){
        if(soundAllow){
            cherryCountSoundPlayer.play();
        }
    }
    public  void towerUpdateFunc(){
        if(soundAllow){
            updateTowerPlayer.play();
        }
    }
    public  void characterChooseFunc(){
        if(soundAllow){
            chooseCharacterSoundPlayer.play();
        }
    }
    public  void stickCreateFunc(){
        if(soundAllow){
            stickGenerateSoundPlayer.play();
        }
    }
    public  void toggleFunc(){
        if(soundAllow){
            toggleSoundPlayer.play();
        }
    }
    public  void fallEndFunc(){
        if(soundAllow){
            fallEndPlayer.play();
        }
    }
    public void backgroundMusicFunc(){
        backgroundMusic= new AudioClip(getClass().getResource("/Audio/backgroundMusicMid.mp3").toString());
        backgroundMusic.setCycleCount(AudioClip.INDEFINITE); // Repeat indefinitely
        backgroundMusic.play();

    }

    public void backgroundMusicPlay(){
        backgroundMusicFunc();
    }
    public void backgroundMusicPause(){
        backgroundMusic.stop();

    }

}
