package Boletin1;

import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio13 {

    static Scanner sc = new Scanner(System.in);
    static int indice = 0;

    public static void main(String[] args) {

        int[] pila = new int[5];
        int op;

        do {

            System.out.println("Si quieres apilar pulsa 1, si quieres desapilar pulsa 2 y si quieres salir pulsa 3");
            op = sc.nextInt();

            if (op == 1) {
                apilar(pila);
            }
            else if (op == 2) {
                desapilar(pila);
            }

        } while (op != 3);

        System.out.println(Arrays.toString(pila));
    }

    private static void apilar(int[] array) {

        int num;

        if (indice < array.length) {

            System.out.println("¿Qué número quieres introducir");
            num = sc.nextInt();
            array[indice] = num;
            System.out.println("Has apilado el " + num);
            indice++;

        } else {

            System.out.println("La pila está llena");
        }
    }

    private static void desapilar(int[] array) {

        if (indice > 0) {

            indice--;
            int desapila = array[indice];
            System.out.println("Has desapilado el " + desapila);

        } else {

            System.out.println("La pila está vacía");
        }
    }
}
