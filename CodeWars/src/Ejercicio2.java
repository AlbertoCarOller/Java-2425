public class Ejercicio2 {
    public static void main(String[] args) {
        System.out.println(rps("paper", "paper"));
    }

    // Hacemos el método
    public static String rps(String p1, String p2) {

        if (p1.equalsIgnoreCase(p2)) {
            return "Draw!";

        } else if (p1.equalsIgnoreCase("rock") && p2.equalsIgnoreCase("paper")) {
            return "Player 2 won!";

        } else if (p2.equalsIgnoreCase("rock") && p1.equalsIgnoreCase("paper")) {
            return "Player 1 won!";

        } else if (p1.equalsIgnoreCase("rock")) {
            return "Player 1 won!";

        } else if (p2.equalsIgnoreCase("rock")) {
            return "Player 2 won!";

        } else if (p1.equalsIgnoreCase("scissors")) {
            return "Player 1 won!";
        }
        return "Player 2 won!";
    }
}