package Boletin1;

import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] array = {14, 21, 19, 15, 16, 20, 17, 18}; // Declaramos un array de 8 posiciones
        int mensaje;
        boolean reverse;

        do { // Se hace esto para elegir de que forma estará ordenado

            System.out.println("Para ordenar de mayor a menor presione 1, para ordenar de menor a mayor presione 2");
            mensaje = sc.nextInt();

        } while (mensaje != 1 && mensaje != 2);

        if (mensaje == 1) {

            reverse = true;

        } else {

            reverse = false;
        }

        int[] array2 = ordenarArray(array, reverse); // Llamamos el método burbuja
        System.out.println(Arrays.toString(array2));
    }

    private static int[] ordenarArray(int[] array, boolean reverse) { // Hacemos el método burbuja

        int[] array2 = new int[array.length];
        int temporal;

        for (int i = 0; i < array.length; i++) {

            for (int j = 0; j < array.length - 1; j++) {

                if (reverse) {

                    if (array[j] < array[j + 1]) {

                        temporal = array[j + 1];
                        array[j + 1] = array[j];
                        array[j] = temporal;
                    }
                } else {

                    if (array[j] > array[j + 1]) {

                        temporal = array[j + 1];
                        array[j + 1] = array[j];
                        array[j] = temporal;
                    }
                }
            }
        }
        return array;
    }
}