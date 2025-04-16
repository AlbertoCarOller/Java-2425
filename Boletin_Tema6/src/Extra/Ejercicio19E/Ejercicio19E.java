package Extra.Ejercicio19E;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ejercicio19E {
    public static void main(String[] args) {
        try {
            System.out.println(mostrarFicherosPesoYCantidadValidos());

        } catch (Ejercicio19EException | RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a mostrar los ficheros de un directorio en concreto descartando los
     * que sean directorios, se va a mostrar también el peso de cada uno y se mostrará
     * el número de ficheros que coincidan con la expresión regular
     *
     * @return
     * @throws Ejercicio19EException
     */
    public static String mostrarFicherosPesoYCantidadValidos() throws Ejercicio19EException {
        try {
            Path directorio = Path.of("Boletin_Tema6/src/Extra/Ejercicio12E");
            try (Stream<Path> flujo = Files.list(directorio)) {
                AtomicInteger atomicInteger = new AtomicInteger(0);
                return Optional.of(flujo.filter(Files::isRegularFile).map(p -> {
                            try {
                                Pattern pattern = Pattern.compile("^.+\\d{3,}.{3,}$");
                                Matcher matcher = pattern.matcher(p.toFile().getName());
                                if (matcher.matches()) {
                                    atomicInteger.incrementAndGet();
                                }
                                return "Nombre: " + p.toFile().getName() + ", Tamaño: " + (double) Files.size(p);

                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }).collect(Collectors.joining("\n"))).filter(s -> !s.isEmpty())
                        .orElseThrow(() -> new Ejercicio19EException("No hay ficheros")) + "\nFicheros válidos: " + atomicInteger;

            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio19EException(e.getMessage());
        }
    }
}