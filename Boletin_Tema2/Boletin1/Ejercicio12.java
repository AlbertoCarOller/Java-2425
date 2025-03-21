package Boletin1;

import java.util.Arrays;

public class Ejercicio12 {
    public static void main(String[] args) {

        int[] array = {6, 5, 8, 7, 16, 12};
        ordenarArray(array);

        System.out.println(Arrays.toString(array));
    }

    private static void ordenarArray(int[] array) {

        int temporal;

        for (int i = 0; i < array.length; i++) {

            for (int j = 0; j < array.length - 1; j++) {

                if (array[j] > array[j + 1]) {

                    temporal = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temporal;
                }
            }
        }
    }
}