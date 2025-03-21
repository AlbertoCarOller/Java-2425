package Ejercicio17;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JuegoEstrategia extends Juego implements Multijugable {

    // Creamos los atributos
    private List<String> recursos;
    private int duracionCampana;
    public static final double PORCENTAJE_EXTRA = 0.05;

    // Creamos el constructor
    public JuegoEstrategia(String titulo, String estudio, double costoDesarrollo, int duracionCampana) throws JuegoException {
        super(titulo, estudio, costoDesarrollo);
        this.recursos = new ArrayList<>();
        setDuracionCampana(duracionCampana);
    }

    // Hacemos los get y set
    public List<String> getRecursos() {
        return recursos;
    }

    private void setRecursos(List<String> recursos) {
        this.recursos = recursos;
    }

    public int getDuracionCampana() {
        return duracionCampana;
    }

    protected void setDuracionCampana(int duracionCampana) throws JuegoException {
        if (duracionCampana < 1) {
            throw new JuegoException("No puede tener menos de 1 hora de campaña");
        }
        this.duracionCampana = duracionCampana;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return super.toString() + ", Recursos: " + this.recursos + ", Duración campaña: " + this.duracionCampana;
    }

    @Override
    public double calcularCostoTotal() {
        return super.getCostoDesarrollo() + (super.getCostoDesarrollo() * PORCENTAJE_EXTRA);
    }

    @Override
    public void conectarAServidor() {
        Random probabilidad = new Random();
        if (probabilidad.nextBoolean()) {
            System.out.println("El juego " + super.getTitulo() + " se ha podido conectar al servidor");

        } else {
            System.out.println("El juego " + super.getTitulo() + " no se ha podido conectar al servidor");
        }
    }
}