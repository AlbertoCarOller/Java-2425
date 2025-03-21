package Boletin1;

import java.util.Scanner;

public class Ejercicio14 {

    static Scanner sc = new Scanner(System.in);
    static int indice = 0;


    public static void main(String[] args) {

        int[] array = new int[5];
        int op;

        do {

            System.out.println("Si quieres apilar pulsa 1, si quieres desapilar pulsa 2, si quieres salir pulsa 3");
            op = sc.nextInt();

            if (op == 1) {

                apilar(array);

            } else if (op == 2) {

                desapilar(array);
            }

        } while (op == 1 || op == 2);
    }

    // Apilamos el número en la pila
    private static void apilar(int[] array) {

        int num;

        // Si el índice es menor al tamaño del array sigue apilando
        if (indice < array.length) {

            System.out.println("Introduce un número");
            num = sc.nextInt();
            array[indice] = num;
            System.out.println("Has apilado el " + num);
            indice++;
        } else {

            System.out.println("La pila está llena");
        }
    }

    // Le damos la vuelta al array y desapilamos el número, una vez desapilado, le volvemos a dar la vuelta
    private static void desapilar(int[] array) {

        int[] contraria = new int[array.length];

        // Le damos la vuelta al array
        for (int i = 0; i < array.length; i++) {
                contraria[i] = array[array.length - 1 - i];

        }

        if (indice > 0) {

            indice--;
            int desapila = contraria[indice];
            System.out.println("Has desapilado el " + desapila);
        }

        // Le volvemos dar la vuelta al array una vez reescribimos la posición
        for (int i = 0; i < array.length; i++) {

            for (int j = array.length - 1; j >= 0; j--) {

                array[i] = contraria[j];
            }
        }
    }
}
