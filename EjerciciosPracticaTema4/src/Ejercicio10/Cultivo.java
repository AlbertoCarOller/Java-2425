package Ejercicio10;

import java.util.Objects;

public abstract class Cultivo {

    // Creamos los atributos
    private int tiempoCrecimiento;
    private int litrosAguaNecesaria;
    private int cantidadProducida;
    private int resistenciaPlagas;
    private String nombre;

    // Creamos el constructor
    public Cultivo(int tiempoCrecimiento, int litrosAguaNecesaria, int resistenciaPlagas, String nombre) throws CultivoException {
        setTiempoCrecimiento(tiempoCrecimiento);
        setLitrosAguaNecesaria(litrosAguaNecesaria);
        this.cantidadProducida = 0;
        setResistenciaPlagas(resistenciaPlagas);
        this.nombre = nombre;
    }

    // Creamos los get y set
    public int getTiempoCrecimiento() {
        return tiempoCrecimiento;
    }

    protected void setTiempoCrecimiento(int tiempoCrecimiento) throws CultivoException {
        if (tiempoCrecimiento < 0 || tiempoCrecimiento > 168) {
            throw new CultivoException("Un cultivo no puede crecer en menos de 0 horas ni en más de una semana");
        }
        this.tiempoCrecimiento = tiempoCrecimiento;
    }

    public int getLitrosAguaNecesaria() {
        return litrosAguaNecesaria;
    }

    protected void setLitrosAguaNecesaria(int litrosAguaNecesaria) throws CultivoException {
        if (litrosAguaNecesaria < 0 || litrosAguaNecesaria > 250) {
            throw new CultivoException("Un cultivo no puede necesitar menos de 10 litros de agua ni más de 250");
        }
        this.litrosAguaNecesaria = litrosAguaNecesaria;
    }

    public int getCantidadProducida() {
        return cantidadProducida;
    }

    protected void setCantidadProducida(int cantidadProducida) throws CultivoException {
        if (cantidadProducida < 0 || cantidadProducida > 100) {
            throw new CultivoException("La cantidad de cultivos producidos no puede ser menor que 5 ni mayor a 100");
        }
        this.cantidadProducida = cantidadProducida;
    }

    public int getResistenciaPlagas() {
        return resistenciaPlagas;
    }

    protected void setResistenciaPlagas(int resistenciaPlagas) throws CultivoException {
        if (resistenciaPlagas < 0 || resistenciaPlagas > 100) {
            throw new CultivoException("La resistencia a plagas no puede ser menor a 0 ni mayor a 100");
        }
        this.resistenciaPlagas = resistenciaPlagas;
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cultivo cultivo = (Cultivo) o;
        return Objects.equals(nombre, cultivo.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Tiempo crecimiento: %d, Litros de agua necesaria: %d, Cantidad producida: %d," +
                        " Resistencia a plagas: %d, Nombre: %s", this.tiempoCrecimiento, this.litrosAguaNecesaria, this.cantidadProducida,
                this.resistenciaPlagas, this.nombre);
    }
}