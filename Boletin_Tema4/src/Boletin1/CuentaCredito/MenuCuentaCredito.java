package Boletin1.CuentaCredito;

import utils.MiEntradaSalida;

public class MenuCuentaCredito {
    public static void main(String[] args) {

        System.out.println("1. Crea una cuenta con saldo y con crédito predeterminado");
        System.out.println("2. Crea una cuenta sin saldo predeterminado y con crédito predeterminado");
        System.out.println("3. Crea una cuenta con saldo predeterminado y sin crédito predeterminado");
        System.out.println("4. Crea una cuenta sin saldo y sin crédito predeterminado");

        int op = MiEntradaSalida.solicitarEnteroPositivo("Elija una opción");

        switch (op) {

            case 1:
                int op1 = 0;
                do {
                    try {
                        String numCuenta = MiEntradaSalida.solicitarCadena("Introduce el número de cuenta");
                        String titular = MiEntradaSalida.solicitarCadena("Introduce el titular de la cuenta");
                        CuentaCredito c1 = CuentaCredito.sinSaldoYSinCredito(numCuenta, titular);

                        mostrarMenu();

                        op1 = MiEntradaSalida.solicitarEnteroPositivo("Elije una opción");

                        switch (op1) {

                            case 1:
                                double ingreso = MiEntradaSalida.solicitarDoublePositivo("Introduce el ingreso");
                                c1.ingreso(ingreso);
                                break;

                            case 2:
                                double retirada = MiEntradaSalida.solicitarDoublePositivo("Introduce la retirada");
                                c1.reintegro(retirada);
                                break;

                            case 3:
                                c1.mostrarSaldoYCredito();
                                break;

                            case 4:
                                System.out.println("Hasta la próxima");
                                break;

                            default:
                                System.out.println("No has seleccioando ninguna opción");
                                break;
                        }

                    } catch (CuentaExcepcion e) {
                        System.out.println(e.getMessage());
                    }
                } while (op1 != 4);
                break;

            case 2:
                int op2 = 0;
                do {
                    try {
                        String numCuenta = MiEntradaSalida.solicitarCadena("Introduce el número de cuenta");
                        String titular = MiEntradaSalida.solicitarCadena("Introduce el titular de la cuenta");
                        double saldo = MiEntradaSalida.solicitarDoublePositivo("Introduce el saldo");
                        CuentaCredito c2 = CuentaCredito.conSaldoYSinCredito(numCuenta, titular, saldo);

                        mostrarMenu();

                        op2 = MiEntradaSalida.solicitarEnteroPositivo("Elije una opción");

                        switch (op2) {

                            case 1:
                                double ingreso = MiEntradaSalida.solicitarDoublePositivo("Introduce el ingreso");
                                c2.ingreso(ingreso);
                                break;

                            case 2:
                                double retirada = MiEntradaSalida.solicitarDoublePositivo("Introduce la retirada");
                                c2.reintegro(retirada);
                                break;

                            case 3:
                                c2.mostrarSaldoYCredito();
                                break;

                            case 4:
                                System.out.println("Hasta la próxima");
                                break;

                            default:
                                System.out.println("No has seleccioando ninguna opción");
                                break;
                        }

                    } catch (CuentaExcepcion e) {
                        System.out.println(e.getMessage());
                    }
                } while (op2 != 4);
                break;

            case 3:
                int op3 = 0;
                do {
                    try {
                        String numCuenta = MiEntradaSalida.solicitarCadena("Introduce el número de cuenta");
                        String titular = MiEntradaSalida.solicitarCadena("Introduce el titular de la cuenta");
                        double credito = MiEntradaSalida.solicitarDoublePositivo("Introduce el crédito");
                        CuentaCredito c3 = CuentaCredito.sinSaldoYConCredito(numCuenta, titular, credito);

                        mostrarMenu();

                        op3 = MiEntradaSalida.solicitarEnteroPositivo("Elije una opción");

                        switch (op3) {

                            case 1:
                                double ingreso = MiEntradaSalida.solicitarDoublePositivo("Introduce el ingreso");
                                c3.ingreso(ingreso);
                                break;

                            case 2:
                                double retirada = MiEntradaSalida.solicitarDoublePositivo("Introduce la retirada");
                                c3.reintegro(retirada);
                                break;

                            case 3:
                                c3.mostrarSaldoYCredito();
                                break;

                            case 4:
                                System.out.println("Hasta la próxima");
                                break;

                            default:
                                System.out.println("No has seleccioando ninguna opción");
                                break;
                        }

                    } catch (CuentaExcepcion e) {
                        System.out.println(e.getMessage());
                    }
                } while (op3 != 4);
                break;

            case 4:
                int op4 = 0;
                do {
                    try {
                        String numCuenta = MiEntradaSalida.solicitarCadena("Introduce el número de cuenta");
                        String titular = MiEntradaSalida.solicitarCadena("Introduce el titular de la cuenta");
                        double credito = MiEntradaSalida.solicitarDoublePositivo("Introduce el crédito");
                        double saldo = MiEntradaSalida.solicitarDoublePositivo("Introduce el saldo");
                        CuentaCredito c4 = CuentaCredito.conSaldoYConCredito(numCuenta, titular, credito, saldo);

                        mostrarMenu();

                        op4 = MiEntradaSalida.solicitarEnteroPositivo("Elije una opción");

                        switch (op4) {

                            case 1:
                                double ingreso = MiEntradaSalida.solicitarDoublePositivo("Introduce el ingreso");
                                c4.ingreso(ingreso);
                                break;

                            case 2:
                                double retirada = MiEntradaSalida.solicitarDoublePositivo("Introduce la retirada");
                                c4.reintegro(retirada);
                                break;

                            case 3:
                                c4.mostrarSaldoYCredito();
                                break;

                            case 4:
                                System.out.println("Hasta la próxima");
                                break;

                            default:
                                System.out.println("No has seleccioando ninguna opción");
                                break;
                        }

                    } catch (CuentaExcepcion e) {
                        System.out.println(e.getMessage());
                    }
                } while (op4 != 4);
                break;
        }
    }

    // Hacemos un método que nos va a mostrar un menú una vez creada una cuenta
    public static void mostrarMenu() {

        System.out.println("1. Ingresar dinero");
        System.out.println("2. Sacar dinero");
        System.out.println("3. Mostrar saldo y crédito");
        System.out.println("4. Salir");
    }
}