package Boletin1;

import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio5 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int tamano;

        do {
            System.out.println("Introduce el tamaño del array");
            tamano = sc.nextInt();

        } while (tamano <= 0);

        int[] array = arrayAle(tamano);

        System.out.println(Arrays.toString(array));
    }

    private static int[] arrayAle(int tamano) {

        int[] array = new int[tamano];
        int valor;

        for (int i = 0; i < array.length; i++) {

            valor = (int) (Math.random() * 1001);
            array[i] = valor;
        }
        return array;
    }
}
