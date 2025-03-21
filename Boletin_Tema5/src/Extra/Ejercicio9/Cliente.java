package Extra.Ejercicio9;

import java.util.Objects;

public class Cliente {

    // Creamos los atributos
    private String nombre;
    private int identificador;
    private static int id_valor = 0;

    // Creamos el constructor
    public Cliente(String nombre) throws ClienteException {
        setNombre(nombre);
        this.identificador = ++id_valor;
    }

    // Creamos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) throws ClienteException {
        if (!Character.isUpperCase(nombre.charAt(0))) {
            throw new ClienteException("La primera letra del nombre debe estar en mayúsculas");
        }
        this.nombre = nombre;
    }

    public int getIdentificador() {
        return identificador;
    }

    private void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    // Creamos el equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return identificador == cliente.identificador;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(identificador);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Id: %d", this.nombre, this.identificador);
    }
}