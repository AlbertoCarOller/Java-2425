package Extra.Ejercicio17;

import java.util.Objects;

public class Estudiante {

    // Creamos los atributos
    private String nombre;
    private int numMatricula;
    private static int valorMatricula = 0;

    // Creamos el constructor
    public Estudiante(String nombre) throws EstudianteException {
        setNombre(nombre);
        this.numMatricula = ++valorMatricula;
    }

    // Hacemos los get
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) throws EstudianteException {
        if (!Character.isUpperCase(nombre.charAt(0))) {
            throw new EstudianteException("Debe estar en mayúsculas la primera letra del nombre");
        }
        for (int i = 1; i < nombre.length(); i++) {
            if (!Character.isLowerCase(nombre.charAt(i))) {
                throw new EstudianteException("El resto de caracteres deben estar en minúscula y ser letras");
            }
        }
        this.nombre = nombre;
    }

    public int getNumMatricula() {
        return numMatricula;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estudiante that = (Estudiante) o;
        return numMatricula == that.numMatricula && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, numMatricula);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Número de la matrícula: %d", this.nombre, this.numMatricula);
    }
}