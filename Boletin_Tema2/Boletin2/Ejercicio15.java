package Boletin2;

import java.util.Scanner;

public class Ejercicio15 {
    public static void main(String[] args) {

        int[][] matriz = {{12, 9, 1, 5}, {18, 10, 4, 9}, {19, 7, 1, 2}, {11, 17, 3, 16}};
        int fila;
        int columna;
        Scanner sc = new Scanner(System.in);

        do {

            System.out.println("¿Qué fila eliges?");
            fila = sc.nextInt();

            System.out.println("¿Qué columna eliges?");
            columna = sc.nextInt();

        } while (fila < 0 || columna < 0);

        int resultado = numCasilla(matriz, fila, columna);

        if (resultado == -1) {

            System.out.println("El rango está fuera del alcance");

        } else {

            System.out.println("El número del rango es " + resultado);
        }
    }

    private static int numCasilla(int[][] matriz, int numFila, int numColumna) {

        int resultado = -1;

        if (numFila < matriz.length && numColumna < matriz[0].length) {

            resultado = matriz[numFila][numColumna];
        }
        return resultado;
    }
}