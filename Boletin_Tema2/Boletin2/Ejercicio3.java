package Boletin2;

public class Ejercicio3 {
    public static void main(String[] args) {

        int[][] matriz = {{9, 13, 5, 1, 3}, {10, 19, 4}};

        unaLinea(matriz);
    }

    private static void unaLinea(int[][] matriz) {

        String unaLinea = "";

        for (int i = 0; i < matriz.length; i++) {

            for (int j = 0; j < matriz[i].length; j++) {

                unaLinea += matriz[i][j];
            }
        }
        System.out.println(unaLinea);
    }
}
