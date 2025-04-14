package Extra.Ejercicio8E;

import Extra.Ejercicio7E.Ejercicio7EException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio8E {
    public static void main(String[] args) {
        try {
            comprobarInformacionEmpleado();

        } catch (Ejercicio7EException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a mostrar los nombres de las personas que tengan todos sus "atributos"
     * válidos del fichero a mirar
     *
     * @throws Ejercicio7EException
     */
    public static void comprobarInformacionEmpleado() throws Ejercicio7EException {
        try {
            Path leerFichero = Path.of("Boletin_Tema6/src/Extra/Ejercicio8E/FormatoEmpleados.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(leerFichero.toFile()))) {
                Set<String> infoEmpleados = new HashSet<>();
                String infoEmpleado;
                while ((infoEmpleado = br.readLine()) != null) {
                    infoEmpleados.add(infoEmpleado);
                }
                infoEmpleados.stream().filter(s -> {
                    Pattern pattern = Pattern.compile("^\\p{Lu}\\p{Ll}{2,} \\p{Lu}\\p{Ll}{2,} \\p{Lu}\\p{Ll}{2,}" +
                                    " [0-9]{8}[A-Z] [67][0-9]{8} [a-z][a-z._-]+@(gmail\\.com|hotmail\\.com|outlook\\.com)",
                            Pattern.UNICODE_CHARACTER_CLASS);
                    Matcher matcher = pattern.matcher(s);
                    if (matcher.matches()) {
                        return true;
                    }
                    return false;
                }).forEach(s -> {
                    String[] a = s.split(" ");
                    System.out.println(a[0] + " " + a[1] + " " + a[2]);
                });
            }
        } catch (IOException | InvalidPathException e) {
            throw new Ejercicio7EException(e.getMessage());
        }
    }
}