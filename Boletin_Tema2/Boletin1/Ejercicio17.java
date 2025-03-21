package Boletin1;

import java.util.Arrays;

public class Ejercicio17 {
    public static void main(String[] args) {

        int[][] matriz = {{1, 3, 5, 6, 7}, {7, 9, 1, 10}};
        int[][] matriz2 = {{2, 5, 6, 7, 5}, {10, 12, 15, 1}};

        boolean iguales = comprobarTamano(matriz, matriz2);

        if (iguales) {

            copiarMatriz(matriz, matriz2);
            System.out.println(Arrays.deepToString(matriz2));
        } else {

            System.out.println("Son distintas");
        }
    }

    private static boolean comprobarTamano(int[][] matriz, int[][] matriz2) {

        if (matriz.length != matriz2.length){

            return  false;

        }

        for (int i = 0; i < matriz.length; i++) {

            if (matriz[i].length != matriz2[i].length){

                return false;
            }
        }
        return true;
    }

    private static void copiarMatriz(int[][] matriz, int[][] matriz2) {

        for (int i = 0; i < matriz.length; i++) {

            for (int j = 0; j < matriz[i].length; j++) {

                matriz2[i][j] = matriz[i][j];
            }
        }
    }
}