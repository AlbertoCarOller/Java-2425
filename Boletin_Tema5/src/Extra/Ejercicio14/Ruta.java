package Extra.Ejercicio14;

import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

public class Ruta {

    // Creamos los atributos
    private String nombre;
    private Queue<Paquete> paquetes;

    // Creamos el constructor
    public Ruta(String nombre) throws PaqueteException {
        setNombre(nombre);
        this.paquetes = new PriorityQueue<>();
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) throws PaqueteException {
        if (!Character.isUpperCase(nombre.charAt(0))) {
            throw new PaqueteException("La primera letra debe estar en mayúsculas");
        }
        for (int i = 1; i < nombre.length(); i++) {
            if (!(Character.isLowerCase(nombre.charAt(i)) || Character.isSpaceChar(nombre.charAt(i)))) {
                throw new PaqueteException("El resto de caracteres debe estar en minúsculas y ser letras");
            }
        }
        this.nombre = nombre;
    }

    public Queue<Paquete> getPaquetes() {
        return paquetes;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ruta ruta = (Ruta) o;
        return Objects.equals(nombre, ruta.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Paquetes: %s", this.nombre, this.paquetes);
    }

    // Hacemos un método para añadir un paquete a la ruta
    public String registrarPaquete(Paquete paquete) throws PaqueteException {
        if (paquetes.stream().anyMatch(paquete::equals)) {
            throw new PaqueteException("El paquete ya está registrado");
        }
        paquetes.add(paquete);
        return "El paquete " + paquete.getNumeroSeguimiento() + " ha sido registrado";
    }

    // Hacemos un método para entregar un paquete
    public String entregarPaquete() throws PaqueteException {
        Paquete paquete = paquetes.poll();
        if (paquete == null) {
            throw new PaqueteException("No hay paquetes registrados");
        }
        return "El paquete " + paquete.getNumeroSeguimiento() + " ha sido entregado";
    }
}