package Extra.Ejercicio28E;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Ejercicio28E {
    public static void main(String[] args) {
        try {
            ordenarTiposArchivo();

        } catch (Ejercicio28EException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a mover los ficheros de la carpeta especificada a otra carpeta
     * que se creará en tiempo de ejecución junto con sus subdirectorios
     *
     * @throws Ejercicio28EException
     */
    public static void ordenarTiposArchivo() throws Ejercicio28EException {
        try {
            Path descargas = Path.of("Boletin_Tema6/src/Extra/Ejercicio28E/Descargas");
            Path descargasOrdenadas = Path.of("Boletin_Tema6/src/Extra/Ejercicio28E/DescargasOrdenadas");
            Path imagenes = Path.of(descargasOrdenadas + "/Imagenes");
            Path textos = Path.of(descargasOrdenadas + "/Textos");
            /* Files.deleteIfExists(descargasOrdenadas); -> No moverá los archivos en caso de
             * que el directorio no esté vacío, habría problemas entonces */
            try (Stream<Path> flujo = Files.walk(descargas)) {
                flujo.filter(Files::isRegularFile).forEach(p -> {
                    if (p.toFile().getName().matches("^[A-Za-z0-9]+\\.txt$")) {
                        try {
                            Files.createDirectories(textos);
                            Files.move(p, Path.of(textos + "/" + p.toFile().getName()));

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else if (p.toFile().getName().matches("^[A-Za-z0-9]+\\.(jpg|jpeg)$")) {
                        try {
                            Files.createDirectories(imagenes);
                            Files.move(p, Path.of(imagenes + "/" + p.toFile().getName()));

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }

        } catch (IOException | RuntimeException e) {
            throw new Ejercicio28EException(e.getMessage());
        }
    }
}