package Extra.Ejercicio19;

import java.util.*;

public class Vuelo {

    // Creamos los atributos
    private int codigo;
    private static int codigoValor = 0;
    private Queue<Pasajero> listaEsperaPasajeros;
    private static final int PASAJEROS_MAX = 20;

    // Creamos el constructor
    public Vuelo() {
        this.codigo = ++codigoValor;
        this.listaEsperaPasajeros = new ArrayDeque<>(PASAJEROS_MAX);
    }

    // Hacemos los get
    public int getCodigo() {
        return codigo;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vuelo vuelo = (Vuelo) o;
        return codigo == vuelo.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Código: %d, Lista de espera: %s", this.codigo, this.listaEsperaPasajeros);
    }

    // Hacemos un método para añadir un pasejero
    public void registrarPasajero(Pasajero pasajero) throws PasajeroExcepcion {
        if (listaEsperaPasajeros.contains(pasajero)) {
            throw new PasajeroExcepcion("El pasajero ya está en la cola");
        }
        if (listaEsperaPasajeros.size() == PASAJEROS_MAX) {
            throw new PasajeroExcepcion("La cola está llena");
        }
        listaEsperaPasajeros.add(pasajero);
    }

    // Hacemos un método para asignar un asiento a un pasajero en espera
    public void asignarAsiento() throws PasajeroExcepcion {
        if (listaEsperaPasajeros.poll() == null) {
            throw new PasajeroExcepcion("No se encuentra el pasajero");
        }
    }

    // Hacemos un método que nos va a devolver la información de un pasajero
    public String informacionPasajero(Pasajero pasajero) throws PasajeroExcepcion {
        return listaEsperaPasajeros.stream().filter(pasajero::equals)
                .map(Pasajero::toString).findFirst().orElse(null);
    }

    // Hacemos un método que va a devolver una lista de espera de un vuelo ordenado alfabéticamente
    public List<Pasajero> listaEsperaVueloOrdenado() throws PasajeroExcepcion {
        return Optional.of(listaEsperaPasajeros.stream().sorted(Comparator.comparing(Pasajero::getNombre)).toList())
                .orElseThrow(() -> new PasajeroExcepcion("No hay pasajeros"));
    }

    // Hacemos un método para fusionar la lista de espera de dos vuelos manteniendo el orden de llegada
    public List<Pasajero> fusionarVuelos(Vuelo vuelo) throws PasajeroExcepcion {
        List<Pasajero> pasajerosFusionados = new ArrayList<>(this.listaEsperaPasajeros);
        pasajerosFusionados.addAll(vuelo.listaEsperaPasajeros);
        return Optional.of(pasajerosFusionados.stream().distinct().toList())
                .orElseThrow(() -> new PasajeroExcepcion("No había pasajeros en ningún vuelo"));
    }

    // Hacemos un método para devolver una lista de pasajeros que están en dos vuelos distintos
    public List<Pasajero> pasajerosEnDosVuelos(Vuelo vuelo) throws PasajeroExcepcion {
        return Optional.of(this.listaEsperaPasajeros.stream().filter(p ->
                        vuelo.listaEsperaPasajeros.stream().anyMatch(p::equals)).toList())
                .orElseThrow(() -> new PasajeroExcepcion("No hay pasajeros en ningunas de la listas de espera"));
    }

    // Hacemos un método que va a devolver la lista de espera ordenada alfabéticamente
    public List<Pasajero> pasajerosOrdenadosAlfabeticamente() throws PasajeroExcepcion {
        return Optional.of(listaEsperaPasajeros.stream().sorted(Comparator.comparing(Pasajero::getNombre))
                .toList()).orElseThrow(() -> new PasajeroExcepcion("No hay pasajeros en la lista de espera"));
    }
}