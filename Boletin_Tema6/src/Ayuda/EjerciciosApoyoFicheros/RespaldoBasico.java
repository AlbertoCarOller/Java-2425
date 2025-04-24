package Ayuda.EjerciciosApoyoFicheros;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class RespaldoBasico {
    public static void main(String[] args) {
        try {
            System.out.println("Prueba de buffer propio con FileReader");
            fileReaderP();
            System.out.println("Prueba de FileReader");
            fileReaderPV2();
            System.out.println("Prueba de FileWriter");
            fileWriterP();
            System.out.println("Prueba de BufferedReader");
            bufferedReaderMarkReset();
            System.out.println("Prueba de BufferedWriter");
            bufferedWriterFileWriter();
            System.out.println("Prueba de ImputStreamReader");
            bufferedReaderImputStreamReader();

        } catch (RespaldoBasicoException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * En este método probamos el funcionamiento de FileReader con un
     * buffer propio creado
     * @throws RespaldoBasicoException
     */
    public static void fileReaderP() throws RespaldoBasicoException {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Ayuda/EjerciciosDeApoyo/Ejercicio2A/FicheroEJ2A.txt");
            // Creamos un buffer de 5 caracteres de capacidad
            char[] buffer = new char[5];
            try (FileReader fr = new FileReader(leerlo.toFile())) {
                int limite;
                /* el .read() sobrecargado acepta un buffer, que es un array de char, este método sobrecargado devuelve
                 * un entero que representa el número de caracteres que ha leído, -1 cuando ya no hay más caracteres
                 * por leer */
                while ((limite = fr.read(buffer)) != -1) {
                    /* Imprimimos los caracteres dentro del buffer, hasta el límite, es decir hasta antes que
                     * devuelva -1, va de la posición 0 hasta el límite */
                    System.out.println(new String(buffer, 0, limite));
                    /* Si mostráramos directamente el buffer, si imprime los 5 caracteres por obligación, imprimirá
                     * el residuo que haya dentro del buffer, ya que los caracteres no alcanzarían para mostrar los 5 */
                    //System.out.println(buffer);
                }
                int caracter;
                while ((caracter = fr.read()) != -1) {
                    System.out.println(caracter + "-" + (char) caracter);
                }
            }

        } catch (InvalidPathException | IOException e) {
            throw new RespaldoBasicoException(e.getMessage());
        }
    }

    /**
     * Este método muestra por pantalla la letra leída y el número ASCII que corresponde a este,
     * lee letra por letra
     *
     * @throws RespaldoBasicoException
     */
    public static void fileReaderPV2() throws RespaldoBasicoException {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Ayuda/EjerciciosDeApoyo/Ejercicio2A/FicheroEJ2A.txt");
            try (FileReader fr = new FileReader(leerlo.toFile())) {
                // Guardamos el entero, que a su vez al hacer un casting de char nos devolverá la letra correspondiente
                int caracter;
                while ((caracter = fr.read()) != -1) {
                    /* Mostramos el número de char y después mostramos el char que corresponde, el número es ASCII,
                     * por eso corresponde a un char */
                    System.out.println(caracter + "-" + (char) caracter);
                }
            }

        } catch (InvalidPathException | IOException e) {
            throw new RespaldoBasicoException(e.getMessage());
        }
    }

    /**
     * Este método prueba el FileWriter y sus diversos métodos para escribir en
     * el fichero especificado
     *
     * @throws RespaldoBasicoException
     */
    public static void fileWriterP() throws RespaldoBasicoException {
        try {
            Path escribirle = Path.of("Boletin_Tema6/src/Ayuda/EjerciciosDeApoyo/Ejercicio2A/FicheroEJ2A2.txt");
            try (FileWriter fw = new FileWriter(escribirle.toFile())) {
                // write() en este caso le pasamos directamente la cadena de texto
                fw.write("Mi nombre: ");
                // append() junta los caracteres y lo escribe en el fichero en concreto
                fw.append('A').append('l').append('b').append('e').append('r').append('t').append('o');
                // Creamos un buffer con los caracteres a escribir
                char[] buffer = {'\n', 'A', 'L', 'B', 'E', 'R', 'T', 'O'};
                // write() sobrecargado recibe el buffer y lo escribe en el fichero
                fw.write(buffer);
            }

        } catch (InvalidPathException | IOException e) {
            throw new RespaldoBasicoException(e.getMessage());
        }
    }

    /**
     * Este método va a leer la primera línea del fichero especificado, pero antes de leer
     * va a colocar un mark, cuando se haya leído la primera línea, se hace un reset y vuelve
     * al principio, se imprimen por pantalla la primera línea dos veces
     *
     * @throws RespaldoBasicoException
     */
    public static void bufferedReaderMarkReset() throws RespaldoBasicoException {
        try {
            Path leerlo = Path.of("Boletin_Tema6/src/Ayuda/EjerciciosDeApoyo/Ejercicio2A/FicheroEJ1A.txt");
            FileReader fr = new FileReader(leerlo.toFile());
            // Para utilizar el mark y el reset, el File debe estar envuelto en un Buffered
            try (BufferedReader br = new BufferedReader(fr)) {
                String linea;
                /* Hacemos un mark, este funciona a modo de marca para cuando haga un reset volver hasta donde
                 * coloqué el mark, el mark va a ser físicamente literal, por ejemplo, si leo 3 líneas, hago un
                 * mark y después leo otras 3 líneas y hago un reset va a volver a la línea donde llamé al mark.
                 * El entero pasado por parámetros es decir el 'readAheadLimit', es el número máximo de caracteres
                 * que puedes leer después del mark, si lees más de lo especificado, el mark se perderá */
                br.mark(100);
                if ((linea = br.readLine()) != null) {
                    System.out.println("Primera línea: " + linea);
                }
                // El reset vuelve al mark, si no se ha perdido el mark
                br.reset();
                System.out.println("Reset: " + br.readLine());
            }

        } catch (InvalidPathException | IOException e) {
            throw new RespaldoBasicoException(e.getMessage());
        }
    }

    /**
     * Este método utiliza varios métodos del BufferedWriter para escribir directamente
     * en el fichero
     *
     * @throws RespaldoBasicoException
     */
    public static void bufferedWriterFileWriter() throws RespaldoBasicoException {
        try {
            Path escribirle = Path.of("Boletin_Tema6/src/Ayuda/EjerciciosDeApoyo/Ejercicio2A/FicheroPruebasBufferedWriter.txt");
            try (BufferedWriter br = new BufferedWriter(new FileWriter(escribirle.toFile()))) {
                br.write("A-L-B-E-R-T-O"); // Escribimos en el fichero
                br.flush(); // Manda lo contenido en el buffer temporal directamente al destino final
                char[] c = {'A', 'n', 't', 'o', 'n', 'i', 'o'}; // Creamos un buffer con lo que va a escribir
                br.newLine(); // Salto de línea
                br.write(c); // Escribimos lo contenido en el buffer, método sobrecargado
            }

        } catch (InvalidPathException | IOException e) {
            throw new RespaldoBasicoException(e.getMessage());
        }
    }

    /**
     * Este método lee por teclado e imprime mientras no haya una línea vacía,
     * es decir que no haga un intro
     *
     * @throws RespaldoBasicoException
     */
    public static void bufferedReaderImputStreamReader() throws RespaldoBasicoException {
        /* El ImputStreamReader está leyendo lo pasado por teclado, además lee por teclado una serie de caracteres
         * gracias a que está envuelto en un BufferedReader, si esto no fuera así, leería char por char*/
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String linea;
            while (!(linea = br.readLine()).isEmpty()) {
                System.out.println(linea);
            }

        } catch (IOException e) {
            throw new RespaldoBasicoException(e.getMessage());
        }
    }
}