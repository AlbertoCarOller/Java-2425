package Boletin1;

import java.util.Arrays;

public class Ejercicio4 {

    public static final int TAMANO = 6000;

    public static void main(String[] args) {

        int[] array = new int[TAMANO]; // Creamos el array con el tamaño de la varible 'TAMANO'
        rellenarArray(array); // Llamamos al método que rellena el array

        System.out.println(Arrays.toString(array)); // Por borrar

        double media = media(array); // Llamamos al método que hace la media
        System.out.println("La media es " + media);

        int mayor = ultimoDigMax(array); // Llamamos al método que da el último dígito que más se repite
        System.out.println("El último dígito que se repite más veces es " + mayor);

        int nulo = ultimoDigNulo(array); // Llamamos al método que comprueba si hay algún dígito que no se repita

        if (nulo == -1) { // Si todos se repiten
            System.out.println("Todos los dígitos se repiten");

        } else { // Si alguno no se repite
            System.out.println("No se repite el dígito " + nulo);

        }

        int[] array2 = numDig(array); // Llamamos al método que va a devolver el nuevo array

        double porcentaje;
        double sumaPorcent = 0;
        double mediaPorcent;

        for (int i = 0; i < array2.length; i++) { // Recorremos el nuevo array para decir el dígito y las veces que se repite

            porcentaje = ((double) array2[i] / array.length) * 100; // Calculamos el porcentaje
            sumaPorcent += porcentaje; // Calculamos la suma de todos los porcentajes

            System.out.println("El dígito " + i + " se repite " + array2[i] + " veces. El porcetaje es de " + porcentaje + "%");
        }

        mediaPorcent = sumaPorcent / array2.length; // Calculamos la media de los porcentajes

        System.out.println("La suma de los porcentajes es " + sumaPorcent + "% y la media de estos es " + mediaPorcent + "%");
    }

    private static void rellenarArray(int[] array) { // Rellenamos el array

        for (int i = 0; i < array.length; i++) {

            int valor = (int) (Math.random() * 101);
            array[i] = valor;
        }
    }

    private static double media(int[] array) { // Hacemos la media del array

        int suma = 0;
        double media = 0;

        for (int i = 0; i < array.length; i++) {

            suma += array[i];
            media = (double) suma / array.length;
        }
        return media;
    }

    private static int ultimoDigMax(int[] array) { // Comprobar cuántas veces se repite el último dígito

        int[] array2 = new int[10]; // Creamos un array de 10 posiciones donde se guardarán el número de veces que se repite esa posición


        for (int i = 0; i < array.length; i++) {

            int ultimoDig = array[i] % 10;
            array2[ultimoDig]++;
        }

        int max = 0; // Creamos la varible donde se alamcenará el nº máximo
        int indice = 0; // Creamos varible que indicará en que posición del array estamos

        for (int i = 0; i < array2.length; i++) {

            if (max < array2[i]) {

                max = array2[i];
                indice = i;
            }
        }
        return indice;
    }

    private static int ultimoDigNulo(int[] array) { // Comprobar si hay algún dígito que no se repita

        int[] array2 = new int[10];

        for (int i = 0; i < array.length; i++) {

            int ultimoDig = array[i] % 10;
            array2[ultimoDig]++;
        }

        int indice = -1; // Indice valdrá por defecto -1 para que no nos dé un dígito equivocado por si se repiten todos

        for (int i = 0; i < array2.length; i++) {

            if (array2[i] == 0) {
                indice = i;
            }
        }
        return indice;
    }

    /* Comprobar las veces que se repiten los últimos dígitos,
    lo hacemos guardando el número de veces
     que se repite cada dígito en un nuevo array */

    private static int[] numDig(int[] array) {

        int[] array2 = new int[10];

        for (int i = 0; i < array.length; i++) {

            int ultimoDig = array[i] % 10;
            array2[ultimoDig]++;
        }
        return array2;
    }
}