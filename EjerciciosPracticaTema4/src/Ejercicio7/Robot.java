package Ejercicio7;

import java.util.Objects;

public abstract class Robot implements Acelerable, Frenable {

    // Creamos los atributos
    private String nombre;
    private int velocidadDesplazamiento;
    private int capacidadAdaptacion;
    private int nivelEnergia;
    private int distanciaRecorrida;
    protected static final int FIN = 350;

    // Creamos el constructor
    public Robot(String nombre, int capacidadAdaptacion, int nivelEnergia) throws RobotException {
        this.nombre = nombre;
        this.velocidadDesplazamiento = 0;
        setCapacidadAdaptacion(capacidadAdaptacion);
        setNivelEnergia(nivelEnergia);
        this.distanciaRecorrida = 0;
    }

    // Creamos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVelocidadDesplazamiento() {
        return velocidadDesplazamiento;
    }

    public void setVelocidadDesplazamiento(int velocidadDesplazamiento) throws RobotException {
        this.velocidadDesplazamiento = velocidadDesplazamiento;
    }

    public int getCapacidadAdaptacion() {
        return capacidadAdaptacion;
    }

    public void setCapacidadAdaptacion(int capacidadAdaptacion) throws RobotException {
        if (capacidadAdaptacion < 1 || capacidadAdaptacion > 50) {
            throw new RobotException("La capacidad de adaptación debe estar entre 1 y 50");
        }
        this.capacidadAdaptacion = capacidadAdaptacion;
    }

    public int getNivelEnergia() {
        return nivelEnergia;
    }

    public void setNivelEnergia(int nivelEnergia) throws RobotException {
        if (nivelEnergia < 1 || nivelEnergia > 50) {
            throw new RobotException("El nivel de energía debe estar entre 1 y 50");
        }
        this.nivelEnergia = nivelEnergia;
    }

    public int getDistanciaRecorrida() {
        return distanciaRecorrida;
    }

    public void setDistanciaRecorrida(int distanciaRecorrida) {
        this.distanciaRecorrida = distanciaRecorrida;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Robot robot = (Robot) o;
        return Objects.equals(nombre, robot.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return "Robot{" +
                "nombre='" + nombre + '\'' +
                ", velocidadDesplazamiento=" + velocidadDesplazamiento +
                ", capacidadAdaptacion=" + capacidadAdaptacion +
                ", nivelEnergia=" + nivelEnergia +
                ", distanciaRecorrida=" + distanciaRecorrida +
                '}';
    }

    // Hacemos un método para superar un obstáculo
    public abstract boolean superarObstaculo(Obstaculo obstaculo, int FINAL) throws RobotException;
}