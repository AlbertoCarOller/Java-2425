package Extra.Ejercicio16E;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Ejercicio16E {
    public static void main(String[] args) {
        try {
            cambiarExtensionFicheros();

        } catch (Ejercicio16EException | RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Este método va a cambiar a quitar la extensión de los ficheros dentro del directorio
     * especificado
     *
     * @throws Ejercicio16EException
     */
    public static void cambiarExtensionFicheros() throws Ejercicio16EException {
        try {
            Path fichero = Path.of("Boletin_Tema6/src/Extra/Ejercicio16E");
            try (Stream<Path> flujo = Files.walk(fichero)) {
                flujo.filter(p -> {
                    Pattern pattern = Pattern.compile("^.*\\d{3}\\.bak$");
                    Matcher matcher = pattern.matcher(p.toFile().getName());
                    if (matcher.matches()) {
                        return true;
                    }
                    return false;
                }).forEach(p -> {
                    try {
                        Files.move(p, Path.of(fichero + "/" + p.toFile().getName().split("\\.")
                                [0]));

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio16EException(e.getMessage());
        }
    }
}