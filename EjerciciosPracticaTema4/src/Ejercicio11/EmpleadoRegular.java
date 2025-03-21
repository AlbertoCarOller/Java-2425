package Ejercicio11;

public class EmpleadoRegular extends Empleado {

    // Creamos el constructor
    public EmpleadoRegular(String nombre, double salario) throws EmpleadoException {
        super(nombre, salario);
        setSalario(salario);
    }

    // Modificamos el set del salario para que el ingreso no pueda ser mayor a 1700 euros
    @Override
    protected void setSalario(double salario) throws EmpleadoException {
        if (salario > 1700) {
            throw new EmpleadoException("Los empleados regulares no pueden cobrar más de 1700 euros");
        }
        super.setSalario(salario);
    }
}