package Boletin1;

import java.util.Scanner;

public class Ejercicio8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el primer coeficiente");
        int a = sc.nextInt();

        System.out.println("Introduce el segundo coeficiente");
        int b = sc.nextInt();

        System.out.println("Introduce el tercer coeficiente");
        int c = sc.nextInt();

        int resultado = (int) Math.pow(b, 2) - 4 * a * c;

        if (resultado > 0) {
            System.out.println("La ecuación tiene dos soluciones reales");

        } else if (resultado == 0) {
            System.out.println("Tiene una solución real");

        } else {
            System.out.println("No tiene ninguna solución real");
        }
        sc.close();
    }
}
