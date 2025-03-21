package Boletin2;

import java.util.Arrays;

public class Ejercicio8 {
    public static void main(String[] args) {

        int[][] matriz = {{1, 4, 6, 13}, {9, 5, 7, 10}};
        int[][] matriz2 = {{6, 19, 15, 9}, {12, 8, 6, 11}};

        int[][] suma = sumaMatrices(matriz, matriz2);
        System.out.println(Arrays.deepToString(suma));
    }

    private static int[][] sumaMatrices(int[][] matriz, int[][] matriz2) {

        int[][] suma = new int[matriz.length][matriz[0].length];

        for (int i = 0; i < matriz.length; i++) {

            for (int j = 0; j < matriz[i].length; j++) {

                suma[i][j] = matriz[i][j] + matriz2[i][j];
            }
        }
        return suma;
    }
}