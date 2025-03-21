import java.util.Scanner;

public class Ejercicio1 {

    private static int[][] matrizMinas;
    private static boolean[][] matrizLevantadas;

    private static final int TAM_MATRIZ = 10;
    private static final int MINAS_A_PLANTAR = 10;
    private static final int MINA = -1;

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        generarMatrices();
        plantarMinas();
        recorrerTablero();
        seleccionarCordenadas();

    }

    // Genero las dos matrices
    private static void generarMatrices() {

        matrizMinas = new int[TAM_MATRIZ][TAM_MATRIZ];
        matrizLevantadas = new boolean[TAM_MATRIZ][TAM_MATRIZ];
    }

    // Colocamos las minas en la matriz de minas, que no se muestra al usuario
    private static void plantarMinas() {

        for (int i = 0; i < MINAS_A_PLANTAR; i++) {

            int coordenadaX = (int) (Math.random() * TAM_MATRIZ);
            int coordenadaY = (int) (Math.random() * TAM_MATRIZ);

            if (matrizMinas[coordenadaX][coordenadaY] != MINA) {

                matrizMinas[coordenadaX][coordenadaY] = MINA;

            } else {

                i--;
            }
        }
    }

    // Recorremos el tablero
    private static void recorrerTablero() {

        for (int i = 0; i < matrizMinas.length; i++) {

            for (int j = 0; j < matrizMinas[i].length; j++) {

                contarMinasAbyacentes(i, j);
            }
        }
    }

    // Recorremos las casillas adyacentes en busca de minas y colamos el número de minas en la casilla seleccionada
    private static void contarMinasAbyacentes(int i, int j) {

        /* Antes de contar comprobamos que en esa misma casilla no haya una bomba,
         para no sobreescribir la casilla y que la bomba desaparezca */
        if (matrizMinas[i][j] == MINA) {

            return;
        }

        int contadorDeMinas = 0;

        for (int k = i - 1; k <= i + 1; k++) {

            for (int l = j - 1; l <= j + 1; l++) {

                if (k >= 0 && k < matrizMinas.length && l >= 0 && l < matrizMinas[k].length) {

                    if (k == i && l == j) {

                        continue;
                    }

                    if (matrizMinas[k][l] == MINA) {

                        contadorDeMinas++;
                    }
                }
            }
        }
        matrizMinas[i][j] = contadorDeMinas;
    }

    // Muestra por pantalla el tablero
    private static void visualizaTablero() {

        for (int i = 0; i < TAM_MATRIZ; i++) {

            for (int j = 0; j < TAM_MATRIZ; j++) {

                if (matrizLevantadas[i][j]) {

                    System.out.printf("%2d | ", matrizMinas[i][j]);

                } else {

                    System.out.print("  | ");
                }
            }
            System.out.println();
        }
    }

    // Preguntamos al usuario las coordenadas donde miramos si hay mina
    private static void seleccionarCordenadas() {

        int cordenadaX;
        int cordenadaY;

        do {

            do {

                System.out.println("Seleccione una cordenada X");
                cordenadaX = sc.nextInt();

            } while (cordenadaX >= TAM_MATRIZ || cordenadaX < 0);

            do {

                System.out.println("Seleccione una coordenada Y");
                cordenadaY = sc.nextInt();

            } while (cordenadaY >= TAM_MATRIZ || cordenadaY < 0);

            matrizLevantadas[cordenadaX][cordenadaY] = true;
            levantarCasillasLimpias(cordenadaX, cordenadaY);

            if (matrizMinas[cordenadaX][cordenadaY] == MINA) {

                System.out.println("La mina ha explotado, el juego ha acabado");
            }

            visualizaTablero();

            // Si nos devuelve true se imprime el mensaje y salimos del bucle
            if (haGanado()) {

                System.out.println("Felicidades, has ganado");
                break;
            }

        } while (matrizMinas[cordenadaX][cordenadaY] != MINA);
    }

    // Levantamos las casillas adyacentes a las coordenadas
    private static void levantarCasillasLimpias(int coordenadasX, int coordenadasY) {

        for (int k = coordenadasX - 1; k <= coordenadasX + 1; k++) {

            for (int l = coordenadasY - 1; l <= coordenadasY + 1; l++) {

                if (k >= 0 && k < matrizMinas.length && l >= 0 && l < matrizMinas[k].length) {

                    if (k == coordenadasX && l == coordenadasY || matrizLevantadas[k][l]) {

                        continue;
                    }

                    if (matrizMinas[k][l] != MINA && !matrizLevantadas[k][l]) {

                        matrizLevantadas[k][l] = true;

                        if (matrizMinas[k][l] == 0) {

                            levantarCasillasLimpias(k, l);
                        }
                    }
                }
            }
        }
    }

    // Comprobamos si el jugador ha ganado
    private static boolean haGanado() {

        for (int i = 0; i < matrizLevantadas.length; i++) {

            for (int j = 0; j < matrizLevantadas[i].length; j++) {

                if (matrizMinas[i][j] != MINA && !matrizLevantadas[i][j]) {

                    return false;
                }
            }
        }
        return true;
    }
}