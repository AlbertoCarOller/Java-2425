package Boletin1;

import java.util.Arrays;

public class Ejercicio18 {
    public static void main(String[] args) {

        int[][] matriz = {{5, 6, 11}, {1, 15, 6}, {17, 20, 23}};
        int[][] matriz2 = {{18, 7, 5}, {19, 1, 5}, {13, 15, 27}};
        // Llamamos al método para comprobar si es cuadrada
        boolean cuadradas = comprobarCuadradas(matriz);
        // Llamamos al método para comprobar que las dos tengan el mismo tamaño
        boolean iguales = comprobarTamano(matriz, matriz2);

        if (cuadradas && iguales) {

            copiarMatriz(matriz, matriz2);
            System.out.println(Arrays.deepToString(matriz2));

        } else {

            System.out.println("Son de distinto tamaño o no son cuadradas");
        }
    }

    /* Comprobamos si son iguales y como antes comprobamos si son cuadradas,
     entonces también estaría comprobando que las dos sean cuadradas */
    private static boolean comprobarTamano(int[][] matriz, int[][] matriz2) {

        if (matriz.length != matriz2.length) {

            return false;
        }

        for (int i = 0; i < matriz.length; i++) {

            if (matriz[i].length != matriz2[i].length) {

                return false;
            }
        }
        return true;
    }

    // Cambiamos el valor de las posiciones de 'matriz2' por las de 'matriz' en diferente orden
    private static void copiarMatriz(int[][] matriz, int[][] matriz2) {

        for (int i = 0; i < matriz.length; i++) {

            for (int j = 0; j < matriz[i].length; j++) {

                matriz2[j][i] = matriz[i][j];
            }
        }
    }

    // Comprobamos si es cuadrada una de las matrices
    private static boolean comprobarCuadradas(int[][] matriz) {

        for (int i = 0; i < matriz.length; i++) {

            if (matriz.length != matriz[i].length) {

                return false;
            }
        }
        return true;
    }
}