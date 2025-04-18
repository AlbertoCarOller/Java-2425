package Extra.EjercicioNASAJPL;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NasaJPL {
    public static void main(String[] args) {
        try {
            //System.out.println(validarDatos());
            System.out.println(validarDatosOrdenados());

        } catch (NasaJPLEException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a coger los objetos de 'datos.json' e imprimirlos
     * en 'limpio.json' y va a devolver un String con la fecha máxima,
     * mínima y media
     *
     * @return String
     * @throws NasaJPLEException
     */
    public static String validarDatos() throws NasaJPLEException {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Extra/EjercicioNASAJPL/datos.json");
            Path escribir = Path.of("Boletin_Tema6/src/Extra/EjercicioNASAJPL/limpio.json");
            try (BufferedReader br = new BufferedReader(new FileReader(leerlo.toFile()));
                 PrintWriter pw = new PrintWriter(escribir.toFile())) {
                List<String> datosS = new ArrayList<>();
                String linea;
                while ((linea = br.readLine()) != null) {
                    datosS.add(linea.trim());
                }
                pw.println("{");
                pw.println("  \"" + escribir.toFile().getName() + "\": [");
                List<String> datosValidos = datosS.stream().filter(s -> {
                    Pattern pattern = Pattern.compile("^\\{\"fecha\":\\s*\"(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(19[0-9]{2}|20[0-9]{2})\"," +
                            "\\s*\"temperatura\":\\s*\"(-[0-9]+|[0-9]+)\\.[0-9]+C\"},?$");
                    Matcher matcher = pattern.matcher(s);
                    if (matcher.matches()) {
                        return true;
                    }
                    return false;
                }).toList();
                datosValidos.forEach(s -> pw.println("    " + s));
                pw.println("  ]");
                pw.println("}");
                return "Máx:" + fechaConMaxTemperatura(datosValidos) + ", Min: " + fechaConMinTemperatura(datosValidos)
                        + " y Media: " + temperaturaMedia(datosValidos);
            }

        } catch (InvalidPathException | IOException e) {
            throw new NasaJPLEException(e.getMessage());
        }
    }

    /**
     * Este método va a coger los objetos de 'datos.json' e imprimirlos
     * en 'limpio.json' y va a devolver un String con la fecha máxima,
     * mínima y media de forma ordenada
     *
     * @return String
     * @throws NasaJPLEException
     */
    public static String validarDatosOrdenados() throws NasaJPLEException {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Extra/EjercicioNASAJPL/datos.json");
            Path escribir = Path.of("Boletin_Tema6/src/Extra/EjercicioNASAJPL/limpio.json");
            try (BufferedReader br = new BufferedReader(new FileReader(leerlo.toFile()));
                 PrintWriter pw = new PrintWriter(escribir.toFile())) {
                List<String> datosS = new ArrayList<>();
                String linea;
                while ((linea = br.readLine()) != null) {
                    datosS.add(linea.trim());
                }
                pw.println("{");
                pw.println("  \"" + escribir.toFile().getName() + "\": [");
                AtomicInteger lenght = new AtomicInteger(0);
                AtomicInteger indice = new AtomicInteger(0);
                List<String> datosValidos = datosS.stream().filter(s -> {
                    Pattern pattern = Pattern.compile("^\\{\"fecha\":\\s*\"(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(19[0-9]{2}|20[0-9]{2})\"," +
                            "\\s*\"temperatura\":\\s*\"(-[0-9]+|[0-9]+)\\.[0-9]+C\"},?$");
                    Matcher matcher = pattern.matcher(s);
                    if (matcher.matches()) {
                        lenght.incrementAndGet();
                        return true;
                    }
                    return false;
                }).toList();
                datosValidos.stream().sorted(Comparator.comparing(s -> LocalDate.parse(s.substring(11, 21),
                        DateTimeFormatter.ofPattern("dd-MM-yyyy")))).toList().reversed().forEach(s -> {
                    if (indice.incrementAndGet() == lenght.get()) {
                        if (s.charAt(s.length() - 1) == ',') {
                            char[] c = s.toCharArray();
                            c[c.length - 1] = ' ';
                            String s1 = new String(c);
                            pw.println("    " + s1.trim());

                        } else {
                            pw.println("    " + s);
                        }

                    } else {
                        if (s.charAt(s.length() - 1) != ',') {
                            pw.println("    " + s + ",");

                        } else {
                            pw.println("    " + s);
                        }
                    }
                });
                pw.println("  ]");
                pw.println("}");
                return "Máx:" + fechaConMaxTemperatura(datosValidos) + ", Min: " + fechaConMinTemperatura(datosValidos)
                        + " y Media: " + temperaturaMedia(datosValidos);
            }

        } catch (InvalidPathException | IOException e) {
            throw new NasaJPLEException(e.getMessage());
        }
    }

    public static String fechaConMaxTemperatura(List<String> lista) throws NasaJPLEException {
        return lista.stream().map(s -> Map.entry(s.substring(0, 22), Double.parseDouble(s.substring(41, 43))))
                .max(Map.Entry.comparingByValue()).orElseThrow(() -> new NasaJPLEException("No hay un max")).getKey();
    }

    public static String fechaConMinTemperatura(List<String> lista) throws NasaJPLEException {
        return lista.stream().map(s -> Map.entry(s.substring(0, 22), Double.parseDouble(s.substring(41, 43))))
                .min(Map.Entry.comparingByValue()).orElseThrow(() -> new NasaJPLEException("No hay un max")).getKey();
    }

    public static Double temperaturaMedia(List<String> lista) throws NasaJPLEException {
        return lista.stream().map(s -> Map.entry(s.substring(0, 22), Double.parseDouble(s.substring(41, 43))))
                .mapToDouble(Map.Entry::getValue).average().orElseThrow(() -> new NasaJPLEException("No hay media"));
    }
}