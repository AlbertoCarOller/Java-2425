package Boletin2.Ejercicio3;

public class MP3Player implements OfflineMusicPlayer {

    // Implementamos el método
    @Override
    public void load() {
        System.out.println("Playing music on MP3 Player");
    }

    @Override
    public void play() {
        System.out.println("Play MP3Player");
    }

    @Override
    public void stop() {
        System.out.println("Stop MP3Player");
    }
}