package Boletin1;

import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio6 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int[] array = new int[6];
        rellenarArray(array);
        System.out.println(Arrays.toString(array));

        int elemento;

        do {

            System.out.println("¿Qué elemento quieres buscar?");
            elemento = sc.nextInt();

        } while (elemento < 0);

        int indice = elementoBuscar(array, elemento);

        if (indice == -1) {

            System.out.println("El elemento no está en el array");

        } else {

            System.out.println("El elemento se encuentra en la posición " + indice);

        }
    }

    private static void rellenarArray(int[] array) {

        int valor = 0;

        for (int i = 0; i < array.length; i++) {

            System.out.println("Introduce el valor de la posición");
            valor = sc.nextInt();
            array[i] = valor;
        }
    }

    private static int elementoBuscar(int[] array, int elemento) {

        int indice = -1;

        for (int i = 0; i < array.length; i++) {

            if (elemento == array[i]) {

                indice = i;
            }
        }
        return indice;
    }
}