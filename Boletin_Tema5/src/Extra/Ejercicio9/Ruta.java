package Extra.Ejercicio9;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Ruta {

    // Creamos los atributos
    private String nombre;
    private String destino;
    private List<String> paradas;

    // Creamos el constructor
    public Ruta(String nombre, String destino) throws ClienteException {
        setNombre(nombre);
        this.destino = destino;
        this.paradas = new ArrayList<>();
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) throws ClienteException {
        if (!Character.isUpperCase(nombre.charAt(0))) {
            throw new ClienteException("La primera letra del nombre debe estar en mayúsculas");
        }
        this.nombre = nombre;
    }

    public String getDestino() {
        return destino;
    }

    private void setDestino(String destino) {
        this.destino = destino;
    }

    public List<String> getParadas() {
        return paradas;
    }

    private void setParadas(List<String> paradas) {
        this.paradas = paradas;
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
        return String.format("Nombre: %s, Destino: %s, Paradas: %s", this.nombre,
                this.destino, this.paradas.stream().sorted().collect(Collectors.joining(", ")));
    }

    // Hacemos un método para registrar paradas a la ruta
    public void registrarParada(String parada) throws ClienteException {
        if (paradas.stream().anyMatch(parada.trim()::equalsIgnoreCase)) {
            throw new ClienteException("La parada ya existe");
        }
        paradas.add(parada.trim());
    }

    // Hacemos un método para eliminar una parada
    public void eliminarParada(String parada) throws ClienteException {
        if (paradas.stream().noneMatch(parada.trim()::equalsIgnoreCase)) {
            throw new ClienteException("No está la parada");
        }
        paradas.remove(parada.trim());
    }
}