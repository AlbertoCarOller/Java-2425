package Taller;

import java.util.Arrays;
import java.util.Objects;

public class Vehiculo {

    // Creamos los atributos
    private String matricula;
    private String marca;
    private String estado;
    private static final int MAX_REPARACIONES = 5;
    private Reparacion[] reparaciones;

    // Creamos un constructor
    public Vehiculo(String matricula, String marca, String estado) {
        this.matricula = matricula;
        this.marca = marca;
        this.estado = estado;
        this.reparaciones = new Reparacion[MAX_REPARACIONES];
    }

    // Creamos los get y set
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Reparacion[] getReparaciones() {
        return reparaciones;
    }

    public void setReparaciones(Reparacion[] reparaciones) {
        this.reparaciones = reparaciones;
    }

    // Creamos un toString
    @Override
    public String toString() {
        return "Vehiculo{" +
                "matricula='" + matricula + '\'' +
                ", marca='" + marca + '\'' +
                ", estado='" + estado + '\'' +
                ", reparaciones=" + Arrays.toString(reparaciones) +
                '}';
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return Objects.equals(matricula, vehiculo.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(matricula);
    }
}