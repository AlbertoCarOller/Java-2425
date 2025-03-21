package Boletin2.Ejercicio3;

public class Spotify implements OnlineMusicPlayer {

    // Implementamos el método
    @Override
    public void stream() {
        System.out.println("Streaming music on Spotify");
    }

    // Implementamos
    @Override
    public void play() {
        System.out.println("Play Spotify");
    }

    @Override
    public void stop() {
        System.out.println("Stop Spotify");
    }
}