package Boletin3.Ejercicio1B3;

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
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PersonaApp {
    public static void main(String[] args) {
        try {
            List<Persona> personas = List.of(new Persona("Atisbedo", "98716396P", "698173450"),
                    new Persona("Respicio", "89876525T", "789163860"),
                    new Persona("Chelu", "89876525O", "676512387"),
                    new Persona("Antonio", "29867189W", "698762788"),
                    new Persona("Bermudo", "98615379L", "786153996"));
            exportarColeccion(personas);

        } catch (PersonaException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método crea un documento, se le van colocando los elementos y sus atributos
     * e hijos y después una vez realizado esto, se guarda y transforma la estructura
     * de nodos a una estructura xml, para así guardarla en el archivo
     *
     * @param personas
     * @throws PersonaException
     */
    public static void exportarColeccion(List<Persona> personas) throws PersonaException {
        try {
            // Creamos el Path hacia el archivo xml
            Path archivoXML = Path.of("Boletin_Tema6/src/Boletin3/personas.xml");
            // Creamos un objeto Document para poder trabajar con él y manipularlo
            DocumentBuilderFactory dBFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dB = dBFactory.newDocumentBuilder();
            Document doc = dB.newDocument(); // Creamos directamente un nuevo documento
            // Creamos el nodo raíz
            Element root = doc.createElement("personas");
            // Colocamos el nodo raíz en el documento
            doc.appendChild(root);
            // Recorremos las personas
            personas.forEach(p -> {
                // Creamos por cada persona, un elemento persona
                Element persona = doc.createElement("persona");
                // Le añadimos un atributo a cada persona, que se va a llamar dni y su valor es el dni de la persona
                persona.setAttribute("dni", p.getDni());
                // Creamos los elementos hijos para cada persona
                Element nombre = doc.createElement("nombre");
                /* Le damos el valor del nombre de la persona, cuidado con el setTextContent(), porque puede eliminar
                 los nodos hijos */
                nombre.setTextContent(p.getNombre());
                Element telefono = doc.createElement("telefono");
                telefono.setTextContent(p.getTelefono());
                Element fecha = doc.createElement("fecha");
                fecha.setTextContent(p.getFechaNacimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                // Le asignamos al nodo padre "persona" los nodos hijos con .appendChild()
                persona.appendChild(nombre);
                persona.appendChild(telefono);
                persona.appendChild(fecha);
                // Asignamos al documento el nodo persona, al nodo padre, que es personas
                doc.getDocumentElement().appendChild(persona);
            });
            /* TransformerFactory es una clase que puede crear un Transformer, esta trasforma
             * el árbol DOM, el Document,en algo que se pueda mostrar y guardar */
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            /* Transformer es la clase que nos va a permitir trasformar el árbol de nodos
             * para poder guardar su información y mostrarla */
            Transformer transformer = transformerFactory.newTransformer();
            /* DOMSource es la clase que dice al Transformer el contenido que tiene que
             * trasformar, se lo especificamos por parámetros, en este caso 'doc' que es
             * el objeto Document (el árbol de nodos) */
            DOMSource domSource = new DOMSource(doc);
            /* StreamResult es la clase que va a guardar el contenido en el archivo
             * especificádo, pasado por parámetros, si el archivo no existe, se
             * creará automáticamente */
            StreamResult streamResult = new StreamResult(archivoXML.toFile());
            /* con .transform() se guarda realmente el domSource que es el Documento
             * que se va a trasformar ya preparado para ello y el streamResult es
             * el destino ya preparado donde se va a guardar el Document ya transformado */
            transformer.transform(domSource, streamResult);

        } catch (ParserConfigurationException | RuntimeException | TransformerException e) {
            throw new PersonaException(e.getMessage());
        }
    }
}