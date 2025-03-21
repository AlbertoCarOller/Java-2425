package Taller;

import java.util.Arrays;

public class Taller {

    // Creamos los atributos
    private String nombre;
    private static final int MAX_VEHICULOS = 5;
    private Vehiculo[] vehiculos;

    // Creamos el constructor
    public Taller(String nombre) {
        this.nombre = nombre;
        this.vehiculos = new Vehiculo[MAX_VEHICULOS];
    }

    // Creamos los get y set
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Vehiculo[] getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(Vehiculo[] vehiculos) {
        this.vehiculos = vehiculos;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return "Información del taller:\n" +
                "nombre='" + nombre + '\'' +
                ", vehiculos=" + Arrays.toString(vehiculos);
    }

    // Hacemos un método para introducir un coche al taller
    public void darAltaVehiculo(Vehiculo vehiculo) throws TallerExcepcion {

        boolean esIgual = false;
        int hayEspacio = -1;

        for (int i = 0; i < vehiculos.length && !esIgual; i++) {

            if (vehiculo.equals(vehiculos[i])) {
                esIgual = true;
            }

            if (vehiculos[i] == null && hayEspacio == -1) {
                hayEspacio = i;
            }
        }

        if (!esIgual && hayEspacio != -1) {
            vehiculos[hayEspacio] = vehiculo;

        } else {
            throw new TallerExcepcion("No se ha podido introducir el coche");
        }
    }
}