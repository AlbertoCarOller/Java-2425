package Extra.Ejercicio11E;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio11E {
    public static void main(String[] args) {
        try {
            validarCorreos();

        } catch (Ejercicio11EExcepcion e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método valida los correos del fichero especificado, en caso de que sea correcto
     * pasará al nuevo fichero donde se acumulan los correos válidos
     *
     * @throws Ejercicio11EExcepcion
     */
    public static void validarCorreos() throws Ejercicio11EExcepcion {
        try {
            Path leer = Path.of("Boletin_Tema6/src/Extra/Ejercicio11E/CorreosValidar.txt");
            Path escribir = Path.of("Boletin_Tema6/src/Extra/Ejercicio11E/CorreosCorrectos.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(leer.toFile()));
                 PrintWriter pw = new PrintWriter(new FileWriter(escribir.toFile()))) {
                String correo;
                while ((correo = br.readLine()) != null) {
                    String[] s = correo.split(",");
                    Pattern pattern = Pattern.compile("^[a-z][a-z._-]{2,}@(gmail\\.com|outlook\\.com|hotmail\\.(com|es))$");
                    Matcher matcher = pattern.matcher(s[s.length - 1]);
                    if (matcher.matches()) {
                        pw.println(correo);
                    }
                }
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio11EExcepcion(e.getMessage());
        }
    }
}