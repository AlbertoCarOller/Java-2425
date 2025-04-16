package Extra.Ejercicio17E;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio17E {
    public static void main(String[] args) {
        try {
            validarEntradas();

        } catch (Ejercicio17EException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a comprobar que en el texto de un fichero especificado
     * haya una palabra que empiece por mayúsculas, que termine el texto con
     * un ';' y que haya una fecha válida
     *
     * @throws Ejercicio17EException
     */
    public static void validarEntradas() throws Ejercicio17EException {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Extra/Ejercicio17E/lineasEntrada.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(leerlo.toFile()))) {
                StringBuilder sb = new StringBuilder();
                String linea;
                while ((linea = br.readLine()) != null) {
                    sb.append(linea).append(" ");
                }
                if (!sb.toString().trim().endsWith(";")) {
                    throw new Ejercicio17EException("No termina por ';'");
                }
                String[] palabras = sb.toString().split(" ");
                boolean letraMayuscula = false;
                boolean hayFecha = false;
                Pattern pattern = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(19[0-9]{2}|20([01][0-9]|2[0-5]))$");
                Matcher matcher;
                for (int i = 0; i < palabras.length; i++) {
                    if (!letraMayuscula && Character.isUpperCase(palabras[i].charAt(0))) {
                        letraMayuscula = true;
                    }
                    matcher = pattern.matcher(palabras[i]);
                    if (!hayFecha && matcher.matches()) {
                        hayFecha = true;
                    }
                }
                if (hayFecha && letraMayuscula) {
                    System.out.println(sb);

                } else {
                    throw new Ejercicio17EException("No hay fecha o no hay una palabra que empiece en mayúsculas");
                }
            }

        } catch (InvalidPathException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}