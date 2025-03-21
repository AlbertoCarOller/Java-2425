package Boletin1.CuentaCredito;

public class Cuenta {

    private String numCuenta;
    private String titular;
    protected double saldo;
    protected int contadorRetiradas;
    protected int contadorIngresos;

    // Creamos otro constructor
    public Cuenta(double saldo, String numCuenta, String titular) throws CuentaExcepcion {
        setSaldo(saldo);
        this.numCuenta = numCuenta;
        this.titular = titular;
        this.contadorRetiradas = 0;
        this.contadorIngresos = 0;
    }

    public void setSaldo(double saldo) throws CuentaExcepcion {
        this.saldo = saldo;
    }

    public void reintegro(double retirada) throws CuentaExcepcion {

        if (retirada < 0) {
            throw new CuentaExcepcion("El saldo es insuficiente, introduzaca una cantidad menor");

        } else if (retirada > saldo) {
            throw new CuentaExcepcion("La retirada es mayor que el saldo");
        }
        this.saldo -= retirada;
        this.contadorRetiradas++;
    }

    public void ingreso(double ingreso) throws CuentaExcepcion {

        if (ingreso < 0 || ingreso == 0) {
            throw new CuentaExcepcion("El ingreso no debe ser nada o negativo");
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