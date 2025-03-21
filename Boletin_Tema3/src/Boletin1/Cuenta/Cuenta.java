package Boletin1.Cuenta;

public class Cuenta {

    private String numCuenta;
    private String titular;
    private double saldo;
    private int contadorRetiradas;
    private int contadorIngresos;

    // Creamos el constructor vacío
    public Cuenta() {

        this.contadorRetiradas = 0;
        this.contadorIngresos = 0;
    }

    public void setSaldo(double saldo) throws CuentaException {

        if (saldo < 0) {

            throw new CuentaException("El saldo es negativo, no puede ser negativo");

        }
        this.saldo = saldo;
    }

    public void reintegro(double retirada) throws CuentaException {

        if (retirada < 0) {
            throw new CuentaException("El saldo es insuficiente, introduzaca una cantidad menor");

        } else if (retirada > saldo) {
            throw new CuentaException("La retirada es mayor que el saldo");
        }
        this.saldo -= retirada;
        this.contadorRetiradas++;
    }

    public void ingreso(double ingreso) throws CuentaException {

        if (ingreso < 0 || ingreso == 0) {
            throw new CuentaException("El ingreso no debe ser nada o negativo");
        }
        this.saldo += ingreso;
        this.contadorIngresos++;
    }

    public double getSaldo() {
        return saldo;
    }

    public void consultarOperaciones() {

        System.out.println("El saldo es de " + getSaldo());
        System.out.println("Se han realizado " + this.contadorRetiradas + " reintegros");
        System.out.println("Se han realizado " + this.contadorIngresos + " ingresos");
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public void finalizar() {

        System.out.println("Has salido, te has quedado con un saldo de " + this.saldo);
    }
}