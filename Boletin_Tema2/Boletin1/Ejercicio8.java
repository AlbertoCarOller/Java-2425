package Boletin1;

import java.util.Scanner;

public class Ejercicio8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] array = {3, 4, 7, 1, 9, 6, 5};
        int elemento;

        do {

            System.out.println("Introduce el elemento que quieres buscar");
            elemento = sc.nextInt();

        } while (elemento < 0);

        int indice = buscaElemento(array, elemento);

        if (indice == -1) {

            System.out.println("El elemento no se encuentra en el array");

        } else {

            System.out.println("El elemento introducido se encuentra en la posción " + indice);
        }
    }

    private static int buscaElemento(int[] array, int elemento) {

        int indice = -1;

        for (int i = 0; i < array.length; i++) {

            if (elemento == array[i]) {

                indice = i;
            }
        }
        return indice;
    }
}