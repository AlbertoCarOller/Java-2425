package Boletin1.Ejercicio5;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

public class Ejercicio5 {
    public static void main(String[] args) {
        try {
            //crearDirectorio("ejemplo5");
            //crearDirectorioV2("ejemplo5|");
            crearFicheroDeTexto("ejemplo5", "hola buenas tardes");
            borrarFicheroDeTexto("ejemplo5");
            mostrarFicherosDeCarpeta("Boletin1");

        } catch (DirectorioException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Creamos un directorio con el nombre pasado por parámetros, si no
     * existe lanzará excepción gracias al mkdir
     *
     * @param nombreDirectorio nombre del directorio
     * @return la ruta
     */
    public static File crearDirectorio(String nombreDirectorio) throws DirectorioException {
        File directorio = new File("Boletin_Tema6/" + nombreDirectorio);
        if (!directorio.mkdir()) {
            throw new DirectorioException("No se ha podido crear la carpeta");
        }
        return directorio;
    }

    /**
     * Creamos un directorio con el nombre pasado por parámetros en caso de que se pueda crear,
     * si no existe se lanzará mi excepción capturando antes las excepciones correspondientes
     *
     * @param nombreDirectorio
     * @return la dirección
     * @throws DirectorioException
     */
    public static Path crearDirectorioV2(String nombreDirectorio) throws DirectorioException {
        try {
            Path directorio = Path.of("Boletin_Tema6/", nombreDirectorio);
            Files.createDirectory(directorio);
            return directorio;

        } catch (IOException | InvalidPathException e) {
            throw new DirectorioException("Error: " + e.getMessage());

        }
    }

    /**
     * Creamos un fichero con el nombre pasado por parámetros, en caso de que ya exista
     * se lanzará excepción
     *
     * @param nombreFichero
     * @param contenido
     * @throws DirectorioException
     */
    public static Path crearFicheroDeTexto(String nombreFichero, String contenido) throws DirectorioException {
        try {
            Path fichero = Path.of("Boletin_Tema6/" + nombreFichero);
            Files.createFile(fichero);
            // Con Files.writeString se asegura de que se han escrito todas las líneas, se cierra automáticamente
            Files.writeString(fichero, contenido);
            return fichero;

        } catch (IOException | InvalidPathException e) {
            throw new DirectorioException("Error: " + e.getMessage());
        }
    }

    /**
     * Eliminamos un fichero en caso de que exista, en caso de que no el 'deleteIfExists'
     * lanzará un false y lo podemos atrapar en un if para lanzar la excepción
     *
     * @param nombreFichero
     * @throws DirectorioException
     */
    public static void borrarFicheroDeTexto(String nombreFichero) throws DirectorioException {
        try {
            Path fichero = Path.of("Boletin_Tema6/" + nombreFichero);
            if (!Files.deleteIfExists(fichero)) {
                throw new DirectorioException("No se ha podido borrar el fichero, no existe");
            }

        } catch (IOException | InvalidPathException e) {
            throw new DirectorioException("Error: " + e.getMessage());
        }
    }

    /**
     * Mostramos por pantalla los ficheros que pertencen al fichero pasado
     * por parámetros
     *
     * @param nombreDirectorio
     * @throws DirectorioException
     */
    public static void mostrarFicherosDeCarpeta(String nombreDirectorio) throws DirectorioException {
        /* Tenemos que meter dentro de este tipo de 'try()' el cuál nos permite cerrar los objetos que sean
         * autocloseable, en este caso, el flujo, ya que si falla el programa se quedaría abierto el flujo */
        try (Stream<Path> flujo = Files.list(Path.of("Boletin_Tema6/src/Ejercicio5" + nombreDirectorio))) {
            flujo.filter(Files::isRegularFile)
                    .sorted(Comparator.comparing(Path::getFileName)).forEach(System.out::println);

        } catch (IOException | InvalidPathException e) {
            throw new DirectorioException(e.getMessage());
        }
    }
}