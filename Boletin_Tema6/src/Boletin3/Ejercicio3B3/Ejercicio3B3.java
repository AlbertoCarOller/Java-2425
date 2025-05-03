package Boletin3.Ejercicio3B3;

import Boletin3.Ejercicio2B3.Ejercicio2B3Exception;
import Extra.Ejercicio27E.Ejercicio27EException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio3B3 {
    public static void main(String[] args) {
        try {
            System.out.println("Platos a menos de 5 euros:");
            mostrarPlatosBaratos();
            System.out.println("Platos con más de 500 calorías");
            mostrarPlatosAltoEnCalorias();
            anadirID();
            anadirPlatoXML("Antonio a la brasa", "90,5€",
                    "Antonio pasado por la brasa a 180 grados", "700");
            //eliminarPlatosAltosEnCalorias();

        } catch (Ejercicio3B3Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Este método va a crear un DocumentBuilder para así facilitar esta parte del código,
     * ya que siempre es la misma
     *
     * @return el DocumentBuilder
     * @throws Ejercicio2B3Exception
     */
    public static DocumentBuilder crearDocumentBuilder() throws Ejercicio3B3Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            return documentBuilderFactory.newDocumentBuilder();

        } catch (ParserConfigurationException e) {
            throw new Ejercicio3B3Exception(e.getMessage());
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
    public static void guardarCambios(Document doc, Path archivoFinal) throws Ejercicio3B3Exception {
        // Quitamos los nodos de texto, sigue sin identarse correctamente con este proceso, pero sí mejor
        // TODO: probar a hacerlo de forma manual
        /*NodeList recorrido = doc.getDocumentElement().getChildNodes();
        List<Node> nodosTextos = new ArrayList<>();
        for (int i = 0; i < recorrido.getLength(); i++) {
            if (recorrido.item(i).getNodeType() == Node.TEXT_NODE) {
                nodosTextos.add(recorrido.item(i));
            }
        }
        nodosTextos.forEach(n -> doc.getDocumentElement().removeChild(n));*/
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(archivoFinal.toFile());
            transformer.transform(domSource, streamResult);
            // Sirve para decir que el documento debe tener saltos de línea y espacios
            //transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            //Aplica 4 espacios por cada nivel de identación, HAY QUE TENER CUIDADO CON LOS SALTOS DE LÍNEA EXISTENTE
            //transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");


        } catch (TransformerException e) {
            throw new Ejercicio3B3Exception(e.getMessage());
        }
    }

    /**
     * Este método va a mostrar por pantalla el nombre los platos
     * que valen menos de 5 euros
     *
     * @throws Ejercicio3B3Exception
     */
    public static void mostrarPlatosBaratos() throws Ejercicio3B3Exception {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Boletin3/Ejercicio3B3/desayuno.xml");
            Document doc = crearDocumentBuilder().parse(archivoXML.toFile());
            NodeList comidas = doc.getDocumentElement().getChildNodes();
            for (int i = 0; i < comidas.getLength(); i++) {
                if (comidas.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element comida = (Element) comidas.item(i);
                    // .strip() funciona igual que el .trim()
                    if (comida.getElementsByTagName("price").item(0).getTextContent().strip()
                            // Con matches ^ y $ no es necesario, solo con find para saber donde está el principio y final
                            .matches("[0-4][.,]\\d{2}€")) {
                        System.out.println("-> " + comida.getElementsByTagName("name").item(0).getTextContent().strip());
                    }
                }
            }

        } catch (InvalidPathException | IOException | SAXException e) {
            throw new Ejercicio3B3Exception(e.getMessage());
        }
    }

    /**
     * Este método va a mostrar los platos altos en caloría, que son los que
     * tengan más de 500 calorías
     *
     * @throws Ejercicio3B3Exception
     */
    public static void mostrarPlatosAltoEnCalorias() throws Ejercicio3B3Exception {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Boletin3/Ejercicio3B3/desayuno.xml");
            Document doc = crearDocumentBuilder().parse(archivoXML.toFile());
            NodeList foodList = doc.getDocumentElement().getChildNodes();
            for (int i = 0; i < foodList.getLength(); i++) {
                // Esta comprobación es innecesaria, ya que .getDocumentElement() devuelve todos elementos
                if (foodList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element food = (Element) foodList.item(i);
                    if (Integer.parseInt(food.getElementsByTagName("calories").item(0).getTextContent())
                            > 500) {
                        System.out.println("-> " + food.getElementsByTagName("name").item(0).getTextContent());
                    }
                }
            }

        } catch (InvalidPathException | IOException | SAXException e) {
            throw new Ejercicio3B3Exception(e.getMessage());
        }
    }

    /**
     * Este método añade como atributo el id empezando por valor 1
     *
     * @throws Ejercicio3B3Exception
     */
    public static void anadirID() throws Ejercicio3B3Exception {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Boletin3/Ejercicio3B3/desayuno.xml");
            Document doc = crearDocumentBuilder().parse(archivoXML.toFile());
            NodeList comidas = doc.getDocumentElement().getChildNodes();
            int valorID = 0;
            for (int i = 0; i < comidas.getLength(); i++) {
                if (comidas.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element comida = (Element) comidas.item(i);
                    comida.setAttribute("id", String.valueOf(++valorID)); /* -> i + 1 no cambia el valor de i
                     no es viable, ya que puede haber nodos de texto entre medio*/
                }
            }
            guardarCambios(doc, archivoXML);

        } catch (InvalidPathException | IOException | SAXException e) {
            throw new Ejercicio3B3Exception(e.getMessage());
        }
    }

    /**
     * Este método va a añadir un plato nuevo, creando los nodos hijos de este
     * con los valores pasados por parámetros, verificando la validéz de estos
     *
     * @param name        el nombre de la comida
     * @param price       el precio de la comida
     * @param description la descripción de la comida
     * @param calories    las calorías de la comida
     * @throws Ejercicio3B3Exception
     */
    public static void anadirPlatoXML(String name, String price, String description, String calories)
            throws Ejercicio3B3Exception {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Boletin3/Ejercicio3B3/desayuno.xml");
            Document doc = crearDocumentBuilder().parse(archivoXML.toFile());
            // Creamos la cómida
            Element food = doc.createElement("food");
            // Añadimos los atributos de la comida
            if (!name.matches("^\\p{L}[\\p{L}\\s*]+$")) {
                throw new Ejercicio3B3Exception("El nombre no es válido");
            }
            Element nombre = doc.createElement("name");
            nombre.setTextContent(name);
            if (!price.matches("^(0[.,][0-9]+|[1-9][0-9]+[.,][0-9]+)€$")) {
                throw new Ejercicio3B3Exception("El precio no es válido");
            }
            Element precio = doc.createElement("price");
            precio.setTextContent(price);
            if (!description.matches("^[\\p{L}\\s\n\\d]+$")) {
                throw new Ejercicio3B3Exception("La descripción no es correcta");
            }
            Element descripcion = doc.createElement("description");
            descripcion.setTextContent("\n\t\t\t" + description + "\n\t\t");
            if (!calories.matches("^[1-9][0-9]{1,3}$")) {
                throw new Ejercicio3B3Exception("Las calorías no tienen un formato correcto");
            }
            Element calorias = doc.createElement("calories");
            calorias.setTextContent(calories);
            // Append sobre los atributos
            food.appendChild(doc.createTextNode("\n\t\t"));
            food.appendChild(nombre);
            food.appendChild(doc.createTextNode("\n\t\t"));
            food.appendChild(precio);
            food.appendChild(doc.createTextNode("\n\t\t"));
            food.appendChild(descripcion);
            food.appendChild(doc.createTextNode("\n\t\t"));
            food.appendChild(calorias);
            food.appendChild(doc.createTextNode("\n\t"));
            // Añadimos la comida al nodo principal
            /* IMPORTANTE: Antes me estaba dando problemas, ya que estaba insertando la tabulación y espacio dentro de
             * del nodo food directamente ne vez de fuera */
            doc.getDocumentElement().appendChild(doc.createTextNode("\t"));
            doc.getDocumentElement().appendChild(food);
            doc.getDocumentElement().appendChild(doc.createTextNode("\n"));
            //doc.insertBefore(doc.createTextNode("\n"), doc.getDocumentElement()); -> No se puede insertar directamente en doc
            // Guardamos los cambios
            guardarCambios(doc, archivoXML);

        } catch (InvalidPathException | IOException | SAXException e) {
            throw new Ejercicio3B3Exception(e.getMessage());
        }
    }

    /**
     * Este método elimina los platos con más de calorías, se consideran altos
     * en calorías los que tengan más de 500 calorías
     *
     * @throws Ejercicio3B3Exception
     */
    public static void eliminarPlatosAltosEnCalorias() throws Ejercicio3B3Exception {
        try {
            Path archivoXML = Path.of("Boletin_Tema6/src/Boletin3/Ejercicio3B3/desayuno.xml");
            Path archivoNuevoXML = Path.of("Boletin_Tema6/src/Boletin3/Ejercicio3B3/desayuno_saludable.xml");
            Document doc = crearDocumentBuilder().parse(archivoXML.toFile());
            NodeList comidas = doc.getDocumentElement().getChildNodes();
            List<Node> nodosBorrar = new ArrayList<>();
            List<Node> nodosTextBorrar = new ArrayList<>();
            for (int i = 0; i < comidas.getLength(); i++) {
                if (comidas.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element comida = (Element) comidas.item(i);
                    if (Integer.parseInt(comida.getElementsByTagName("calories").item(0).getTextContent()) > 500) {
                        nodosBorrar.add(comidas.item(i));
                    }
                }
                if (comidas.item(i).getNodeType() == Node.TEXT_NODE) {
                    nodosTextBorrar.add(comidas.item(i));
                }
            }
            // Borramos los nodos necesarios
            nodosBorrar.forEach(n -> doc.getDocumentElement().removeChild(n));
            // Borramos los nodos de tipo texto, ya que se quedan nodos sobrantes (saltos de línea)
            nodosTextBorrar.forEach(n -> doc.getDocumentElement().removeChild(n));
            // Guardamos los cambios
            guardarCambios(doc, archivoNuevoXML);

        } catch (InvalidPathException | IOException | SAXException e) {
            throw new Ejercicio3B3Exception(e.getMessage());
        }
    }
}