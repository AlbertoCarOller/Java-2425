package Boletin1.Ejercicio7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Ejercicio7 {
    public static void main(String[] args) {
        try {
            //listarFicherosDirectorioYPeso("Boletin_Tema6/src/Boletin1");
            //listarFicherosTerminadosEn("Boletin_Tema6/src/Boletin1", "3.java");
            //buscarFicherosPorExtension("Boletin_Tema6/src/Boletin1", "java");
            //System.out.println(buscarFicheroDeDirectorio("Boletin_Tema6/src/Boletin1", "Ejercicio1.java"));
            buscarFicheroRecursivamente("./", "Ejercicio1.java");


        } catch (Ejercicio7Exception e) {
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
                throw new Ejercicio7Exception("El directorio no es legible");
            }
            try (Stream<Path> flujo = Files.list(archivo)) {
                flujo.map(p -> {
                    if (Files.isDirectory(p)) {
                        return String.format("Nombre: %s", p.getFileName());

                    } else {
                        try {
                            return String.format("Nombre: %s, Peso: %.2f", p.getFileName(), (double) Files.size(p) / 1024);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).forEach(System.out::println);

            } catch (IOException | RuntimeException e) {
                throw new Ejercicio7Exception(e.getMessage());
            }
        } catch (InvalidPathException e) {
            throw new Ejercicio7Exception(e.getMessage());
        }
    }

    /**
     * Listamos los ficheros acabados en lo especificado
     *
     * @param ruta
     * @param ultima
     * @throws Ejercicio7Exception
     */
    public static void listarFicherosTerminadosEn(String ruta, String ultima) throws Ejercicio7Exception {
        try {
            Path directorio = Path.of(ruta);
            try (Stream<Path> flujo = Files.list(directorio)) {
                /* Para ver en qué termina el fichero que estamos buscando hay que pasarlo a File y utilizar .getName()
                 * para ver el nombre del fichero como un String */
                flujo.filter(p -> p.toFile().getName().endsWith(ultima))
                        .map(p -> {
                            if (Files.isDirectory(p)) {
                                return String.format("Nombre: %s", p.getFileName());

                            } else {
                                try {
                                    return String.format("Nombre: %s, Peso: %.2f", p.getFileName(), (double) Files.size(p) / 1024);

                                } catch (IOException e) {
                                    return "Error";
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

    /**
     * Listamos los ficheros con la extensión especificada
     *
     * @param ruta
     * @param extension
     * @throws Ejercicio7Exception
     */
    public static void buscarFicherosPorExtension(String ruta, String extension) throws Ejercicio7Exception {
        try {
            Path directorio = Path.of(ruta);
            try (Stream<Path> flujo = Files.list(directorio)) {
                /* Para ver en qué termina el fichero que estamos buscando hay que pasarlo a File y utilizar .getName()
                 * para ver el nombre del fichero como un String */
                flujo.filter(p -> {
                            // Hacemos un split y miramos si la última parte del array es la extensión especificada
                            String[] array = (p.toFile().getName().split("\\."));
                            return array[array.length - 1].equalsIgnoreCase(extension);
                        })
                        .map(p -> {
                            if (Files.isDirectory(p)) {
                                return String.format("Nombre: %s", p.getFileName());

                            } else {
                                try {
                                    return String.format("Nombre: %s, Peso: %.2f", p.getFileName(), (double) Files.size(p) / 1024);

                                } catch (IOException e) {
                                    return "Error";
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

    /**
     * Buscamos en un directorio especificado el fichero pasado por parámetros
     *
     * @param directorioP
     * @param fichero
     * @return
     * @throws Ejercicio7Exception
     */
    public static Path buscarFicheroDeDirectorio(String directorioP, String fichero) throws Ejercicio7Exception {
        try {
            Path directorio = Path.of(directorioP);
            try (Stream<Path> flujo = Files.list(directorio)) {
                return flujo.filter(Files::isRegularFile)
                        .filter(p -> p.toFile().getName().equalsIgnoreCase(fichero)).findFirst()
                        .orElseThrow(() -> new Ejercicio7Exception("No se encuentra el fichero"));

            } catch (IOException e) {
                throw new Ejercicio7Exception(e.getMessage());
            }

        } catch (InvalidPathException e) {
            throw new Ejercicio7Exception(e.getMessage());
        }
    }

    /**
     * Buscamos ficheros con el nombre pasado por parámetros, mirando incluso en el interior de los
     * directorios del directorio pasado por parámetros y guardándolos en una lista
     *
     * @param directorioP
     * @param fichero
     * @return lista de direcciones
     * @throws Ejercicio7Exception
     */
    public static void buscarFicheroRecursivamente(String directorioP, String fichero) throws Ejercicio7Exception {
        try {
            Path directorio = Path.of(directorioP);
            try (Stream<Path> flujo = Files.walk(directorio)) {
                flujo.filter(p -> p.toFile().getName().equalsIgnoreCase(fichero))
                        .forEach(System.out::println);

            } catch (IOException e) {
                throw new Ejercicio7Exception(e.getMessage());
            }

        } catch (InvalidPathException e) {
            throw new Ejercicio7Exception(e.getMessage());
        }
    }
}