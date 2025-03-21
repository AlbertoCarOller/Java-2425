package Boletin1;

import java.util.Scanner;

public class Ejercicio4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num;
        int vidas = 5;
        int numAle = (int) (Math.random() * 100) + 1;

        System.out.println("Bienvenido al juego del número secreto");

        do {
            System.out.println("introduce un número y veremos si te acercas");
            num = sc.nextInt();

            if (num < numAle && num > 1 && num < 100) {
                System.out.println(num + " está por debajo");

            } else if (num > numAle && num > 1 && num < 100) {
                System.out.println(num + " está por encima");
                
            } else if (num < 1 || num > 100) {
                System.out.println("El número debe estar entre 1 y 100");
                
            }

            vidas--;

        } while (vidas > 0 && num != numAle);

        if (num == numAle) {
            System.out.println(num + " es el número aleatorio, felicidades");

        } else {
            System.out.println("Has perdido todas las vidas, lo siento");
        }
        sc.close();
    }
}
