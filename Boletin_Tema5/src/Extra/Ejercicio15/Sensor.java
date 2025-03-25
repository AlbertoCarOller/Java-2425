package Extra.Ejercicio15;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sensor {
    // Creamos los atributos
    private int codigo;
    private static int valorCodigo;
    private TSensor tipo;
    private List<Medicion> mediciones;

    // Creamos el constructor
    public Sensor(TSensor tipo) {
        this.codigo = ++valorCodigo;
        this.tipo = tipo;
        this.mediciones = new ArrayList<>();
    }

    // Hacemos los get
    public int getCodigo() {
        return codigo;
    }

    public TSensor getTipo() {
        return tipo;
    }

    public List<Medicion> getMediciones() {
        return mediciones;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sensor sensor = (Sensor) o;
        return codigo == sensor.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Código: %d, Tipo: %s, Mediciones: %s", this.codigo, this.tipo, this.mediciones);
    }
}