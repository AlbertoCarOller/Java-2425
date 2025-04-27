package Extra.Ejercicio25E;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Ejercicio25E {
    public static void main(String[] args) {
        try {
            validarInfo();

        } catch (Ejercicio25EException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a validar la información de cada línea, en caso de las
     * correctas se imprimirán en 'InfoCorrectas.txt' tal cual, pero en el
     * caso de las incorrectas se imprimirán en 'InfoIncorrecta.txt' con
     * los problemas que tienen
     * @throws Ejercicio25EException
     */
    public static void validarInfo() throws Ejercicio25EException {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Extra/Ejercicio25E/TodaInformacion.txt");
            Path escribirleV = Path.of("Boletin_Tema6/src/Extra/Ejercicio25E/InfoCorrecta.txt");
            Path escribirleI = Path.of("Boletin_tema6/src/Extra/Ejercicio25E/InfoIncorrecta.txt");
            try (Stream<String> flujo = Files.lines(leerlo);
                 PrintWriter pwV = new PrintWriter(new FileWriter(escribirleV.toFile()));
                 PrintWriter pwI = new PrintWriter(new FileWriter(escribirleI.toFile()))) {
                List<Pattern> comprobaciones = List.of(Pattern.compile("^ID: \\d{4}"),
                        Pattern.compile("Nombre: \\p{Lu}\\p{Ll}+( \\p{Lu}\\p{Lu}+)*"),
                        Pattern.compile("Fecha de Nacimiento: (0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(19[0-9]{2}" +
                                "|20[0-9]{2})"), Pattern.compile("Correo: [a-z][a-z_.-]+@dominio\\.com$"));
                flujo.forEach(l -> {
                    StringBuilder sb = new StringBuilder(l);
                    boolean alMenosUn = false;
                    for (int i = 0; i < comprobaciones.size(); i++) {
                        Matcher matcher = comprobaciones.get(i).matcher(l);
                        if (!matcher.find()) {
                            alMenosUn = true;
                            appendMensaje(i, sb);
                        }
                    }
                    if (alMenosUn) {
                        pwI.println(sb);

                    } else {
                        pwV.println(sb);
                    }
                });
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio25EException(e.getMessage());
        }
    }

    /**
     * Este método va a añadir los mensajes correspondientes al StringBuilder
     *
     * @param i  el índice de la comprobación
     * @param sb el StringBuilder al que le vamos a poner el mensaje
     */
    public static void appendMensaje(int i, StringBuilder sb) {
        switch (i) {
            case 0:
                sb.append("\nEl id no es correcto");
                break;
            case 1:
                sb.append("\nEl nombre no es correcto");
                break;
            case 2:
                sb.append("\nLa fecha de naciemiento no es correcta");
                break;
            case 3:
                sb.append("\nEl correo no es válido");
                break;
        }
    }
}