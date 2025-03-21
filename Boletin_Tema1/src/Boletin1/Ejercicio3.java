package Boletin1;

import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num;

        // Comprobamos que el número sea mayor o igual a  0
        do {
            System.out.println("Introduce un número");
            num = sc.nextInt();
        } while (num < 0);

        // Si el número es menor o igual a 9 decimos que tendrá una cifra
        if (num <= 9) {
            System.out.println(num + " tiene solo una cifra");

            // Comprobamos el número de cifras que tiene el número
        } else {
            int contador = 0;
            int temp = num;

            while (temp != 0) {
                int div = temp / 10;
                temp = div;
                contador++;
            }
            System.out.println(num + " tiene " + contador + " cifras");

        }
        sc.close();

    }
}
