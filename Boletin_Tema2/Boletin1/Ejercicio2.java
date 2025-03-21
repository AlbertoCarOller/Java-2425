package Boletin1;

public class Ejercicio2 {
    public static void main(String[] args) {
        int[][] array = {{1, 2, -3, 6, 7}, {-4, 9, 18, 12, -2}};

        int contador = contarNegativos(array);

        System.out.println("Hay " + contador + " numeros negativos");
    }

    private static int contarNegativos(int[][] array) {

        int contador = 0;

        for (int i = 0; i < array.length; i++) {

            for (int j = 0; j < array[i].length; j++) {

                if (array[i][j] < 0) {

                    contador++;
                }
            }
        }
        return contador;
    }
}