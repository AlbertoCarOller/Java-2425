package Boletin1;

import java.util.Scanner;

public class Ejercicio5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean esPrimo = true;

        System.out.println("Introduce un número");
        int num = sc.nextInt();

        for (int i = 2; i < num / 2 && esPrimo; i++) {
            int result = num % i;

            if (result == 0) {
                esPrimo = false;
                System.out.println("No es primo");
            }
        }

        if (esPrimo){
            System.out.println("Es primo");
        }
    }
}
