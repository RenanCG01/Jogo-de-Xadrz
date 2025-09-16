package application;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class SoundManager {

    private static void playSound(String soundName) {
        try {
            // O caminho para o som começa na raiz do classpath (pasta 'src')
            URL url = SoundManager.class.getResource("/sounds/" + soundName);
            if (url == null) {
                System.err.println("Arquivo de som não encontrado: " + soundName);
                return;
            }
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            System.err.println("Erro ao tocar o som: " + soundName);
            e.printStackTrace();
        }
    }

    public static void playMoveSound() {
        playSound("move.wav");
    }

    public static void playCaptureSound() {
        playSound("capture.wav");
    }

    public static void playCheckSound() {
        playSound("check.wav");
    }

    public static void playCheckMateSound() {
        playSound("checkmate.wav");
    }
}