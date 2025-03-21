package Boletin1.Personaje;

import java.util.Objects;

public abstract class Personaje implements Comparable <Personaje>{

    // Creamos los atributos
    private String nombre;
    private Raza raza;
    private int fuerza;
    private int inteligencia;
    private int ptsMax;
    private int ptsActual;

    // Creamos el constructor
    public Personaje(String nombre, Raza raza, int fuerza, int inteligencia, int ptsMax, int ptsActual) throws PersonaException {
        this.nombre = nombre;
        this.raza = raza;
        setFuerza(fuerza);
        setInteligencia(inteligencia);
        setPtsMax(ptsMax);
        setPtsActual(ptsActual);
    }

    // Hacemos los set con las condiciones correspondientes en cada una
    protected void setFuerza(int fuerza) throws PersonaException {
        if (fuerza < 0 || fuerza > 20) {
            throw new PersonaException("La cantidad de puntos de fuerza no es válido");
        }
        this.fuerza = fuerza;
    }

    protected void setInteligencia(int inteligencia) throws PersonaException {
        if (inteligencia < 0 || inteligencia > 20) {
            throw new PersonaException("La cantidad de puntos de inteligencia no es válido");
        }
        this.inteligencia = inteligencia;
    }

    protected void setPtsActual(int ptsActual) throws PersonaException {
        if (ptsActual < 0 || ptsActual > this.ptsMax) {
            throw new PersonaException("La cantidad de puntos de vida actuales no es válido");
        }
        this.ptsActual = ptsActual;
    }

    protected void setPtsMax(int ptsMax) throws PersonaException {
        if (ptsMax < 0 || ptsMax > 100) {
            throw new PersonaException("La cantidad de puntos de vida máximo no es válido");
        }
        this.ptsMax = ptsMax;
    }

    // Hacemos los get
    public String getNombre() {
        return nombre;
    }

    public Raza getRaza() {
        return raza;
    }

    public int getFuerza() {
        return fuerza;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public int getPtsMax() {
        return ptsMax;
    }

    public int getPtsActual() {
        return ptsActual;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return "Personaje{" +
                "nombre='" + nombre + '\'' +
                ", raza=" + raza +
                ", fuerza=" + fuerza +
                ", inteligencia=" + inteligencia +
                ", ptsMax=" + ptsMax +
                ", ptsActual=" + ptsActual +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personaje personaje = (Personaje) o;
        return Objects.equals(nombre, personaje.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Creamos el método para comparar los puntos de vida de los personajes
    @Override
    public int compareTo(Personaje o) {
        return this.ptsActual - o.ptsActual;
    }
}