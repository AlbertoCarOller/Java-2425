package Extra.Ejercicio19;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Aeropuerto {

    // Creamos los atributos
    private String nombre;
    private Set<Vuelo> vuelos;

    // Creamos los constructores
    public Aeropuerto(String nombre) {
        this.nombre = nombre;
        this.vuelos = new HashSet<>();
    }

    // Hacemos el get del nombre
    public String getNombre() {
        return nombre;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aeropuerto that = (Aeropuerto) o;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Vuelos: %s", this.nombre, this.vuelos);
    }

    // Hacemos un método para registrar un vuelo
    public void registrarVuelo(Vuelo vuelo) throws PasajeroExcepcion {
        if (!vuelos.add(vuelo)) {
            throw new PasajeroExcepcion("El vuelo ya está registrado en el aeropuerto");
        }
    }

    // Hacemos un método para fusionar dos vuelos
    public List<Pasajero> fusionarVuelos(Vuelo vuelo1, Vuelo vuelo2) throws PasajeroExcepcion {
        if (!vuelos.contains(vuelo2)) {
            throw new PasajeroExcepcion("No se encuentra un vuelo");
        }
        return vuelos.stream().filter(vuelo1::equals).findFirst()
                .orElseThrow(() -> new PasajeroExcepcion("No se encuentra un vuelo"))
                .fusionarVuelos(vuelo2);
    }

    // Hacemos un método para encontrar pasajeros de dos vuelos distintos
    public List<Pasajero> encontrarPasajerosEnVuelos(Vuelo vuelo1, Vuelo vuelo2) throws PasajeroExcepcion {
        if (!vuelos.contains(vuelo2)) {
            throw new PasajeroExcepcion("No se encuentra un vuelo");
        }
        return vuelos.stream().filter(vuelo1::equals).findFirst()
                .orElseThrow(() -> new PasajeroExcepcion("No se encuentra un vuelo"))
                .pasajerosEnDosVuelos(vuelo2);
    }
}