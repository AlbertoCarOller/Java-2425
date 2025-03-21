package Ejercicio12;

import java.util.Objects;

public abstract class Modulo {

    // Creamos los atributos
    private String nombre;
    private int salud;
    private int envejecimiento;
    protected static final int SALUD_INICIAL = 100;
    protected static final int SALUD_MAXIMA = 200;
    protected static final int ENVEJECIMIENTO_MAXIMO = 100;

    // Creamos el constructor
    public Modulo(String nombre) {
        this.nombre = nombre;
        this.salud = SALUD_INICIAL;
        this.envejecimiento = 0;
    }

    // Creamos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSalud() {
        return salud;
    }

    protected void setSalud(int salud) throws ModuloException {
        if (salud < 0 || salud > SALUD_MAXIMA) {
            throw new ModuloException("La salud del módulo no puede ser menor a 0 ni mayor 200");
        }
        this.salud = salud;
    }

    public int getEnvejecimiento() {
        return envejecimiento;
    }

    protected void setEnvejecimiento(int envejecimiento) throws ModuloException {
        if (envejecimiento < 0 || envejecimiento > ENVEJECIMIENTO_MAXIMO) {
            throw new ModuloException("El envejecimiento no puede ser menor a 0 ni mayor a 100");
        }
        this.envejecimiento = envejecimiento;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Modulo modulo = (Modulo) o;
        return Objects.equals(nombre, modulo.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Salud: %d, Envejecimiento: %d", this.nombre, this.salud, this.envejecimiento);
    }
}