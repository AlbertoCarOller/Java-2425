package Boletin1.Cuenta;

import java.util.Scanner;

public class Cuenta_App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        // Se crea el objeto
        Cuenta c = new Cuenta();
        boolean error;

        do {
            error = false;
            try {
                double saldoInicial;
                System.out.println("Introduzca su saldo");
                saldoInicial = sc.nextDouble();
                // Esto se hace para corregir un error
                sc.nextLine();
                c.setSaldo(saldoInicial);

            } catch (CuentaException e) {
                System.out.println(e.getMessage());
                error = true;
            }
        } while (error);

        int op = 0;

        do {
            try {
                System.out.println("Introduza su número de cuenta");
                String numCuenta = sc.nextLine();
                c.setNumCuenta(numCuenta);

                System.out.println("Introduza el nombre del titular");
                String nombreTitular = sc.nextLine();
                c.setTitular(nombreTitular);


                System.out.println("Pulsa 1 para hacer una retirada de dinero");
                System.out.println("Pulsa 2 para ingresar dinero");
                System.out.println("Pulsa 3 para mostrar las operaciones realizadas");
                System.out.println("Pulsa 4 para finalizar");
                op = sc.nextInt();

                switch (op) {

                    case 1:
                        System.out.println("¿Cuánto quieres retirar?");
                        int dineroRetirado = sc.nextInt();
                        c.reintegro(dineroRetirado);
                        break;

                    case 2:
                        System.out.println("¿Cuánto dinero quieres ingresar?");
                        int dineroIngresa = sc.nextInt();
                        c.ingreso(dineroIngresa);
                        break;

                    case 3:
                        c.consultarOperaciones();
                        break;

                    case 4:
                        c.finalizar();
                        break;

                    default:
                        System.out.println("No ha seleccionado ninguna opción");
                        break;
                }

            } catch (CuentaException e) {
                System.out.println(e.getMessage());
            }
        } while (op != 4);
    }
}