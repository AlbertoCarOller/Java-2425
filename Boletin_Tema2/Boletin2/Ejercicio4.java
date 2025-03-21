package Boletin2;

public class Ejercicio4 {
    public static void main(String[] args) {

        int[][] matriz = {{3, 6, 7, 9, 10}, {13, 17, 25, 4}};

        unaLineaAlReves(matriz);
    }

    private static void unaLineaAlReves(int[][] matriz) {

        String unaLinea = "";

        for (int i = matriz.length - 1; i >= 0; i--) {

            for (int j = matriz[i].length - 1; j >= 0; j--) {

                unaLinea += matriz[i][j];

            }
        }
        System.out.println(unaLinea);
    }
}
