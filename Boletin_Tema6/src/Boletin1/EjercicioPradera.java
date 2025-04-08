package Boletin1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class EjercicioPradera {
    public static void main(String[] args) {
        try (RandomAccessFile rd = new RandomAccessFile("Boletin_Tema6/ejercicioPradera.bmp", "r");
             FileOutputStream out = new FileOutputStream("Boletin_Tema6/ejercicioPraderaInvertida.bmp")) {
            // Leemos la cabecera
            byte[] cabecera = new byte[54];
            rd.read(cabecera);
            out.write(cabecera);
            // Hacemos un array de bytes de los 3 colores, que forman un pixel
            byte[] pixel = new byte[3];
            // Invertimos lo colores, recorriendo los píxeles de la imagen mientras haya, es decir que no sea -1
            while (rd.read(pixel) != -1) {
                for (int i = 0; i < pixel.length; i++) {
                    // '~' operador para negar los bytes, es decir se invierten
                    pixel[i] = (byte) ~pixel[i];
                }
                // Se escribe el pixel
                out.write(pixel);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}