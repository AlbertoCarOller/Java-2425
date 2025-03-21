package Boletin2.Ejercicio3;

public class iTunes implements OfflineMusicPlayer {

    // Implementamos el método
    @Override
    public void load() {
        System.out.println("Loading music on iTunes");
    }

    @Override
    public void play() {
        System.out.println("Play iTunes");
    }

    @Override
    public void stop() {
        System.out.println("Stop iTunes");
    }
}