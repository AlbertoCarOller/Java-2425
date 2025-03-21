package Boletin2;

public class Ejercicio6 {
    public static void main(String[] args) {

        int[][] matriz = {{2, 4, 6, 7, 9},
                          {16, 14}};

        imprimirAlReves(matriz);
    }

    private static void imprimirAlReves(int[][] matriz) {

        int max = 0;
        String unaLinea = "";

        for (int i = 0; i < matriz.length; i++) {

            if (matriz[i].length > max) {

                max = matriz[i].length;
            }
        }

        for (int i = max - 1; i >= 0; i--) {

            for (int j = matriz.length - 1; j >= 0; j--) {

                if (i < matriz[j].length) {

                    unaLinea += matriz[j][i];
                }
            }
        }
        System.out.println(unaLinea);
    }
}