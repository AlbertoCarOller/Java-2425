package Boletin1;

import java.util.Scanner;

public class Ejercicio7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num;
        int num2;
        int resto;

        do {
            System.out.println("Introduce un número entero positivo");
            num = sc.nextInt();

            System.out.println("introduce otro número entero positivo");
            num2 = sc.nextInt();

        } while (num <= 0 || num2 <= 0);

        while ((resto = num % num2) != 0) {
            num = num2;
            num2 = resto;

        }
        System.out.println("El m.c.d es " + num2);
        sc.close();
    }
}
