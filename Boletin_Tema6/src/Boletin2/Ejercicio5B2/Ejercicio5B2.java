package Boletin2.Ejercicio5B2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio5B2 {
    public static void main(String[] args) {
        try {
            validarLineas();

        } catch (Ejercicio5B2Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a validar las líneas del fichero y los meterá en una
     * lista
     *
     * @throws Ejercicio5B2Exception
     */
    public static void validarLineas() throws Ejercicio5B2Exception {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Boletin2/Ejercicio5B2/NombresFicheros.txt");
            try (BufferedReader br = new BufferedReader(new FileReader(leerlo.toFile()))) {
                String linea;
                List<String> lineasValidas = new ArrayList<>();
                while ((linea = br.readLine()) != null) {
                    if (linea.matches("^F [A-Za-z]+\\.[a-z]{3,}$")) {
                        lineasValidas.add(linea);

                    } else {
                        System.out.println("El fichero " + linea.split(" ")[linea.split(" ").length - 1] +
                                " no se podido crear");
                    }
                }
                // Llamo al método que va a crear los ficheros
                crearFicheros(lineasValidas);
            }

        } catch (InvalidPathException | IOException e) {
            throw new Ejercicio5B2Exception(e.getMessage());
        }
    }

    /**
     * Este método creará los ficheros correctos en caso de que
     * no existan ya previamente, es un método auxiliar
     *
     * @param lineasCorrectas
     * @throws Ejercicio5B2Exception
     */
    public static void crearFicheros(List<String> lineasCorrectas) throws Ejercicio5B2Exception {
        try {
            lineasCorrectas.stream().forEach(l -> {
                Path path = Path.of("Boletin_Tema6/src/Boletin2/Ejercicio5B2/" +
                        l.split(" ")[l.split(" ").length - 1]);
                if (!Files.exists(path)) {
                    try {
                        Files.createFile(path);
                        System.out.println("Se ha creado el fichero " + path.toFile().getName());

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    System.out.println("Ya existe el fichero " + path.toFile().getName());
                }
            });
        } catch (RuntimeException e) {
            throw new Ejercicio5B2Exception(e.getMessage());
        }
    }
}