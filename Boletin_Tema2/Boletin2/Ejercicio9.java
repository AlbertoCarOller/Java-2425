package Boletin2;

import java.util.Scanner;

public class Ejercicio9 {

    static final int FILAS = 4;
    static final int COLUMNAS = 4;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int[][] matriz = new int[FILAS][COLUMNAS];
        rellenarMatriz(matriz);

        boolean esSimetrica = matrizSimetrica(matriz);

        if (esSimetrica) {

            System.out.println("La matriz es simétrica en su eje X");
        } else {

            System.out.println("No es simétrica en su eje X");
        }
    }

    private static void rellenarMatriz(int[][] matriz) {

        int num;

        for (int i = 0; i < matriz.length; i++) {

            for (int j = 0; j < matriz[i].length; j++) {

                System.out.println("Introduce un número");
                num = sc.nextInt();
                matriz[i][j] = num;
            }
        }
    }

    private static boolean matrizSimetrica(int[][] matriz) {

        for (int i = 0; i < matriz.length / 2; i++) {

            int[] filasPrimeras = matriz[i];
            int[] filasUltimas = matriz[matriz.length - 1 - i];

            for (int j = 0; j < matriz[i].length; j++) {

                if (filasPrimeras[j] != filasUltimas[j]) {

                    return false;
                }
            }
        }
        return true;
    }
}
