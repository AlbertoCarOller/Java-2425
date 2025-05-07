package Boletin4.Ejercicio4B4;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ejercicio4B4 {
    public static void main(String[] args) {
        try {
            leerArchivoYValidarPeso();
        } catch (Ejercicio4B4Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método crea por cada línea pasada por teclado un PrintWriter,
     * este escribe en log.txt el cual por cada líena será uno nuevo, ya que
     * está envuelto por el pw, si se supera el tamaño especificado se
     * renombrará el fichero con el nombre especificado, en caso de que no se
     * supere el tamaño se creará el log, pero se borrará en tiempo de ejecución,
     * ya que solo es un intermediario y además puede producir errores si se crean
     * más de un log.txt
     *
     * @throws Ejercicio4B4Exception
     */
    public static void leerArchivoYValidarPeso() throws Ejercicio4B4Exception {
        try {
            Path leer = Path.of("Boletin_Tema6/src/Boletin4/Ejercicio4B/log.txt");
            // ImputStreamReader permite la lectura por teclado
            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                String linea;
                while ((linea = br.readLine()) != null && !linea.equalsIgnoreCase("fin")) {
                    /* Creamos un PrintWriter por cada línea leída con la idea de que al cerrar
                     * este y se lea la siguiente línea no haya problemas */
                    try (PrintWriter pw = new PrintWriter(new FileWriter(leer.toFile()))) {
                        pw.println(linea);
                        // Forzamos a que se escriba ya en el fichero
                        pw.flush();
                        if (Files.size(leer) > 20) {
                            // Cerramos el PrintWriter para no interferir en el flujo al modificar el fichero leer
                            pw.close();
                            // Llamamos al método
                            trasformarFicheroYNuevo(leer);

                        } else {
                            // Eliminamos el log.txt para que no haya problema después, por si se crean más de uno
                            Files.deleteIfExists(leer);
                        }
                    }
                }
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio4B4Exception(e.getMessage());
        }
    }

    /**
     * Este método es un apoyo para el anterior, en este se renombra el
     * log.txt
     *
     * @param leerlo
     * @throws Ejercicio4B4Exception
     * @throws IOException
     */
    public static void trasformarFicheroYNuevo(Path leerlo) throws Ejercicio4B4Exception, IOException {
        try {
            // Renombramos el path actual con el nombre especificado para el log
            Files.move(leerlo, Path.of("Boletin_Tema6/src/Boletin4/Ejercicio4B/" +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".txt"));

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio4B4Exception(e.getMessage());
        }
    }
}