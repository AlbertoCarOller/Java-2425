package Boletin4.Ejercicio6B4;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Ejercicio6B4 {
    public static void main(String[] args) {
        try {
            limitarContenidoFichero();

        } catch (Ejercicio6B4Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método eliminará la primera línea del fichero existente en caso de que
     * se supere el tamaño especificado
     *
     * @throws Ejercicio6B4Exception
     */
    public static void limitarContenidoFichero() throws Ejercicio6B4Exception {
        try {
            Path escribirle = Path.of("Boletin_Tema6/src/Boletin4/Ejercicio6B4/bitacora.txt");
            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                String linea;
                while ((linea = br.readLine()) != null && !linea.equalsIgnoreCase("fin")) {
                    /* StandardOpenOption.CREATE -> si el fichero no existe, se crea, si ya existe se sobreescribe
                     * StandardOpenOption.CREATE_NEW -> si el fichero no existe se crea, lanza excepción si ya existe */
                    Files.writeString(escribirle, linea + "\n", StandardOpenOption.APPEND, StandardOpenOption.CREATE);
                    if (Files.size(escribirle) > 30) {
                        List<String> lineas = Files.readAllLines(escribirle);
                        lineas.remove(lineas.getFirst());
                        Files.writeString(escribirle, "", StandardOpenOption.TRUNCATE_EXISTING);
                        for (int i = 0; i < lineas.size(); i++) {
                            Files.writeString(escribirle, lineas.get(i) + "\n", StandardOpenOption.APPEND);
                        }
                    }
                }
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio6B4Exception(e.getMessage());
        }
    }
}