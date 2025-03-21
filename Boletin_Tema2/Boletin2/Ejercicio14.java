package Boletin2;

import java.util.Arrays;

public class Ejercicio14 {
    public static void main(String[] args) {

        int[][] matriz = {{4, 9, 10}, {23, 11, 6}, {46, 1, 8}};

        boolean esCuadrada = matrizCuadrada(matriz);

        if (esCuadrada) {

            int[][] matriz2 = filasAColumnas(matriz);
            System.out.println(Arrays.deepToString(matriz2));

            int[][] matriz3 = filasAColumnas(matriz2);
            System.out.println(Arrays.deepToString(matriz3));

        } else {

            System.out.println("No es cuadrada");
        }
    }

    private static boolean matrizCuadrada(int[][] matriz) {

        for (int i = 0; i < matriz.length; i++) {

            if (matriz.length != matriz[i].length) {

                return false;
            }
        }
        return true;
    }

    private static int[][] filasAColumnas(int[][] matriz) {

        int[][] matriz2 = new int[matriz.length][matriz[0].length];

        for (int i = 0; i < matriz.length; i++) {

            for (int j = 0; j < matriz[i].length; j++) {

                matriz2[i][j] = matriz[j][i];
            }
        }
        return matriz2;
    }
}
