package Ejercicio11;

public class Vendedor extends Empleado implements Bonificable {

    // Creamos los atributos
    private int ventasTotales;

    // Creamos el constructor
    public Vendedor(String nombre, double salario, int ventasTotales) throws EmpleadoException {
        super(nombre, salario);
        setVentasTotales(ventasTotales);
        if (ventasTotales == 0) {
            super.setSalario(0);
        }
    }

    // Hacemos los get y set
    public int getVentasTotales() {
        return ventasTotales;
    }

    private void setVentasTotales(int ventasTotales) throws EmpleadoException {
        if (ventasTotales < 0 || ventasTotales > 5000) {
            throw new EmpleadoException("Las ventas totales no pueden ser menor a 0 ni mayor a 5000");
        }
        this.ventasTotales = ventasTotales;
    }

    @Override
    public void calcularBonificacion() throws EmpleadoException {
        if (this.ventasTotales == 0) {
            throw new EmpleadoException("Con 0 ventas totales no se puede calcular la bonificación");
        }
        super.setSalario(super.getSalario() + (ventasTotales * 0.15));
    }
}