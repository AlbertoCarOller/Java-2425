package Boletin2;

public class Ejercicio1 {
    public static void main(String[] args) {

        int[][] matriz = {{2, 4, 6, -2}, {-9, 17, -1, 35}};

        boolean negativo = comprobarNegativo(matriz);

        if (negativo) {

            System.out.println("La matriz tiene números negativos");
        } else {

            System.out.println("La matriz no tiene ningún número negativo");
        }
    }

    private static boolean comprobarNegativo(int[][] matriz) {

        for (int i = 0; i < matriz.length; i++) {

            for (int j = 0; j < matriz[i].length; j++) {

                if (matriz[i][j] < 0) {

                    return true;
                }
            }
        }
        return false;
    }
}
