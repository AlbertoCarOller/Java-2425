package Ejercicio3;

import utils.MiEntradaSalida;

import java.time.Year;

public class DispositivoApp {

    // Creamos el tamaño que va a tener el array de dispositivos
    static final int TAMANO_MAXIMO = 20;
    // Creamos el array y le damos el valor
    static Dispositivo[] dispositivos = new Dispositivo[TAMANO_MAXIMO];

    public static void main(String[] args) {
        try {
            // Creamos varios dispositivos
            Telefono telefono = new Telefono("atisbedo", "apple", Year.of(2008), 2.5,
                    SistemaOperativo.IOS, 60);
            Tableta tableta = new Tableta("bedo", "alcatel", Year.of(2005), 50.5,
                    SistemaOperativo.ANDROID, true);
            Ordenador ordenador = new Ordenador("atis", "alfredo", Year.of(2009),
                    34, "plastico", 50);
            Monitor monitor = new Monitor("bedobedo", "xavier", Year.of(2020), 60,
                    "metal", 50.6);
            // Metemos los dispositivos en el array
            dispositivos[0] = telefono;
            dispositivos[1] = tableta;
            dispositivos[2] = ordenador;
            dispositivos[3] = monitor;

            // Llamamos al método
            metodosDispositivos();

        } catch (DispositivoExcepcion e) {
            System.out.println(e.getMessage());
        }
    }

    // Hacemos un método para llamar a los métodos de los dispositivos
    public static void metodosDispositivos() throws DispositivoExcepcion {

        for (int i = 0; i < dispositivos.length; i++) {

            if (dispositivos[i] instanceof Recargable recargable) {
                recargable.mostrarCarga();
            }

            if (dispositivos[i] instanceof Ordenador ordenador) {
                int ajusteOrdenador = MiEntradaSalida.solicitarEnteroPositivo("Introduce cuanto le quieres sumar a la memoria RAM");
                ordenador.ajustar(ajusteOrdenador);
            }

            if (dispositivos[i] instanceof Monitor monitor) {
                int ajusteMonitor = MiEntradaSalida.solicitarEnteroPositivo("Introduce cuanto le quieres dar al brillo");
                monitor.ajustar(ajusteMonitor);
            }
        }
    }
}