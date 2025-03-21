package Boletin2;

public class Ejercicio2 {
    public static void main(String[] args) {

        int[][] matriz = {{2, 5, 9, 17, 1, 7}, {4, 2, 6, 20}};

        int suma = sumaPosiciones(matriz);
        System.out.println("La suma de todas las posiciones es " + suma);
    }

    private static int sumaPosiciones(int[][] matriz) {

        int suma = 0;

        for (int i = 0; i < matriz.length; i++) {

            for (int j = 0; j < matriz[i].length; j++) {

                suma += matriz[i][j];
            }
        }
        return suma;
    }
}
