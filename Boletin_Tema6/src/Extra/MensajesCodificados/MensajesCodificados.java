package Extra.MensajesCodificados;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MensajesCodificados {
    public static void main(String[] args) {
        try {
            validarMensajesCodificados();

        } catch (MensajesCodificadosExcepcion e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a validar los mensajes codificados en el fichero .txt
     * y los correctos pasarán con otro formato al fichero .json
     *
     * @throws MensajesCodificadosExcepcion
     */
    public static void validarMensajesCodificados() throws MensajesCodificadosExcepcion {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Extra/MensajesCodificados/MensajesCodificados.txt");
            Path escribir = Path.of("Boletin_Tema6/src/Extra/MensajesCodificados/MensajesCodificadosValidos.json");
            try (BufferedReader br = new BufferedReader(new FileReader(leerlo.toFile()));
                 PrintWriter pw = new PrintWriter(escribir.toFile())) {
                List<String> lineas = new ArrayList<>();
                String linea;
                while ((linea = br.readLine()) != null) {
                    lineas.add(linea);
                }
                AtomicInteger atomicInteger = new AtomicInteger(0);
                Pattern pattern = Pattern.compile("^\\[ID:(?<ID>[0-9]{4})] DE:(?<EMISOR>[A-Z][a-zA-Z]+) A:(?<RECEPTOR>[A-Z][a-zA-Z]+)" +
                        " <FECHA:(?<FECHA>(?:0[1-9]|[12][0-9]|3[01])-(?:0[1-9]|1[0-2])-(?:19[0-9]{2}|20[0-9]{2}))>" +
                        " -- MSG:(?<MENSAJE>\"\\p{Lu}[\\p{L}\\p{N}\\p{P}\\s]+\")$", Pattern.UNICODE_CHARACTER_CLASS);
                pw.println("{");
                pw.println(" \"" + escribir.toFile().getName() + "\": [");
                long lengt = lineas.stream().filter(s -> {
                    Matcher matcher = pattern.matcher(s);
                    if (matcher.matches()) {
                        return true;
                    }
                    return false;
                }).count();
                lineas.stream().map(s -> {
                    Matcher matcher = pattern.matcher(s);
                    if (matcher.matches()) {
                        if (atomicInteger.incrementAndGet() == lengt) {
                            return "  {\n   \"id\": " + matcher.group("ID") +
                                    ",\n   \"emisor\": \"" + matcher.group("EMISOR") + "\"" + ",\n" +
                                    "   \"receptor\": \"" + matcher.group("RECEPTOR") + "\"" +
                                    ",\n   \"fecha\": \"" + matcher.group("FECHA") + "\"" +
                                    ",\n   \"mensaje\": " + matcher.group("MENSAJE") +
                                    "\n  }";
                        }
                        return "  {\n   \"id\": " + matcher.group("ID") +
                                ",\n   \"emisor\": \"" + matcher.group("EMISOR") + "\"" + ",\n" +
                                "   \"receptor\": \"" + matcher.group("RECEPTOR") + "\"" +
                                ",\n   \"fecha\": \"" + matcher.group("FECHA") + "\"" +
                                ",\n   \"mensaje\": " + matcher.group("MENSAJE") +
                                "\n  },";
                    }
                    return "";
                }).forEach(s -> {
                    if (!s.isEmpty()) {
                        pw.println(s);
                    }
                });
                pw.println(" ]");
                pw.println("}");

            }
        } catch (InvalidPathException | IOException e) {
            throw new MensajesCodificadosExcepcion(e.getMessage());
        }
    }
}