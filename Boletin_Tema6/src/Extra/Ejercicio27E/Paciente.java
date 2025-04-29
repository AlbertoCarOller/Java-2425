package Extra.Ejercicio27E;

import java.util.Objects;

public class Paciente {
    // Creamos los atributos
    private String id;
    private String nombreCompleto;
    private int edad;
    private String correoElectronico;
    private double peso;
    private double altura;

    // Creamos el constructor
    public Paciente(String id, String nombreCompleto, int edad, String correoElectronico, double peso, double altura) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.edad = edad;
        this.correoElectronico = correoElectronico;
        this.peso = peso;
        this.altura = altura;
    }

    // Creamos los get
    public String getId() {
        return id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public int getEdad() {
        return edad;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public double getPeso() {
        return peso;
    }

    public double getAltura() {
        return altura;
    }

    // Creamos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(id, paciente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    // Creamos un toString
    @Override
    public String toString() {
        return String.format("ID: %s, Nombre completo: %s, Edad: %d, Correo electrónico: %s, Peso: %.2f, Altura: %.2f",
                this.id, this.nombreCompleto, this.edad, this.correoElectronico, this.peso, this.altura);
    }
}