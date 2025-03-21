import utils.MiEntradaSalida;

public class Ejercicio1 {

    // Creamos el tablero que va a tener las minas
    static int[][] tablero;
    // Creamos el tablero que mostramos al usuario
    static boolean[][] tableroUsuario;
    // Creamos el número de bombas
    static final int NUMERO_DE_BOMBAS = 10;
    // Creamos los tamaños de los tableros
    static final int TAMANO_TABLERO = 10;
    // Creamos la mina
    static final int MINA = -1;
    // Creamos un scanner

    public static void main(String[] args) {

        // Llamamos al método para crear los tableros
        crearTablero();
        // Generamos las minas
        generarMinas();
        // Recorremos el tablero y colocamos el número de 'MINAS' adyacentes
        recorreTablero();
        // Llamamos al método que pregunta las coordenadas y levanta las casillas
        retirarCasilla();
    }

    // Creamos los tableros
    public static void crearTablero() {

        tablero = new int[TAMANO_TABLERO][TAMANO_TABLERO];
        tableroUsuario = new boolean[TAMANO_TABLERO][TAMANO_TABLERO];
    }

    // Generamos las 10 bombas en coordenadas aleatorias del tablero
    public static void generarMinas() {

        for (int i = 0; i < NUMERO_DE_BOMBAS; i++) {

            int coordenadaX = (int) (Math.random() * 10);
            int cordenadaY = (int) (Math.random() * 10);

            if (tablero[coordenadaX][cordenadaY] != MINA) {

                tablero[coordenadaX][cordenadaY] = MINA;

            } else {

                i--;
            }
        }
    }

    // Creamos un método que nos va a mostrar el tablero dependiendo de si está o no levantada la casilla
    public static void mostrarTablero() {

        for (int i = 0; i < tablero.length; i++) {

            for (int j = 0; j < tablero[i].length; j++) {

                if (tableroUsuario[i][j]) {

                    System.out.printf(" %2d |  ", tablero[i][j]);

                } else {

                    System.out.print("  |  ");
                }
            }
            System.out.println();
        }
    }

    // Cuenta las minas adyacentes y se lo suma a las coordenadas i, j
    public static void contarMinasAdyacentes(int i, int j) {

        if (tablero[i][j] == MINA) {

            return;
        }

        int contadorDeMinasAdyacentes = 0;

        for (int k = i - 1; k <= i + 1; k++) {

            for (int l = j - 1; l <= j + 1; l++) {

                if (k >= 0 && k < TAMANO_TABLERO && l >= 0 && l < TAMANO_TABLERO) {

                    if (k ==i && l == j){

                        continue;
                    }

                    if (tablero[k][l] == MINA) {

                        contadorDeMinasAdyacentes++;
                    }
                }
            }
        }

        tablero[i][j] = contadorDeMinasAdyacentes;
    }

    public static void retirarCasilla() {

        int coordenadaX;
        int coordenadaY;

        do {
            // Utilizamos la clase 'MiEntradaSalida' para devolver un entero positivo
            do {
                coordenadaX = MiEntradaSalida.solicitarEnteroPositivo("Introduce la coordenada X");

            } while (coordenadaX >= TAMANO_TABLERO);

            do {
                coordenadaY = MiEntradaSalida.solicitarEnteroPositivo("Introduce la coordenada Y");

            } while (coordenadaY >= TAMANO_TABLERO);

            tableroUsuario[coordenadaX][coordenadaY] = true;

            // Si en las coordenadas X, Y hay una bomba mostramos el mensaje de que pierdes
            if (tablero[coordenadaX][coordenadaY] == MINA) {

                System.out.println("Ha explotado una mina, has perdido");
                break;

            } else {

                minasAdyacentes(coordenadaX, coordenadaY);
            }

            if (haGanado()) {

                break;
            }

            mostrarTablero();

        } while (tablero[coordenadaX][coordenadaY] != MINA);
    }

    // Este método levanta las minas adyacentes
    public static void minasAdyacentes(int coordenadaX, int coordenadaY) {

        if (tablero[coordenadaX][coordenadaY] != 0) {

            return;
        }

        for (int i = coordenadaX - 1; i <= coordenadaX + 1; i++) {

            for (int j = coordenadaY - 1; j <= coordenadaY + 1; j++) {

                if (i == coordenadaX && j == coordenadaY) {

                    continue;
                }

                if (i >= 0 && i < TAMANO_TABLERO && j >= 0 && j < TAMANO_TABLERO) {

                    if (!tableroUsuario[i][j]) {

                        tableroUsuario[i][j] = true;

                        // Sí hay un 0 alrededor se levantan las adyacentes de esa posición (Se vuelve a llamar al método)
                        if (tablero[i][j] == 0) {

                            minasAdyacentes(i, j);
                        }
                    }
                }
            }
        }
    }

    // Comprobamos que haya ganado el usuario
    public static boolean haGanado() {

        for (int i = 0; i < TAMANO_TABLERO; i++) {

            for (int j = 0; j < TAMANO_TABLERO; j++) {

                if (tablero[i][j] != MINA && !tableroUsuario[i][j]) {

                    return false;
                }
            }
        }
        return true;
    }

    // Hacemos este método para poder hacer el método 'contarMinasAdyacentes'
    public static void recorreTablero() {

        for (int i = 0; i < TAMANO_TABLERO; i++) {

            for (int j = 0; j < TAMANO_TABLERO; j++) {

                contarMinasAdyacentes(i, j);
            }
        }
    }
}