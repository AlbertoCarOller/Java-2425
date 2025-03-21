package Boletin2;

import java.util.Scanner;

public class Ejercicio10 {

    static final int FILAS = 4;
    static final int COLUMNAS = 5;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int[][] matriz = new int[FILAS][COLUMNAS];
        rellenarMatriz(matriz);
        boolean esSimetrico = simetricaY(matriz);

        if (esSimetrico) {

            System.out.println("La matriz es simétrica en su eje Y");
        } else {

            System.out.println("La matriz no es simétrica en su eje Y");
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

    private static boolean simetricaY(int[][] matriz) {

        for (int i = 0; i < matriz.length; i++) {

            for (int j = 0; j < matriz[i].length / 2; j++) {

                if (matriz[i][j] != matriz[i][matriz[i].length - 1 - j]) {

                    return false;
                }
            }
        }
        return true;
    }
}