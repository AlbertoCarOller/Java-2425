package Extra.Ejercicio19;

import java.util.Objects;

public class Pasajero {

    // Creamos los atributos
    private String nombre;
    private int numPasaporte;
    private static int valorNumPasaporte = 0;

    // Creamos el constructor
    public Pasajero(String nombre) throws PasajeroExcepcion {
        setNombre(nombre);
        this.numPasaporte = ++valorNumPasaporte;
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws PasajeroExcepcion {
        if (!Character.isUpperCase(nombre.charAt(0))) {
            throw new PasajeroExcepcion("La primera letra debe estar en mayúsculas");
        }
        for (int i = 1; i < nombre.length(); i++) {
            if (!Character.isLowerCase(nombre.charAt(i))) {
                throw new PasajeroExcepcion("El resto de caracteres deben estar en minúsculas y ser letras");
            }
        }
        this.nombre = nombre;
    }

    public int getNumPasaporte() {
        return numPasaporte;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pasajero pasajero = (Pasajero) o;
        return numPasaporte == pasajero.numPasaporte;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numPasaporte);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Número del pasaporte: %d", this.nombre, this.numPasaporte);
    }
}