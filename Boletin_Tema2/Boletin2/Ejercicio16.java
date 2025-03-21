package Boletin2;

import java.util.Arrays;

public class Ejercicio16 {
    public static void main(String[] args) {

        int[][] matriz = {{12, 9, 1, 5}, {18, 10, 4, 9}, {19, 7, 1, 2}, {11, 17, 3, 16}};

        int[][] matrizAdyacentes = abyacentesPares(matriz);

        System.out.println(Arrays.deepToString(matrizAdyacentes));
    }

    private static int[][] abyacentesPares(int[][] matriz) {

        int[][] matrizAdyacentesPares = new int[matriz.length][matriz[0].length];

        for (int i = 0; i < matriz.length; i++) {

            for (int j = 0; j < matriz[i].length; j++) {

                matrizAdyacentesPares[i][j] = cuentaAdyacentesPares(matriz, i, j);
            }
        }
        return matrizAdyacentesPares;
    }

    private static int cuentaAdyacentesPares(int[][] matriz, int i, int j) {

        int numPares = 0;

        for (int k = i - 1; k < i + 1; k++) {

            for (int l = j - 1; l < j + 1; l++) {

                if (k >= 0 && k < matriz.length && l >= 0 && l < matriz.length) {

                    if (k == i && l == j) {

                        continue;
                    }

                    if (matriz[k][l] % 2 == 0) {

                        numPares++;
                    }
                }
            }
        }
        return numPares;
    }
}