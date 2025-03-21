package Pesca;

import java.util.Arrays;
import java.util.Objects;

public class Pescador {

    // Creamos los atributos
    private String nombre;
    private int edad;
    private int experiencia;
    private static final int MAX_CAPTURAS = 5;
    private int[] capturasPeso;
    private boolean capitan;

    // Hacemos el constructor
    public Pescador(String nombre, int edad, int experiencia, boolean capitan) throws PescaException {
        this.nombre = nombre;
        setEdad(edad);
        setExperiencia(experiencia);
        this.capturasPeso = new int[MAX_CAPTURAS];
        this.capitan = capitan;
    }

    // Hacemos los set y los get
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) throws PescaException {

        if (edad < 16 || edad > 90) {
            throw new PescaException("No tienes una edad que entre dentro de los límites");
        }
        this.edad = edad;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public boolean isCapitan() {
        return capitan;
    }

    public void setCapitan(boolean capitan) {
        this.capitan = capitan;
    }

    public void setExperiencia(int experiencia) throws PescaException {

        if (experiencia < 1 || experiencia > 80) {
            throw new PescaException("Tu experiencia laboral no es válida");
        }
        this.experiencia = experiencia;
    }

    public int[] getCapturasPeso() {
        return capturasPeso;
    }

    public void setCapturasPeso(int[] capturasPeso) {
        this.capturasPeso = capturasPeso;
    }

    // Creamos un toString
    @Override
    public String toString() {
        return "Pescador{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", experiencia=" + experiencia +
                ", capturasPeso=" + Arrays.toString(capturasPeso) +
                ", capitan=" + capitan +
                '}';
    }

    // Hacemos un equals para saber si dos pescadores son iguales por el nombre
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pescador pescador = (Pescador) o;
        return Objects.equals(nombre, pescador.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un método para añadir una captura a un pescador
    public void anadirCaptura(int pesoCaptura) throws PescaException {

        if (pesoCaptura < 1 || pesoCaptura > 20) {
            throw new PescaException("El peso no puede ser menor que 1 o mayor a 20 kilos");
        }

        int hayEspacio = -1;

        for (int i = 0; i < capturasPeso.length; i++) {

            if (capturasPeso[i] == 0 && hayEspacio == -1) {
                hayEspacio = i;
            }
        }

        if (hayEspacio != -1) {
            capturasPeso[hayEspacio] = pesoCaptura;

        } else {
            throw new PescaException("No puedes hacer más capturas");
        }
    }
}