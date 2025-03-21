public class Ejercicio1 {

    static final int TAMANO_TABLERO = 64;

    public static void main(String[] args) {

        // Creamos un array con las 64 casillas del tablero
        int[] tablero = new int[TAMANO_TABLERO];
        // Creamos un array con los 4 jugadores
        String[] jugadores = {"Amarillo", "Verde", "Rojo", "Naranja"};
        // Creamos un array con el número obtenido por cada jugador al tirar el dado
        int[] tiradas = new int[4];

        casillasEspeciales(tablero);
        dado(jugadores, tiradas);
        comprobarIguales(tiradas, jugadores);
        ordenDeTirada(tiradas, jugadores);
        // Creamos un array que va a contener la posición de cada jugador en el tablero
        int[] posicionJugador = new int[4];

        while (!jugarEnTablero(posicionJugador, jugadores, tablero, tiradas)) {

            dado(jugadores, tiradas);
            jugarEnTablero(posicionJugador, jugadores, tablero, tiradas);
        }
    }

    // Tiramos el dado y guardamos el valor en el array 'tiradas'
    private static void dado(String[] jugadores, int[] tiradas) {

        for (int i = 0; i < jugadores.length; i++) {

            int tirada = (int) (Math.random() * 6) + 1;
            tiradas[i] = tirada;
            System.out.println("Jugador " + jugadores[i] + " has sacado un " + tirada);
        }
    }

    // Ordenamos el array 'tiradas' de mayor a menor
    private static void ordenDeTirada(int[] tiradas, String[] jugadoresOrden) {

        int temp;
        String temp2;

        for (int i = 0; i < tiradas.length; i++) {

            for (int j = 0; j < tiradas.length - 1; j++) {

                if (tiradas[j] < tiradas[j + 1]) {

                    // Ordenamos la puntuación
                    temp = tiradas[j];
                    tiradas[j] = tiradas[j + 1];
                    tiradas[j + 1] = temp;

                    // Ordenamos los nombres de los jugadores
                    temp2 = jugadoresOrden[j];
                    jugadoresOrden[j] = jugadoresOrden[j + 1];
                    jugadoresOrden[j + 1] = temp2;
                }
            }
        }
    }

    // Comprobamos si hay empate y en caso de que sí, volvemos a tirar los dados
    private static void comprobarIguales(int[] tiradas, String[] jugadores) {

        for (int i = 0; i < tiradas.length; i++) {

            for (int j = 1; j < tiradas.length; j++) {

                // Hacemos esto para que no compare una posición con ella misma
                if (i == j) {

                    continue;
                }

                if (tiradas[i] == tiradas[j]) {

                    System.out.println("Los jugadores " + jugadores[i] + " y "
                            + jugadores[j] + " han sacado lo mismo");

                    int tiradaDeNuevo = (int) (Math.random() * 6) + 1;
                    tiradas[i] = tiradaDeNuevo;

                    int tiradaDeNuevo2 = (int) (Math.random() * 6) + 1;
                    tiradas[j] = tiradaDeNuevo2;
                }
            }
        }
    }

    // Colocamos las casillas especiales
    private static void casillasEspeciales(int[] tablero) {

        for (int i = 0; i < tablero.length; i++) {

            if (i % 4 == 0) {

                tablero[i] = 2; // Los 2 son ocas
            }

            tablero[62] = -1; // El -1 es la muerte
            tablero[63] = 1; // El 1 es la victoria
        }
    }

    // Ponemos en acción las casillas especiales
    private static boolean jugarEnTablero(int[] posicionJugador, String[] jugadores, int[] tablero, int[] tirada) {

        for (int i = 0; i < posicionJugador.length; i++) {

            if (posicionJugador[i] >= tablero.length) {

                posicionJugador[i] = posicionJugador[i] - tablero.length;
                System.out.println("Has ganado " + jugadores[i]);
                return true;
            }

            if (tablero[posicionJugador[i]] == 2) {

                System.out.println(jugadores[i] + " de oca en oca y tiro porque me toca");
                int tiradaOca = (int) (Math.random() * 6) + 1;
                posicionJugador[i] += tirada[i] + tiradaOca;
                System.out.println(jugadores[i] + " ha sacado un " + tiradaOca);
                System.out.println(jugadores[i] + " está en la posición " + posicionJugador[i]);

                if (posicionJugador[i] >= tablero.length) {

                    posicionJugador[i] = posicionJugador[i] - tablero.length;
                    System.out.println("Has ganado " + jugadores[i]);
                    return true;
                }
            }

            if (tablero[posicionJugador[i]] == 0) {
                System.out.println(jugadores[i] + " ha caído en una casilla normal");
                posicionJugador[i] += tirada[i];
                System.out.println(jugadores[i] + " está en la posición " + posicionJugador[i]);

                if (posicionJugador[i] >= tablero.length) {

                    posicionJugador[i] = posicionJugador[i] - tablero.length;
                    System.out.println("Has ganado " + jugadores[i]);
                    return true;
                }
            }

            if (tablero[posicionJugador[i]] == -1) {

                System.out.println("Has caído en la casilla de la muerte, empiezas de 0 " + jugadores[i]);
                posicionJugador[i] = 0;
            }
        }
        return false;
    }
}