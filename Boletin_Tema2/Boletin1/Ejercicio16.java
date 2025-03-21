package Boletin1;

import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio16 {
    public static void main(String[] args) {
        int tamano;

        Scanner sc = new Scanner(System.in);

        do {

            System.out.println("Introduce hasta donde quieres llegar");
            tamano = sc.nextInt();

        } while (tamano <= 0);

        int[][] tablas = tablasDeMultiplicar(tamano);

        System.out.println(Arrays.deepToString(tablas));
    }

    public static int[][] tablasDeMultiplicar(int tamano) {
        int matriz[][] = new int[10][tamano];

        for (int i = 1; i <= matriz.length; i++) {

            for (int j = 1; j <= matriz[i - 1].length; j++) {

                matriz[i - 1][j - 1] = i * j;
            }
        }

        return matriz;
    }
}
