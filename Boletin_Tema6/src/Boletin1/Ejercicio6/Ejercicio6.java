package Boletin1.Ejercicio6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ejercicio6 {
    public static void main(String[] args) {
        try {
            //System.out.println(mostrarArchivosYPeso("Boletin_Tema6/src/Boletin1"));
            mostrarArchivosYPesoV2("Boletin_Tema6/src/Boletin1");

        } catch (Ejercicio6Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param nombreFichero
     * @return String con los ficheros y directorios, en el caso de los ficheros se calcula
     * los KB y en caso de los directorios se muestra el mensaje de que no se puede calcular
     * @throws Ejercicio6Exception
     */
    public static String mostrarArchivosYPeso(String nombreFichero) throws Ejercicio6Exception {
        try (Stream<Path> flujo = Files.list(Path.of(nombreFichero))) {
            return flujo.map(p -> {
                try {
                    if (Files.isDirectory(p)) {
                        return Map.entry(p, 0L);

                    } else {
                        // Lo divido entre 1024 para obtenerlo en KB y lo paso a double para no perder decimales
                        return Map.entry(p.getFileName(), (double) Files.size(p) / 1024);
                    }

                } catch (IOException e) {
                    return Map.entry(p, 0L);
                }
            }).map(m -> {
                if (Files.isDirectory(m.getKey())) {
                    return m.getKey().getFileName() + ", No se puede calcular";

                } else {
                    return m.getKey().getFileName() + ", " + m.getValue();
                }
            }).collect(Collectors.joining("\n"));

        } catch (IOException | InvalidPathException e) {
            throw new Ejercicio6Exception(e.getMessage());
        }
    }

    /**
     * Esto es una segunda versión del método 'mostrarArchivosYPeso()', en este
     * no lo convierto a mapa, directamente lo convierto a un flujo de Stream y
     * lo imprimo con forEach()
     *
     * @param nombreFichero
     * @throws Ejercicio6Exception
     */
    public static void mostrarArchivosYPesoV2(String nombreFichero) throws Ejercicio6Exception {
        try (Stream<Path> flujo = Files.list(Path.of(nombreFichero))) {
            flujo.map(p -> {
                if (!Files.isDirectory(p)) {
                    try {
                        return String.format("Nombre: %s, Tamaño: %.2f", p.getFileName(), (double) Files.size(p) / 1024);

                    } catch (IOException e) {
                        return "Error";
                    }

                } else {
                    return "Nombre: " + p.getFileName() + ", no se puede calcular";
                }
            }).forEach(System.out::println);

        } catch (IOException | InvalidPathException e) {
            throw new Ejercicio6Exception(e.getMessage());
        }
    }
}