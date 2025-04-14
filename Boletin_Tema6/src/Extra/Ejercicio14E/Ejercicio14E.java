package Extra.Ejercicio14E;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Ejercicio14E {
    public static void main(String[] args) {
        try {
            System.out.println(ficherosCumplenPatron());

        } catch (Ejercicio14EException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método cuenta los ficheros dentro de un directorio que cumplen con el
     * patrón especificado
     * @return
     * @throws Ejercicio14EException
     */
    public static AtomicInteger ficherosCumplenPatron() throws Ejercicio14EException {
        try {
            Path fichero = Path.of("Boletin_Tema6/src/Extra/Ejercicio12E");
            AtomicInteger ficherosCumplen = new AtomicInteger(0);
            try (Stream<Path> flujo = Files.list(fichero)) {
                flujo.filter(p -> {
                    if (!Files.isRegularFile(p)) {
                        return false;
                    }
                    Pattern pattern = Pattern.compile("^[A-Z][a-z]{3,}[A-Za-z]*\\d*\\.(log|txt)$");
                    Matcher matcher = pattern.matcher(p.toFile().getName());
                    if (matcher.matches()) {
                        return true;
                    }
                    return false;
                }).forEach(p -> ficherosCumplen.incrementAndGet());
                return ficherosCumplen;
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio14EException(e.getMessage());
        }
    }
}