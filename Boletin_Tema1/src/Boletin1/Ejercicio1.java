package Boletin1;

import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce un número");
        int num = sc.nextInt();

        System.out.println("Introduce un segundo número");
        int num2 = sc.nextInt();

        System.out.println("Introduce un último número");
        int num3 = sc.nextInt();

        if (num3 < num2 && num2 < num) {
            System.out.println("Ordenado de menor a mayor: " + num3 + " " + num2 + " " + num);

        } else if (num < num3 && num3 < num2) {
            System.out.println("Ordenado de menor a mayor: " + num + " " + num3 + " " + num2);

        } else if (num2 < num && num < num3) {
            System.out.println("Ordenado de menor a mayor: " + num2 + " " + num + " " + num3);

        } else if (num2 < num3 && num3 < num) {
            System.out.println("Ordenado de menor a mayor: " + num2 + " " + num3 + " " + num);

        } else if (num3 < num && num < num2) {
            System.out.println("Ordenado de menor a mayor: " + num3 + " " + num + " " + num2);

        } else if (num < num2 && num2 < num3) {
            System.out.println("Ordenado de menor a mayor: " + num + " " + num2 + " " + num3);

        } else if (num == num2 && num2 == num3) {
            System.out.println("Todos los números son iguales");

        } else if (num == num2) {
            if (num2 > num3) {
                System.out.println("Ordenado de menor a mayor: " + num3 + " " + num2);
            } else {
                System.out.println("Ordenado de menor a mayor: " + num2 + " " + num3);
            }

        } else if (num == num3) {
            if (num3 > num2) {
                System.out.println("Ordenado de menor a mayor: " + num2 + " " + num3);
            } else {
                System.out.println("Ordenado de menor a mayor: " + num3 + " " + num2);
            }

        } else {
            if (num3 > num) {
                System.out.println("Ordenado de menor a mayor: " + num + " " + num3);
            } else {
                System.out.println("Ordenado de menor a mayor: " + num3 + " " + num);
            }
        }
        sc.close();
    }
}
/*
int n1 = 1, n2 = 2, n3 = 1;

        if (n1 >= n2 && n1 >= n3){
            // n1 es el mayor
            if (n2 >= n3){
                // El orden es n1 >= n2 >= n3

            }
            else {
                // El orden es n1 >= n3 >= n2
            }
        }
        else if (n2 >= n1 && n2 >= n3){
            // n2 es el mayor
            if (n1 >= n3) {
                // El orden es n2 >= n1 >= n3
}
            else{
                // El orden es n2 >= n3 >= n1
            }
        }
        else {
            // n3 es el mayor
            if (n1 >= n2){
                // El orden es n3 >= n1 >= n2
            }
            else {
                // El orden es n3 >= n2 >= n1
            }
        }

    }
 */