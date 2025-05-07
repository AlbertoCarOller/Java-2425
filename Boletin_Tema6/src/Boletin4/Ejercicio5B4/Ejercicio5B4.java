package Boletin4.Ejercicio5B4;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Ejercicio5B4 {
    public static void main(String[] args) {
        try {
            //escribirLineasFichero();
            escribirLineasFicheroV2();

        } catch (Ejercicio5B4Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a escribir líneas en un fichero, en caso de que no exista se crea,
     * en caos de que exista se van añadiendo las líneas
     *
     * @throws Ejercicio5B4Exception
     */
    public static void escribirLineasFichero() throws Ejercicio5B4Exception {
        try {
            Path escribirle = Path.of("Boletin_Tema6/src/Boletin4/Ejercicio5B4/datos.txt");
            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                if (!Files.exists(escribirle)) {
                    Files.createFile(escribirle);
                }
                String linea;
                while ((linea = br.readLine()) != null && !linea.equalsIgnoreCase("fin")) {
                    Files.writeString(escribirle, linea + "\n", StandardOpenOption.APPEND);
                }
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio5B4Exception(e.getMessage());
        }
    }

    /**
     * Este método va a escribir líneas en un fichero, en caso de que no exista se crea,
     * en caos de que exista se van añadiendo las líneas (2º versión)
     *
     * @throws Ejercicio5B4Exception
     */
    public static void escribirLineasFicheroV2() throws Ejercicio5B4Exception {
        try {
            Path escribirle = Path.of("Boletin_Tema6/src/Boletin4/Ejercicio5B4/datos.txt");
            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                 PrintWriter pw = new PrintWriter(new FileWriter(escribirle.toFile()))) {
                String linea;
                while ((linea = br.readLine()) != null && !linea.equalsIgnoreCase("fin")) {
                    pw.println(linea);
                }
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio5B4Exception(e.getMessage());
        }
    }
}