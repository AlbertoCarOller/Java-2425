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

    // Hacemos un método que va a calcular la media de todas las mediciones
    public double calcularMedia() throws EstacionException {
        return mediciones.stream().mapToDouble(Medicion::getValorRegistrado).average()
                .orElseThrow(() -> new EstacionException("No se puede calcular"));
    }

    // Hacemos un método para calcular el valor máximo obtenido por una medición
    public double obtenerMax() throws EstacionException {
        return mediciones.stream().mapToDouble(Medicion::getValorRegistrado).max()
                .orElseThrow(() -> new EstacionException("No se puede calcular"));
    }

    // Hacemos un método para calcular el valor mínimo obtenido por una medición
    public double obtenerMin() throws EstacionException {
        return mediciones.stream().mapToDouble(Medicion::getValorRegistrado).min()
                .orElseThrow(() -> new EstacionException("No se puede calcular"));
    }
}