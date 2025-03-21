package Boletin2;

import java.util.Arrays;

public class Ejercicio12 {
    public static void main(String[] args) {

        int[][] matriz = {{12, 9, 1, 5}, {18, 10, 4, 9}, {19, 7, 1, 2}, {11, 17, 3, 16}};

        boolean esCuadrada = matrizCuadrada(matriz);

        if (esCuadrada) {

            int[][] diagonalReflejo = diagonalReflejo(matriz);
            System.out.println(Arrays.deepToString(diagonalReflejo));

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

    private static int[][] diagonalReflejo(int[][] matriz) {

        int[][] resultado = new int[matriz.length][matriz[0].length];

        for (int i = 0; i < matriz.length; i++) {

            for (int j = 0; j < matriz[i].length; j++) {

                resultado[i][j] = matriz[j][i];
            }
        }
        return resultado;
    }
}