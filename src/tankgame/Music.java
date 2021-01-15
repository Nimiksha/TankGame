package tankgame;

import java.io.FileNotFoundException;
import java.io.IOException;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

//adds audio elements to the tank game
//Credits: Stack Overflow
public class Music {

    public void playMusic() {
        AudioPlayer audioPlayer = AudioPlayer.player ;
        AudioStream backgroundMusic;
        AudioData audioData;
        ContinuousAudioDataStream audioLoop = null;

        //reads the audio file from the resources and plays it on loop
        try {
            backgroundMusic = new AudioStream(this.getClass().getResourceAsStream("/resources/backgroundSound.wav"));
            audioData = backgroundMusic.getData();
            audioLoop = new ContinuousAudioDataStream(audioData);
        }
        catch (IOException e) {
            System.out.println("File not found");
        }
        audioPlayer.start(audioLoop);  //loop the background music
    }

    //explosion sound for when the tank shoots and hits something
    public void explosion() {

        try {
            String soundFile = "/resources/explosionSound.wav";
            AudioStream audioStream = new AudioStream(this.getClass().getResourceAsStream(soundFile));
            AudioPlayer.player.start(audioStream);
        }
        catch (FileNotFoundException exc) {
            exc.printStackTrace();
        }
        catch (IOException io) {
            io.printStackTrace();
        }
    }

    //explosion sound for when the tank shoots and hits something
    public void powerUpSound() {

        try {
            String soundFile = "/resources/BonusSound.wav";
            AudioStream audioStream = new AudioStream(this.getClass().getResourceAsStream(soundFile));
            AudioPlayer.player.start(audioStream);
        }
        catch (FileNotFoundException exc) {
            exc.printStackTrace();
        }
        catch (IOException io) {
            io.printStackTrace();
        }
    }

}