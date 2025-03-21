package Boletin1;

import java.util.Arrays;

public class Ejercicio11 {
    public static void main(String[] args) {

        int[] array = {3, 4, 6, 7, 9, 10, 11};
        int[] array2 = {6, 9, 12, 1, 2, 11, 8};

        int[] arrayFusionado = fusionarComunes(array, array2);
        System.out.println(Arrays.toString(arrayFusionado));
    }

    private static int[] fusionarComunes(int[] array, int[] array2) {

        int[] arrayLimpio = new int[array.length];
        int indice = 0;

        for (int i = 0; i < array.length; i++) {


            for (int j = 0; j < array2.length; j++) {

                if (array[i] == array2[j]) {

                    boolean yaPasada = false;
                    // ahora tengo que comprobar si el elemento ya se pasó anteriormente al array final
                    for (int k = 0; k < indice && !yaPasada; k++) {
                        if (arrayLimpio[k] == array[i]){
                            yaPasada = true;
                        }
                    }

                    if (!yaPasada){
                        arrayLimpio[indice] = array[i];
                        indice++;
                    }

                }
            }
        }

        int[] arrayNuevo = new int[indice];

        for (int k = 0; k < indice; k++) {
            arrayNuevo[k] = arrayLimpio[k];
        }
        return arrayNuevo;
    }
}