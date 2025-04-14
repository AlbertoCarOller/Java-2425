package Extra.Ejercicio13E;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio13E {
    public static void main(String[] args) {
        try {
            System.out.println(contarCorreosValidos());

        } catch (Ejercicio13EExcepcion e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a contar los correos válidos dentro del fichero especificado
     * y pasará los ficheros válidos al fichero que contienen los correos válidos
     *
     * @return número de correos válidos
     * @throws Ejercicio13EExcepcion
     */
    public static AtomicInteger contarCorreosValidos() throws Ejercicio13EExcepcion {
        try {
            Path leer = Path.of("Boletin_Tema6/src/Extra/Ejercicio13E/CorreosAValidar.txt");
            Path escribir = Path.of("Boletin_Tema6/src/Extra/Ejercicio13E/CorreosValidados.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(leer.toFile()));
                 PrintWriter pw = new PrintWriter(new FileWriter(escribir.toFile()))) {
                Set<String> correos = new HashSet<>();
                String correo;
                while ((correo = br.readLine()) != null) {
                    correos.add(correo);
                }
                AtomicInteger correosValidos = new AtomicInteger(0);
                correos.stream().filter(s -> {
                    Pattern pattern = Pattern.compile("^[a-z][a-z._-]+@(gmail\\.com|outlook\\.com|hotmail\\.(es|com))$");
                    Matcher matcher = pattern.matcher(s);
                    if (matcher.matches()) {
                        return true;
                    }
                    return false;
                }).forEach(s -> {
                    correosValidos.incrementAndGet();
                    pw.println(s);
                });
                return correosValidos;
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio13EExcepcion(e.getMessage());
        }
    }
}