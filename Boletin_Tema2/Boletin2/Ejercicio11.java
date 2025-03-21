package Boletin2;

public class Ejercicio11 {
    public static void main(String[] args) {

        int[][] matriz = {{4, 5, 8, 10}, {9, 10, 7, 11}, {12, 18, 1, 3}};
        int[][] matriz2 = {{7, 12, 9, 1}, {10, 5, 9, 23}, {1, 14, 11, 4}};

        boolean mismoTamano = compruebaTamano(matriz, matriz2);
        boolean inversas = sonInversas(matriz, matriz2);

        if (mismoTamano) {

            if (inversas) {

                System.out.println("Son las mismas matrices si la invertimos");

            } else {

                System.out.println("Son diferentes matrices");
            }
        } else {

            System.out.println("Son de distinto tamaño");
        }
    }

    private static boolean compruebaTamano(int[][] matriz, int[][] matriz2) {

        if (matriz.length != matriz2.length) {

            return false;
        }

        for (int i = 0; i < matriz.length; i++) {

            if (matriz[i].length != matriz2[i].length) {

                return false;
            }
        }
        return true;
    }

    private static boolean sonInversas(int[][] matriz, int[][] matriz2) {

        for (int i = 0; i < matriz.length; i++) {

            for (int j = 0; j < matriz[i].length; j++) {

                if (matriz[i][j] != matriz2[matriz.length - 1 - i][matriz.length - 1 - j]) {

                    return false;
                }
            }
        }
        return true;
    }
}