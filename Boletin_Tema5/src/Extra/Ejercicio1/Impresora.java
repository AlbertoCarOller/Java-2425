package Extra.Ejercicio1;

import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

public class Impresora {

    // Creamos los atributos
    private Queue<Documento> documentos;
    private String nombre;

    // Creamos el constructor
    public Impresora(String nombre) {
        this.documentos = new PriorityQueue<>();
        this.nombre = nombre;
    }

    // Hacemos los get y set
    public Queue<Documento> getDocumentos() {
        return documentos;
    }

    protected void setDocumentos(Deque<Documento> documentos) {
        this.documentos = documentos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s\nDocumentos en cola: %s", this.nombre, this.documentos);
    }

    // Hacemos un método para añadir un documento a la cola
    public void anadirDocumento(Documento documento) throws DocumentoException {
        if (documentos.contains(documento)) {
            throw new DocumentoException("El documento ya está añadido");
        }
        documentos.add(documento);
        System.out.println("El documento " + documento.getTitulo() + " se ha añadido a la impresora " + this.nombre);
    }

    // Hacemos un método para eliminar un documento de la cola
    public void eliminarDocumento(Documento documentoABorrar) throws DocumentoException {
        if (!documentos.contains(documentoABorrar)) {
            throw new DocumentoException("El documento no se encuentra");
        }
        documentos.remove(documentoABorrar);
    }

    // Hacemos un método para imprimir el documento
    public void imprimirDocumento() throws DocumentoException {
        if (documentos.isEmpty()) {
            throw new DocumentoException("No hay documentos en la cola");
        }
        System.out.println("Imprimiento... " + this.documentos.poll());
    }

    // Hacemos un método para mostrar los documentos que aún no se han impreso en la impresora
    public void mostrarDocumentos() {
        System.out.println(this);
    }
}