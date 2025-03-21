package Boletin1;

import java.util.Scanner;

public class Ejercicio6 {
    public static void main(String[] args) {
        int numApro = 0;
        int numSusp = 0;
        int nota;
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduce el número de alumnos");
        int numAlu = sc.nextInt();

        for (int i = 1; i <= numAlu; i++) {

            do {
                System.out.println("Introduce la nota");
                nota = sc.nextInt();

            } while (nota <= 0 || nota >= 10);

            if (nota >= 5) {
                numApro++;

            } else {
                numSusp++;
            }
        }
        double porcentajeApro =  (double) numApro / numAlu * 100;
        double porcentajeSusp = (double) numSusp / numAlu * 100;

        System.out.println("Han aprobado " + numApro + " alumnos, el porcentaje es de " + porcentajeApro + " %" + " y han suspendido "
                + numSusp + " alumnos, el porcentaje de suspensos es de " + porcentajeSusp + " %");
        sc.close();
    }
}
