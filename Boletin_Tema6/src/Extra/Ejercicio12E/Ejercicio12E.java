package Extra.Ejercicio12E;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio12E {
    public static void main(String[] args) {
        try {
            validarFichero("RutaFicheros.txt");

        } catch (Ejercicio12EException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método comprueba las rutas que contienen el fichero especificado,
     * comprueban que sean válidos con las expresiones regulares, que existan
     * y que sean ficheros únicamente
     *
     * @param nombreFichero
     * @throws Ejercicio12EException
     */
    public static void validarFichero(String nombreFichero) throws Ejercicio12EException {
        try {
            Path leer = Path.of("Boletin_Tema6/src/Extra/Ejercicio12E/" + nombreFichero);
            Path escribir = Path.of("Boletin_Tema6/src/Extra/Ejercicio12E/RutaFicherosValidos.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(leer.toFile()));
                 PrintWriter pw = new PrintWriter(new FileWriter(escribir.toFile()))) {
                Path ruta;
                String rutaS;
                while ((rutaS = br.readLine()) != null) {
                    Pattern pattern = Pattern.compile("^[A-Za-z_.-]+[0-9]{4}\\.[a-z]{3,}$");
                    Matcher matcher = pattern.matcher(rutaS.split("/")[rutaS.split("/").length - 1]);
                    if (matcher.matches()) {
                        ruta = Path.of(rutaS);
                        if (Files.exists(ruta)) {
                            if (Files.isRegularFile(ruta)) {
                                pw.println(rutaS);
                            }
                        }
                    }
                }
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio12EException(e.getMessage());
        }
    }
}