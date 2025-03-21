package Boletin1;

import java.util.Scanner;

public class Ejercicio3 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int tamano; // Declaramos la variable donde se guardará el tamaño del array

        do {
            System.out.println("¿Cuántos elementos quieres que tenga el array?");
            tamano = sc.nextInt();

        } while (tamano <= 0);

        int[] array = new int[tamano];
        rellenararray(array);

        int mayor = maxNum(array);
        int contador = contMax(array, mayor);
        System.out.println("El mayor es " + mayor + ", se repite " + contador + " veces");

        int menor = minNum(array);
        int segundoCont = contMin(array, menor);
        System.out.println("El menor es " + menor + ", se repite " + segundoCont + " veces");

        double media = media(array);
        System.out.println("La media es " + media);
    }

    private static void rellenararray(int[] array) { // Hacemos método para rellenar el array

        int valor;

        for (int i = 0; i < array.length; i++) {

            System.out.println("Introduce un valor");
            valor = sc.nextInt();
            array[i] = valor;

        }
    }

    private static double media(int[] array) { // Hacemos método para hacer la media de todos los números del array

        double media = 0;
        int suma = 0;

        for (int i = 0; i < array.length; i++) {

            suma += array[i];
            media = (double) suma / array.length;
        }
        return media;
    }

    private static int maxNum(int[] array) { // Declaramos método para obtener el número mayor

        int mayor = array[0];
        int contador = 0;

        for (int i = 1; i < array.length; i++) {

            if (array[i] > mayor) {

                mayor = array[i];
            }
        }
        return mayor;
    }

    /* Hacemos un contador para contar las veces
     que se repite el número mayor */

    private static int contMax(int[] array, int mayor) {

        int contador = 0;

        for (int i = 0; i < array.length; i++) {

            if (array[i] == mayor) {
                contador++;
            }
        }
        return contador;
    }

    private static int minNum(int[] array) { // Hacemos método para obtener el número menor del array

        int menor = array[0];

        for (int i = 1; i < array.length; i++) {

            if (menor > array[i]) {

                menor = array[i];
            }
        }
        return menor;
    }

    /* Hacemos un método para contar
     el número de veces que se repite el número mínimo */

    private static int contMin(int[] array, int menor) {

        int segundoCont = 0;

        for (int i = 0; i < array.length; i++) {

            if (menor == array[i]) {
                segundoCont++;
            }
        }
        return segundoCont;
    }
}