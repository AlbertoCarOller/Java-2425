package Boletin2.Ejercicio1;

public class EsportsTournament {
    public static void main(String[] args) {
        Gamer fps = new FPSGamer();
        Gamer moba = new MOBAGamer();
        Gamer sports = new SportsGamer();
        Gamer[] gamers = {fps, moba, sports};
        startTournament(gamers);
    }

    public static void startTournament(Gamer[] gamers) {
        for (Gamer gamer : gamers) {
            gamer.playGame();
        }
    }
}
