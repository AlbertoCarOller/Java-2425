package Ejercicio11;

public class Gerente extends Empleado implements BonificableEspecial {

    // Creamos el constructor
    public Gerente(String nombre, double salario) throws EmpleadoException {
        super(nombre, salario);
    }

    @Override
    public void mensajeMotivacional() {
        System.out.println(super.getNombre() + " continua así y verás que los " + super.getSalario() + " euros los multiplicarás por 10");
    }

    @Override
    public void calcularBonificacion() throws EmpleadoException {
        super.setSalario(super.getSalario() + (super.getSalario() * 0.30));
    }
}