package Boletin1;

import java.util.Scanner;

public class Ejercicio2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int base;
        int exponente;
        int mult = 1;
        do {
            System.out.println("Introduce la base");
            base = sc.nextInt();

            System.out.println("introduce el exponente");
            exponente = sc.nextInt();
        }while (base < 0 || exponente < 0);

        for (int i = 1; i <= exponente; i++){
            mult = mult * base;
        }
        System.out.println("El resultado de " + base + " elevado a " + exponente + " es " + mult);
        sc.close();
    }
}
