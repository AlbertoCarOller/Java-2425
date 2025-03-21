package Ejercicio6;

import java.util.Objects;

public abstract class Luchador {

    // Creamos los atributos
    private String nombre;
    private int velocidad;
    private int ataque;
    private int defensa;
    private int salud;

    // Creamos el construtor
    public Luchador(String nombre, int velocidad, int ataque, int defensa, int salud) throws LuchadorException {
        this.nombre = nombre;
        setVelocidad(velocidad);
        setAtaque(ataque);
        setDefensa(defensa);
        this.salud = salud;
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVelocidad() {
        return velocidad;
    }

    private void setVelocidad(int velocidad) throws LuchadorException {
        if (velocidad < 0 || velocidad > 100) {
            throw new LuchadorException("De 0 a 100");
        }
        this.velocidad = velocidad;
    }

    public int getAtaque() {
        return ataque;
    }

    protected void setAtaque(int ataque) throws LuchadorException {
        if (ataque < 0 || ataque > 100) {
            throw new LuchadorException("De 0 a 100");
        }
        this.ataque = ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    protected void setDefensa(int defensa) throws LuchadorException {
        if (defensa < 0 || defensa > 100) {
            throw new LuchadorException("De 0 a 100");
        }
        this.defensa = defensa;
    }

    public int getSalud() {
        return salud;
    }

    protected void setSalud(int salud) throws LuchadorException {
        if (salud < 0 || salud > 100) {
            throw new LuchadorException("De 0 a 100");
        }
        this.salud = salud;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Luchador luchador = (Luchador) o;
        return Objects.equals(nombre, luchador.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString

    @Override
    public String toString() {
        return "Luchador{" +
                "nombre='" + nombre + '\'' +
                ", velocidad=" + velocidad +
                ", ataque=" + ataque +
                ", defensa=" + defensa +
                ", salud=" + salud +
                '}';
    }

    // Hacemos el método para atacar
    protected abstract void atacar(Luchador victima) throws LuchadorException;

    // Hacemos un método para poder esquivar
    public boolean esquivar() {
        int probabilidadEsquivar = (int) (Math.random() * 100) + 1;
        if (probabilidadEsquivar <= (this.velocidad / 2)) {
            if ((this.velocidad / 2) == 0) {
                return false;
            }
            return true;
        }
        return false;
    }
}