package Boletin2;

public class Ejercicio5 {
    public static void main(String[] args) {

        int[][] matriz = {{4, 2, 6, 9, 12},
                          {20, 1, 7, 3}};
        imprimirColumnas(matriz);
    }

    private static void imprimirColumnas(int[][] matriz) {

        String unaLinea = "";
        int max = 0;

        for (int i = 0; i < matriz.length; i++) {

            if (matriz[i].length > max) {

                max = matriz[i].length;
            }
        }

        for (int i = 0; i < max; i++) {

            for (int j = 0; j < matriz.length; j++) {

                if (i < matriz[j].length) {

                    unaLinea += matriz[j][i];
                }
            }
        }
        System.out.println(unaLinea);
    }
}
