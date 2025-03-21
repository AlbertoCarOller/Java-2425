package Boletin1;

import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio15 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int tamano;

        do {

            System.out.println("¿De qué tamaño quieres que sea el array?");
            tamano = sc.nextInt();

        } while (tamano <= 0);

        int[] array = new int[tamano];

        for (int i = 0; i < array.length; i++) {

            array[i] = i;
        }

        tacharMultiplos(array);
        System.out.println(Arrays.toString(array));
    }

    private static void tacharMultiplos(int[] array) {

        for (int i = 2; i < array.length; i++) {

            for (int j = i + 1; j < array.length; j++) {

                if (array[i] != 0 && array[j] != 0 && array[j] % array[i] == 0) {

                    array[j] = 0;
                }
            }
        }
    }
}
