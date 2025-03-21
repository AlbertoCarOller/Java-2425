package Boletin1;

import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio7 {

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

        boolean estaElem = elementoBuscar(array, elemento);

        if (!estaElem) {

            System.out.println("El elemento no está en el array");

        } else {

            System.out.println("El elemento se encuentra en el array");

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

    private static boolean elementoBuscar(int[] array, int elemento) {

        boolean estaElem = false;

        for (int i = 0; i < array.length; i++) {

            if (elemento == array[i]) {

                estaElem = true;
            }
        }
        return estaElem;
    }
}
