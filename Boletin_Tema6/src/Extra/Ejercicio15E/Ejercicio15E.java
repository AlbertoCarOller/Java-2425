package Extra.Ejercicio15E;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio15E {
    public static void main(String[] args) {
        try {
            System.out.println(contarFrases("FrasesAContar.txt"));

        } catch (Ejercicio15EException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a contar las líneas que contienen fechas, números de teléfono
     * y las que no tienen ninguna de las dos anteriores
     *
     * @param nombreFichero
     * @return string del recuento
     * @throws Ejercicio15EException
     */
    public static String contarFrases(String nombreFichero) throws Ejercicio15EException {
        try {
            Path leer = Path.of("Boletin_Tema6/src/Extra/Ejercicio15E/" + nombreFichero);
            try (BufferedReader br = new BufferedReader(new FileReader(leer.toFile()))) {
                String linea;
                List<Pattern> comprobaciones = new ArrayList<>();
                Pattern pattern = Pattern.compile("^(0[1-9]|[12][0-9]|3[0-1])[-/](0[1-9]|1[0-2])[-/](19[0-9]{2}" +
                        "|20([0-1][0-9]|2[0-5]))$");
                Pattern pattern1 = Pattern.compile("^[67]\\d{8}");
                comprobaciones.add(pattern);
                comprobaciones.add(pattern1);
                Matcher matcher;
                int contadorLineaFecha = 0;
                int contadorLineaNumTelef = 0;
                int contadorNada = 0;
                while ((linea = br.readLine()) != null) {
                    String[] lineaSeparada = linea.split(" ");
                    for (int i = 0; i < lineaSeparada.length; i++) {
                        boolean alMenos = false;
                        for (int j = 0; j < comprobaciones.size(); j++) {
                            matcher = comprobaciones.get(j).matcher(lineaSeparada[i]);
                            if (matcher.matches()) {
                                if (j == 0) {
                                    contadorLineaFecha++;
                                    alMenos = true;
                                    continue;
                                }
                                if (j == 1) {
                                    contadorLineaNumTelef++;
                                    alMenos = true;
                                }
                            }
                        }
                        if (!alMenos) {
                            contadorNada++;
                        }
                    }
                }
                return "Lineas con fecha: " + contadorLineaFecha + "\nLineas con número de teléfono: " + contadorLineaNumTelef
                        + "\nLineas sin nada: " + contadorNada;
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio15EException(e.getMessage());
        }
    }
}