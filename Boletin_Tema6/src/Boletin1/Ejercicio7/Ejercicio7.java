package Boletin1.Ejercicio7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Ejercicio7 {
    public static void main(String[] args) {
        try {
            listarFicherosDirectorioYPeso("Boletin_Tema6/src/Boletin1");

        } catch (Ejercicio7Exception | RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Listamos los ficheros y directorios del directorio de la dirección pasada por parámetros,
     * en caso de que sean ficheros se muestran sus pesos en KB, en caso de directorios no se puede
     * calcular el peso, si el directorio no existe o no tiene permiso de lectura se lanza excepción
     *
     * @param ruta
     * @throws Ejercicio7Exception
     */
    public static void listarFicherosDirectorioYPeso(String ruta) throws Ejercicio7Exception {
        try {
            Path archivo = Path.of(ruta);
            if (!Files.exists(archivo)) {
                throw new Ejercicio7Exception("No existe el directorio");
            }
            if (!Files.isReadable(archivo)) {
                throw new Ejercicio7Exception("El directorio no es leíble");
            }
            try (Stream<Path> flujo = Files.list(archivo)) {
                flujo.map(p -> {
                    if (Files.isDirectory(p)) {
                        return String.format("Nombre: %s, Peso: No se puede calcular", p.getFileName());

                    } else {
                        try {
                            return String.format("Nombre: %s, Peso: %.2f", p.getFileName(), (double) Files.size(p) / 1024);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).forEach(System.out::println);

            } catch (IOException e) {
                throw new Ejercicio7Exception(e.getMessage());
            }
        } catch (InvalidPathException e) {
            throw new Ejercicio7Exception(e.getMessage());
        }
    }
}