package Extra.PracticaZip;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class PracticaZip {
    public static void main(String[] args) {
        try {
            comprimirEnZip();

        } catch (PracticaZipException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método comprime todos los ficheros contenidos de un directorio
     * especificado a otro archivo zip especificado
     *
     * @throws PracticaZipException
     */
    public static void comprimirEnZip() throws PracticaZipException {
        try {
            // Este es el fichero de donde vamos a coger todos sus ficheros y comprimirlos
            Path directorio = Path.of("Boletin_tema6/src/Extra/PracticaZip/ArchivosAComprimir");
            // Este es el fichero donde vamos a guardar el contenido trasformado en ZIP
            Path archivoZip = Path.of("Boletin_Tema6/src/Extra/PracticaZip/archivos.zip");
            Files.deleteIfExists(archivoZip);
            Files.createFile(archivoZip);
            /* Esto es un flujo de los ficheros que queremos comprimir en un archivo ZIP.
             * .walk() y .list() listan ficheros, directorios, etc. Hay una diferencia aparte
             * de la recursividad entre estas dos, si bien walk lo hace de forma recursiva,
             * también incluye al directorio base, pero list no, aunque sí los archivos dentro
             * de este */
            try (Stream<Path> flujo = Files.walk(directorio);
                    /* ZipOutputStream es como una "mochila" la cual va guardando y comprimiendo dentro de ella
                     * los archivos especificados, acepta por parámetros Files.newOutputStream, esto nos
                     * permitirá escribir dentro de esta, se acumulará dentro de archivoZip */
                 ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(archivoZip))) {
                flujo.filter(Files::isRegularFile).forEach(p -> {
                    /* Creamos una entrada de zip con el nombre del archivo a comprimir, la única forma de
                     * meter el archivo en ZipOutputStream es creando una entrada ZipEntry, si metemos
                     * solo el nombre, la entrada solo corresponderá a un fichero con el tal nombre,
                     * para mantener una estructura completa necesitamos la ruta completa, relativice
                     * te da un path que va de un directorio base que en este caso es 'ArchivosAComprimir'
                     * hasta p (que son los path dentro del archivo base) */
                    ZipEntry zipEntry = new ZipEntry(directorio.relativize(p).toString());
                    try {
                        // Enviamos al ZipOutputStream la entrada ZipEntry
                        zipOutputStream.putNextEntry(zipEntry);
                        /* Con el .copy() copiamos el contenido del archivo original al ZipOutputStream,
                         * este comprimirá el contenido del archivo y asociará el contenido a la entrada
                         * automáticamente, PERO IMPORTANTE HABRÁ QUE CERRAR LA ENTRADA PARA QUE FUNCIONE
                         * DE FORMA CORRECTA. Lanzará exception en caso de que p no sea un regular file,
                         * las excepciones que puede lanzar FileSystemException: ...: Is a directory o
                         * AccessDeniedException: ...  */
                        Files.copy(p, zipOutputStream);
                        // IMPORTANTE: CERRAMOS LA ENTRADA ACTUAL (Método de ZipOutputStream)
                        zipOutputStream.closeEntry();

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

        } catch (IOException | RuntimeException e) {
            throw new PracticaZipException(e.getMessage());
        }
    }
}