package Ejercicio11;

import java.util.Objects;

public abstract class Empleado {

    // Creamos los atributos
    private String nombre;
    private double salario;

    // Creamos el constructor
    public Empleado(String nombre, double salario) throws EmpleadoException {
        this.nombre = nombre;
        setSalario(salario);
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSalario() {
        return salario;
    }

    protected void setSalario(double salario) throws EmpleadoException {
        if (salario < 1000 || salario > 5000) {
            throw new EmpleadoException("El salario no puede ser menor a 1000 ni mayor a 5000");
        }
        this.salario = salario;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return Objects.equals(nombre, empleado.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Salario: %f", this.nombre, this.salario);
    }
}