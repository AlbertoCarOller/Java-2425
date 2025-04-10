package Extra.Ejercicio5E;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ejercicio5E {
    public static void main(String[] args) {
        try {
            validarCodigoProducto();
            eliminarCodigoEmpezadoPor('E');

        } catch (Ejercicio5EExcepcion | RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método validará los códigos de productos que se encuentran en el fichero especificado,
     * se creará un nuevo fichero con solo los códigos válidos
     *
     * @throws Ejercicio5EExcepcion
     */
    public static void validarCodigoProducto() throws Ejercicio5EExcepcion {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Extra/Ejercicio5E/CodigoProducto.txt");
            Path escribir = Files.createFile(Path.of("Boletin_Tema6/src/Extra/Ejercicio5E/CodigosProductoValidos.txt"));
            try (BufferedReader bf = new BufferedReader(new FileReader(leerlo.toFile()));
                 PrintWriter pw = new PrintWriter(new FileWriter(escribir.toFile()))) {
                Set<String> codigosProducto = new HashSet<>();
                String codigoProduto;
                while ((codigoProduto = bf.readLine()) != null) {
                    codigosProducto.add(codigoProduto);
                }
                codigosProducto.stream().filter(s -> {
                    Pattern pattern = Pattern.compile("^[A-Z]{3}-[0-9]{4}$");
                    Matcher matcher = pattern.matcher(s);
                    if (matcher.matches()) {
                        return true;
                    }
                    return false;
                }).forEach(pw::println);
            }

        } catch (IOException | InvalidPathException e) {
            throw new Ejercicio5EExcepcion(e.getMessage());
        }
    }

    /**
     * Este método cogerá del fichero con códigos válidos los que no empiecen por
     * el char especificado, creará un fichero nuevo con los que pasen el filtro
     * y eliminará el fichero anterior
     *
     * @param empieza
     * @throws Ejercicio5EExcepcion
     */
    public static void eliminarCodigoEmpezadoPor(char empieza) throws Ejercicio5EExcepcion {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Extra/Ejercicio5E/CodigosProductoValidos.txt");
            Path escribirlo = Files.createFile(Path.of("Boletin_Tema6/src/Extra/Ejercicio5E/CodigosProductosValidos.txt"));
            try (BufferedReader br = new BufferedReader(new FileReader(leerlo.toFile()));
                 BufferedWriter bw = new BufferedWriter(new FileWriter(escribirlo.toFile()))) {
                Set<String> codigos = new HashSet<>();
                String codigo;
                while ((codigo = br.readLine()) != null) {
                    codigos.add(codigo);
                }
                codigos.stream().filter(s -> s.charAt(0) != empieza)
                        .forEach(s -> {
                            try {
                                bw.write(s + "\n");

                            } catch (IOException e) {
                                throw new RuntimeException(e.getMessage());
                            }
                        });
            }
            // Una vez cerrado los flujos podemos eliminar el archivo anterior
            Files.delete(leerlo);

            // TODO: preguntar por qué no hace falta hacer un flush del BufferedWriter
        } catch (IOException | InvalidPathException e) {
            throw new Ejercicio5EExcepcion(e.getMessage());
        }
    }
}