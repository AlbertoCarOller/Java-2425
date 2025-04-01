package Boletin1;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class EjercicioMP3 {
    public static void main(String[] args) {
        /* El RandomAccessFile acepta un file, y los permisos, en este caso r de read, este permite mover el puntero
         * en los bytes del fichero especificado */
        try (RandomAccessFile rnd = new RandomAccessFile(new File("Boletin_Tema6/0103. Fun Time - AShamaluevMusic.mp3")
                , "r")) {
            // .seek mueve el puntero al índice especificado
            rnd.seek(rnd.length() - 128);
            /* Creamos un array de bytes con 3 posiciones que son los 3 primeros caracteres en este caso, ya que
             empezamos en el índice 0 */
            byte[] tag = new byte[3];
            /* Impregna, guarda o acumula los 3 caracteres en el array de bytes (primitivo) y devuelve los bytes
             * leídos, -1 si se ha sobrepasado el límite de bytes o una excepción si por alguna razón no se ha
             * podido leer todos los bytes que se pretendía por alguna razón*/
            rnd.read(tag);
            // Guardamos en un String el char sequence que forma el array de bytes
            String tagS = new String(tag);
            byte[] titulo = new byte[30];
            rnd.read(titulo);
            int contador = 0;
            // Comprobamos los los bytes que no son nulos
            for (byte b : titulo) {
                if (b != 0) {
                    contador++;
                }
            }
            // Reiniciamos el contador hasta después de leer el tag
            rnd.seek(rnd.length() - 125);
            // Nos creamos el array de bytes de lo que realmente ocupa el título
            byte[] tituloOriginal = new byte[contador];
            // Leemos el título original
            rnd.read(tituloOriginal);
            String tituloOriginalS = new String(tituloOriginal);
            // Pasamos a la posición después de los 30 bytes reservados para el título
            rnd.seek(rnd.length() - 95);
            System.out.println(tagS);
            System.out.println(tituloOriginalS);
            // TODO: artista(30), álbum(30), año(4), comentario(30), genero musical(1)


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}