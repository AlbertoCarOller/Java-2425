package Boletin1.CuentaCredito;

public class CuentaCredito extends Cuenta {

    // Atributos
    private double credito;
    // El límite del crédito válido
    private static final double MAX_CREDITO = 300;

    /*
    public CuentaCredito(String numCuenta, String titular) {
        super(numCuenta, titular);
        this.credito = 100;
    }

    public CuentaCredito(String numCuenta, String titular, double saldo, boolean saldoYNoCredito) {
        super(saldo, numCuenta, titular);
        this.credito = 100;
    }

    public CuentaCredito(String numCuenta, String titular, double credito) throws CuentaExcepcion {
        super(numCuenta, titular);
        setCredito(credito);
    }

    public CuentaCredito(String numCuenta, String titular, double credito, double saldo) throws CuentaExcepcion {
        super(saldo, numCuenta, titular);
        setCredito(credito);
    }
     */

    // Hacemos el constructor privado para que no se puedan crear objetos desde fuera de esta clase
    private CuentaCredito(String numCuenta, String titular, double credito, double saldo) throws CuentaExcepcion {
        super(saldo, numCuenta, titular);
        setCredito(credito);
    }

    public static CuentaCredito sinSaldoYSinCredito(String numCuenta, String titular) throws CuentaExcepcion {
        double saldo = 0;
        double credito = 100;

        return new CuentaCredito(numCuenta, titular, credito, saldo);
    }

    public static CuentaCredito sinSaldoYConCredito(String numCuenta, String titular, double credito) throws CuentaExcepcion {
        double saldo = 0;

        return new CuentaCredito(numCuenta, titular, credito, saldo);
    }

    public static CuentaCredito conSaldoYSinCredito(String numCuenta, String titular, double saldo) throws CuentaExcepcion {
        double credito = 100;

        if (saldo < (credito * (-1))) {
            throw new CuentaExcepcion("El saldo no cumple con los requisitos");
        }

        return new CuentaCredito(numCuenta, titular, credito, saldo);
    }

    public static CuentaCredito conSaldoYConCredito(String numCuenta, String titular, double credito, double saldo) throws CuentaExcepcion {

        if (saldo < (credito * (-1))) {
            throw new CuentaExcepcion("El saldo no cumple con los requisitos");
        }
        return new CuentaCredito(numCuenta, titular, credito, saldo);
    }

    // Hacemos los set
    public void setCredito(double credito) throws CuentaExcepcion {

        if (credito > MAX_CREDITO) {
            throw new CuentaExcepcion("El límite del crédito no puede ser mayor de 300 euros");
        }

        if (credito < 0) {
            throw new CuentaExcepcion("El crédito no puede ser negativo");
        }
        this.credito = credito;
    }

    // Hacemos los get
    public double getCredito() {
        return credito;
    }

    // Modificamos la clase del reintegro para tener en cuenta también el crédito a la hora de sacar dinero
    public void reintegro(double retirada) throws CuentaExcepcion {

        if (retirada < 1) {
            throw new CuentaExcepcion("No se puede retirar dinero por debajo de 1");
        }
        // Modificamos esta condición colocando 'suma + crédito', porque será la cantidad máxima que se pueda sacar
        if (retirada > super.saldo + this.credito) {
            throw new CuentaExcepcion("La retirada es mayor que el saldo y el crédito juntos");
        }

        /* Si la retirada es mayor que el saldo y es menor o igual al saldo más el credito, el saldo valdrá 0 y el
         crédito lo restante */
        if (retirada > super.saldo && retirada <= super.saldo + this.credito) {
            retirada -= super.saldo;
            super.saldo = 0;
            this.credito -= retirada;

            // Si la retirada es menor que el saldo, se lo restamos directamente al saldo
        } else if (retirada <= super.saldo) {
            super.saldo -= retirada;
            super.contadorRetiradas++;
        }
    }

    // Hacemos un método para mostrar el saldo y el crédito
    public void mostrarSaldoYCredito() {

        System.out.println("El saldo es de " + super.saldo);
        System.out.println("El crédito es de " + this.credito);
    }
}