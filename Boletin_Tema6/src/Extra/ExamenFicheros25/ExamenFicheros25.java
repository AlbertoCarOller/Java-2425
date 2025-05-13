package Extra.ExamenFicheros25;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class ExamenFicheros25 {
    public static void main(String[] args) {
        try {
            List<Path> ficherosValidosNombre = filtrarFicherosNombre();
            List<Path> ficherosValidos = filtrarFicherosNoConfidencial(ficherosValidosNombre);
            List<String> empleados = cogerEmpleados(ficherosValidos);
            crearEmpleadosUnificados(empleados);
            crearXML(obtenerEmpleadosPorUnificados());

        } catch (ExamenFicheros25Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //--Primera parte--


    /**
     * Este método va a devolver una lista con los
     * ficheros cuyos nombres son válidos
     *
     * @return una lista con los ficheros válidos
     * @throws ExamenFicheros25Exception si el Path es inválido o
     *                                   da algún error .walk
     */
    public static List<Path> filtrarFicherosNombre() throws ExamenFicheros25Exception {
        try {
            Path directorio = Path.of("Boletin_Tema6/src/Extra/ExamenFicheros25/datos_empleados");
            try (Stream<Path> ficherosFlujo = Files.walk(directorio)) {
                // Expresión regular para comprobar que el nombre del fichero sea correcto
                Pattern pattern = Pattern.compile("empleado_[0-9]{3}\\.(txt|data)");
                // Creamos una lista con los ficheros con nombres válidos
                return ficherosFlujo.filter(Files::isRegularFile).filter(p -> {
                    Matcher matcher = pattern.matcher(p.toFile().getName());
                    return matcher.matches();
                }).toList();
            }

        } catch (InvalidPathException | IOException e) {
            throw new ExamenFicheros25Exception(e.toString());
        }
    }

    /**
     * Este método va a filtrar los ficheros que no contienen
     * dentro la palabra confidencial como palabra independiente
     *
     * @param ficherosNombre los ficheros filtrados por nombre
     * @return una lista de ficheros ya completamente válidos
     */
    public static List<Path> filtrarFicherosNoConfidencial(List<Path> ficherosNombre) {
        // Hacemos la expresión regular para buscar confidencial
        Pattern pattern = Pattern.compile("(?i)\\bConfidencial\\b");
        // Devolvemos una lista con los ficheros que no tienen confidencial
        return ficherosNombre.stream().filter(p -> {
            try {
                Matcher matcher = pattern.matcher(Files.readString(p));
                return !matcher.find();

            } catch (IOException e) {
                return false;
            }
        }).toList();
    }

    /**
     * Este método va a guardar en una lista los empleados,
     * esto nos servirá posteriormente para crear los
     * empleados dentro del XML de forma más sencilla
     *
     * @param ficherosValidos una lista con los ficheros válidos
     * @return una lista con los usuarios
     */
    public static List<String> cogerEmpleados(List<Path> ficherosValidos) {
        List<String> empleados = new ArrayList<>();
        // Expresión regular de la información de un empleado
        Pattern pattern = Pattern.compile("((Nombre:\\s(?:\\p{L}+\\s?)+)\n(Departamento:\\s(?:\\p{L}+\\s?)+)" +
                "\n(Edad:\\s\\d{1,3}))");
        Pattern pattern1 = Pattern.compile(".*(\\d{3}).*");
        // Ordenamos los Path por el número que contienen
        ficherosValidos.stream().sorted(Comparator.comparingInt(p -> {
            Matcher matcher = pattern1.matcher(p.toFile().getName());
            int numero = 0;
            if (matcher.matches()) {
                numero = Integer.parseInt(matcher.group(1));
            }
            return numero;
            // Llamamos al método auxiliar por cada Path
        })).forEach(p -> auxiliarCogerEmpleados(p, empleados, pattern));
        return empleados;
    }

    /**
     * Este método es un auxiliar del anterior, coge la parte de la
     * información del empleado y lo guarda en una lista, separándolos
     *
     * @param p         el Path que se está recorriendo
     * @param empleados una lista de los empleados
     * @param pa        el Pattern
     */
    public static void auxiliarCogerEmpleados(Path p, List<String> empleados, Pattern pa) {
        try {
            Matcher matcher = pa.matcher(Files.readString(p));
            // Si se encuentra el empleado, se añade su información a la lista
            if (matcher.find()) {
                empleados.add(matcher.group(1));
            }

        } catch (IOException ignored) {
        }
    }

    /**
     * Este método va a crear un fichero donde se recojan todos
     * los usuarios de los ficheros
     *
     * @param empleados los empleados
     * @throws ExamenFicheros25Exception en caso de que el Path sea
     *                                   inválido o haya un problema con el PrintWriter
     */
    public static void crearEmpleadosUnificados(List<String> empleados) throws ExamenFicheros25Exception {
        try {
            Path ficheroUnificado = Path.of("Boletin_Tema6/src/Extra/ExamenFicheros25/empleados_unificado.txt");
            // El PrintWriter nos creará automáticamente el fichero
            try (PrintWriter pw = new PrintWriter(new FileWriter(ficheroUnificado.toFile()))) {
                // Escribimos en el fichero la información del empleado
                empleados.forEach(e -> pw.println(e.trim() + "\n"));

            } catch (IOException e) {
                throw new ExamenFicheros25Exception(e.toString());
            }

        } catch (InvalidPathException e) {
            throw new ExamenFicheros25Exception(e.toString());
        }
    }

    //--Segunda parte--

    /**
     * Este método va a obtener a partir del fichero unificado,
     * una lista de la información de los empleados
     *
     * @return una lista con la información de los empleados
     * @throws ExamenFicheros25Exception si el Path es inválido o
     *                                   si al leer el fichero hay un error
     */
    public static List<String> obtenerEmpleadosPorUnificados() throws ExamenFicheros25Exception {
        try {
            Path ficheroUnificado = Path.of("Boletin_Tema6/src/Extra/ExamenFicheros25/empleados_unificado.txt");
            Pattern pattern = Pattern.compile("((Nombre:\\s(?:\\p{L}+\\s?)+)\n(Departamento:\\s(?:\\p{L}+\\s?)+)" +
                    "\n(Edad:\\s\\d{1,3}))");
            Matcher matcher = pattern.matcher(Files.readString(ficheroUnificado));
            // El results me devuelve las coincidencias y las transformamos a la información
            return matcher.results().map(m -> m.group(1)).toList();

        } catch (InvalidPathException | IOException e) {
            throw new ExamenFicheros25Exception(e.toString());
        }
    }

    /**
     * Este método va a crear un nuevo Document en el cual
     * se le van a ir añadiendo los nodos correspondientes
     * de acuerdo con la información de los empleados
     *
     * @param empleados una lista con la información de cada empleado
     * @throws ExamenFicheros25Exception si el Path es inválido, si hay
     *                                   un problema al transformar el DOM a un fichero o
     *                                   si hay algún problema al eliminar el archivo XML
     *                                   en caso de que exista
     */
    public static void crearXML(List<String> empleados) throws ExamenFicheros25Exception {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Extra/ExamenFicheros25/empleados.xml");
            Files.deleteIfExists(archivoXML);
            /* Creamos un nuevo Document, donde vamos a crear la estructura para guardar la
             * información de los empleados */
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            // Creamos el nodo padre (root)
            Element empleadosE = doc.createElement("empleados");
            // Añadimos el nodo padre al Document
            doc.appendChild(empleadosE);
            // Creamos el regex de la información del empleado
            Pattern informacionEmpleado = Pattern.compile("Nombre:\\s(?<Nombre>(?:\\p{L}+\\s?)+)\n" +
                    "Departamento:\\s(?<Departamento>(?:\\p{L}+\\s?)+)\nEdad:\\s(?<Edad>\\d{1,3})");
            // recorremos la información de cada empleado creando los nodos correspondientes
            empleados.forEach(e -> auxiliarCrearElementosPorEmpleado(doc, empleadosE, e, informacionEmpleado));
            // Guardamos los cambios
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(archivoXML.toFile());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            // Le damos un formato al XML
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(domSource, streamResult);

        } catch (InvalidPathException | ParserConfigurationException | TransformerException | IOException e) {
            throw new ExamenFicheros25Exception(e.toString());
        }
    }

    /**
     * Esto es un método auxiliar que va a crear los nodos por cada
     * información que haya del empleado y va a ir añadiendo los nodos
     *
     * @param doc       el Document
     * @param empleados el nodo padre del Document
     * @param e         la información del empleado recorrida
     * @param pa        el Patter que va a extraer la información
     */
    public static void auxiliarCrearElementosPorEmpleado(Document doc, Element empleados, String e, Pattern pa) {
        Matcher matcher = pa.matcher(e);
        if (matcher.matches()) {
            // Creamos los nodos
            Element empleado = doc.createElement("empleado");
            Element nombre = doc.createElement("nombre");
            nombre.setTextContent(matcher.group("Nombre").trim());
            empleado.setAttribute("departamento", matcher.group("Departamento").trim());
            Element edad = doc.createElement("edad");
            edad.setTextContent(matcher.group("Edad"));
            // Añadimos los nodos al empleado
            empleado.appendChild(nombre);
            empleado.appendChild(edad);
            // Añadimos el nodo empleado al nodo padre (empleados)
            empleados.appendChild(empleado);
        }
    }
}