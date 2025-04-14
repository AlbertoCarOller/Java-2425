package Extra.Ejercicio9E;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio9E {
    public static void main(String[] args) {
        try {
            validacionFicheroFecha();

        } catch (Ejercicio9EException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a imprimir los nombres de los ficheros que están en el fichero
     * especificado que cumplan la validación
     *
     * @throws Ejercicio9EException
     */
    public static void validacionFicheroFecha() throws Ejercicio9EException {
        try {
            Path fichero = Path.of("Boletin_Tema6/src/Extra/Ejercicio9E/FicherosFecha.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(fichero.toFile()))) {
                Set<String> ficheros = new HashSet<>();
                String ficheroS;
                while ((ficheroS = br.readLine()) != null) {
                    ficheros.add(ficheroS);
                }
                ficheros.stream().filter(s -> {
                    Pattern pattern = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])" +
                            "-(19[0-9]{2}|20([01][0-9]|2[0-5]))_[A-Za-z._-]+\\.[a-z]{3,}");
                    Matcher matcher = pattern.matcher(s);
                    if (matcher.matches()) {
                        return true;
                    }
                    return false;
                }).forEach(System.out::println);
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio9EException(e.getMessage());
        }
    }
}