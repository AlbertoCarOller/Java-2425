package Extra.Ejercicio27E;


import Extra.Ejercicio23E.Ejercicio23Exception;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;

public class Ejercicio27E {
    public static void main(String[] args) {
        // Creamos una lista de pacientes
        List<Paciente> pacientes = List.of(new Paciente("P303", "Antonio Rodríguez", 37,
                "anton.io@gmail.com", 79.5, 1.72), new Paciente("P999",
                "Alberto Carmona", 20, "alberto@gmail.com", 80,
                1.77), new Paciente("X808", "Alberto Rivero", 25,
                "river_p@hotmail.com", 78.8, 1.69));
        try {
            crearXML(validarPacientes(pacientes));

        } catch (Ejercicio27EException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a crear un DocumentBuilder para así facilitar esta parte del código
     * ya que siempre es la misma
     *
     * @return el DocumentBuilder
     * @throws Ejercicio23Exception
     */
    public static DocumentBuilder crearDocumentBuilder() throws Ejercicio27EException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            return documentBuilderFactory.newDocumentBuilder();

        } catch (ParserConfigurationException e) {
            throw new Ejercicio27EException(e.getMessage());
        }
    }

    /**
     * Este método guarda los cambios realizados o la creación del nuevo Document
     * en el archivo especificado, este método es simplemente para optimizar, ya que
     * el proceso siempre es el mismo
     *
     * @param doc          el Document
     * @param archivoFinal el archivo destino (final)
     * @throws Ejercicio27EException
     */
    public static void guardarCambios(Document doc, Path archivoFinal) throws Ejercicio27EException {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(archivoFinal.toFile());
            transformer.transform(domSource, streamResult);

        } catch (TransformerException e) {
            throw new Ejercicio27EException(e.getMessage());
        }
    }

    /**
     * Este método validará el formato de los atributos de cada paciente,
     * los pacientes que tengan uno o más campos inválidos se escribirán
     * dentro de un documento .txt con su respectivo/s motivo/S
     *
     * @param pacientes la lista de los pacientes a evaluar
     * @return lista de los pacientes válidos
     * @throws Ejercicio27EException
     */
    public static List<Paciente> validarPacientes(List<Paciente> pacientes) throws Ejercicio27EException {
        try {
            Path escribirInvalidos = Path.of("Boletin_Tema6/src/Extra/Ejercicio27E/PacientesIncorrectos.txt");
            Files.deleteIfExists(escribirInvalidos);
            /* Recorremos los pacientes en busca de los correctos e incorrectos, se devolverá una lista
             * de los correctos y los no correctos  */
            return Optional.of(pacientes.stream().filter(p -> {
                StringBuilder sb = new StringBuilder();
                sb.append(p.getId()).append(" | ").append(p.getNombreCompleto()).append(" | ").append(p.getEdad())
                        .append(" | ").append(p.getCorreoElectronico()).append(" | ").append(p.getPeso())
                        .append(" | ").append(p.getAltura()).append("\n");
                // Llamamos al método que se va a encargar de filtrar y adjuntar los mensajes
                return appendMensaje(escribirInvalidos, sb, p);
            }).toList()).filter(l -> !l.isEmpty()).orElseThrow(() -> new Ejercicio27EException("No hay pacientes válidos"));

        } catch (IOException | RuntimeException e) {
            throw new Ejercicio27EException(e.getMessage());
        }
    }

    /**
     * Este método es un método auxiliar que escribirá el mensaje o los
     * mensajes correspondientes y enviar el texto a un fichero .txt,
     * en caso de que sea válido devolverá true, por lo que pasará el
     * filtro
     *
     * @param escribirInvalidos el path donde se van a escribir los inválidos
     * @param sb                el StringBuilder con los respectivos mensajes de error
     * @param p                 el paciente que se está recorriendo
     * @return
     */
    public static boolean appendMensaje(Path escribirInvalidos, StringBuilder sb, Paciente p) {
        // Esto nos indicará si no hay ningún error o sí hay
        boolean ninguno = true;
        // Dependiendo de donde no coincida se añadirá los mensajes correspondientes
        if (!p.getId().matches("^P\\d{3}$")) {
            ninguno = false;
            sb.append("-> El id no es correcto\n");
        }
        if (!p.getNombreCompleto().matches("^\\p{Lu}\\p{Ll}+ \\p{Lu}\\p{Ll}+$")) {
            ninguno = false;
            sb.append("-> El nombre no es correcto\n");
        }
        if (!String.valueOf(p.getEdad()).matches("^(\\d|[123456789][0-9]|1[12][0-9]|130)$")) {
            ninguno = false;
            sb.append("-> La edad no es correcta\n");
        }
        if (!p.getCorreoElectronico().matches("^[a-z][a-z._-]+@(gmail\\.com|hotmail\\.(es|com))$")) {
            ninguno = false;
            sb.append("-> El correo no es válido\n");
        }
        if (!String.valueOf(p.getPeso()).matches("^([1-9]|[123456789][0-9]|1\\d{2})\\.[0-9]{1,2}$")) {
            ninguno = false;
            sb.append("-> El peso no es correcto\n");
        }
        if (!String.valueOf(p.getAltura()).matches("^(1\\.([0-9]|[0-9][1-9])|2\\.([0-5]|[0-9][1-9]))$")) {
            ninguno = false;
            sb.append("-> La altura no es correcta\n");
        }
        // Si hay algún error como mínimo se escribirá el contenido de los objetos y sus respectivos fallos
        if (!ninguno) {
            try {
                if (!Files.exists(escribirInvalidos)) {
                    Files.createFile(escribirInvalidos);
                }
                Files.writeString(escribirInvalidos, sb + "\n\n", StandardOpenOption.APPEND);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return ninguno;
    }

    public static void crearXML(List<Paciente> pacientesValidos) throws Ejercicio27EException {
        try {
            // Path del archivo XML (donde quiero que se cree)
            Path archivoXML = Path.of("Boletin_Tema6/src/Extra/Ejercicio27E/pacientes.xml");
            Document doc = crearDocumentBuilder().newDocument();
            // Creamos el nodo raíz, IMPORTANTE
            Element root = doc.createElement("pacientes");
            doc.appendChild(root);
            // Recorremos los pacientes válidos
            pacientesValidos.forEach(p -> {
                // Nos creamos los nodos correspondientes de cada paciente
                Element paciente = doc.createElement("paciente");
                paciente.setAttribute("id", p.getId());
                Element nombreCompleto = doc.createElement("nombreCompleto");
                nombreCompleto.setTextContent(p.getNombreCompleto());
                Element edad = doc.createElement("edad");
                edad.setTextContent(String.valueOf(p.getEdad()));
                Element correoElectronico = doc.createElement("correoElectronico");
                correoElectronico.setTextContent(p.getCorreoElectronico());
                Element peso = doc.createElement("peso");
                peso.setTextContent(String.valueOf(p.getPeso()));
                Element altura = doc.createElement("altura");
                altura.setTextContent(String.valueOf(p.getAltura()));
                // Añadimos los nodos al nodo padre
                paciente.appendChild(nombreCompleto);
                paciente.appendChild(edad);
                paciente.appendChild(correoElectronico);
                paciente.appendChild(peso);
                paciente.appendChild(altura);
                // Añadimos el nodo del paciente como hijo del nodo padre
                root.appendChild(paciente);
            });
            // Guardamos los cambios
            guardarCambios(doc, archivoXML);

        } catch (InvalidPathException e) {
            throw new Ejercicio27EException(e.getMessage());
        }
    }
}