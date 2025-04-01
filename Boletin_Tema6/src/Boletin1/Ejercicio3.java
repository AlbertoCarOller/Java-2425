package Boletin1;

// Importa todos los paquetes de java.io, no es eficiente, deben importarse los necesarios
import java.io.*;

public class Ejercicio3 {
    public static void main(String[] args) {

        /* Hacemos un try-with-resorces y dentro de los paréntesis introducimos los objetos a cerrar (se cierran automáticamente)
         * BufferedReader: Lee lo pasado por teclado y lo almacena en un buffer para poder leer más cantidad de caracteres juntos.
         * InputStreamReader: Se utiliza para leer parámetros pasados por teclado y no directamente de un archivo, por esa razón
         * colocamos el System.in, porque recibirá lo pasado por teclado al igual que pasaba en el Scanner.
         * PrintWriter: Imprime en un archivo lo pasado en este caso por teclado tiene un buffer fijo, su primo BufferedWriter
         * tiene un buffer también, pero este en el constructor se le puede especificar el tamaño en bytes del buffer y
         * FileWriter escribe caracter por caracter mucho más ineficiente en caso de tener un gran volumen de caracteres*/
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter pw = new PrintWriter(new FileWriter("Boletin_Tema6/salidaEjercicio3.txt"))) {
            String l;
            /* .readLine() lee en este caso una linea pasada por teclado, lo guardamos en el String para poder guardarla e
             * imprimirla más tarde*/
            while ((l = br.readLine()) != null && !l.equalsIgnoreCase("fin")) {
                /* .println(l) imprime dentro del archivo especificado l, es decir la línea guardada ahí, la pasada mediante
                 * el teclado*/
                pw.println(l);
            }
            /* .flush() vacía el buffer una vez salido del while, esto es necesario ya que si lo pasado por teclado
             * no rebosa el tamaño del buffer no lo imprime, es decir al terminar la ejecución se pierde el contenido
             * dentro del buffer, ya que este es temporal*/
            pw.flush();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}