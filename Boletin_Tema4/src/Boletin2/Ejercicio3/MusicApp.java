package Boletin2.Ejercicio3;

public class MusicApp {
    public static void main(String[] args) {

        // Creamos los 'MusicPlayer'
        OnlineMusicPlayer spotify = new Spotify();
        OfflineMusicPlayer iTunes = new iTunes();
        OfflineMusicPlayer mp3 = new MP3Player();

        // Creamos un array de MusicPlayer
        MusicPlayer[] musicPlayers = {spotify, iTunes, mp3};
        startMusic(musicPlayers);
    }

    // Creamos el método que va a recorrer el array y llamar a los métodos
    public static void startMusic(MusicPlayer[] musicPlayers) {

        for (MusicPlayer musicPlayer : musicPlayers) {

            if (musicPlayer instanceof OnlineMusicPlayer onlineMusicPlayer) {
                onlineMusicPlayer.stream();
            }

            if (musicPlayer instanceof OfflineMusicPlayer offlineMusicPlayer) {
                offlineMusicPlayer.load();
            }

            musicPlayer.play();
        }
    }
}