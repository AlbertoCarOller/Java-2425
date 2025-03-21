package Boletin1.MaquinaDeCafe;

import java.util.Scanner;
import utils.MiEntradaSalida;

public class MaquinaDeCafe_App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MaquinaDeCafe mc = new MaquinaDeCafe();
        int op;
        String[] opciones = {"Introduzca 1 para pedir un café solo (1 euro)", "Introuzca un 2 para pedir leche (0,8 euros)",
                "Introduzca un 3 para pedir café con leche (1.5 euros)", "Pulse 4 para ver el estado de la máquina",
                "Pulsa 5 para vaciar el monedero", "Pulse 6 para apagar la máquina y salir"};

        do {
            op = MiEntradaSalida.seleccionarOpcion("Selecciona una opción", opciones);
            try {
                /*
                System.out.println("Introduzca 1 para pedir un café solo (1 euro)");
                System.out.println("Introuzca un 2 para pedir leche (0,8 euros)");
                System.out.println("Introduzca un 3 para pedir café con leche (1.5 euros)");
                System.out.println("Pulse 4 para ver el estado de la máquina");
                System.out.println("Pulsa 5 para vaciar el monedero");
                System.out.println("Pulse 6 para apagar la máquina y salir");

                op = sc.nextInt();*/

                if (op == 1) {

                    System.out.println("Introduzca su dinero");
                    double dineroCafe = sc.nextDouble();
                    mc.pedirCafeSolo(dineroCafe);
                }

                if (op == 2) {

                    System.out.println("Introduzca su dinero");
                    double dineroLeche = sc.nextDouble();
                    mc.pedirLeche(dineroLeche);
                }

                if (op == 3) {

                    System.out.println("Introduzca su dinero");
                    double dineroCafeConLeche = sc.nextDouble();
                    mc.pedirCafeConLeche(dineroCafeConLeche);
                }

                if (op == 4) {

                    estadoMaquina(mc);
                }

                if (op == 5) {

                    mc.vaciarMonedero();
                }

                if (op == 6) {

                    System.out.println("Apagando máquina, hasta pronto");
                }

            } catch (MaquinaDeCafeException e) {
                System.out.println(e.getMessage());
            }

        } while (op != 6);

    }

    private static void estadoMaquina(MaquinaDeCafe mc) {

        System.out.println("La máquina tiene " + mc.getDepositoCafe() + " de café solo, " +
                mc.getDepositoLeche() + " de leche, " + mc.getDepositoVasos() +
                " vasos y " + mc.getMonedero() + " euros en el monedero");
    }
}