package Boletin2.Ordenador;

import Boletin2.Videojuego.Videojuego;
import Boletin2.Videojuego.VideojuegoException;
import utils.MiEntradaSalida;

import java.util.Scanner;

public class Ordenador_App {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Creamos los objetos que van a conformar el ordenador
        PlacaBase pb = new PlacaBase();
        Microprocesador mp = new Microprocesador();
        DiscoDuro dd = new DiscoDuro();
        TarjetaGrafica tg = new TarjetaGrafica();

        // Bloque 'PlacaBase'

        //Le pedimos al usuario que introduzca la marca
        String marcaPlacaBase = MiEntradaSalida.solicitarCadena("Introduce la marca de la placa base");
        pb.setMarca(marcaPlacaBase);

        // Le pedimos al usuario que introduzca el chipset
        String chipsetPlacaBase = MiEntradaSalida.solicitarCadena("Introduce el chipset de la placa base");
        pb.setChipset(chipsetPlacaBase);

        // Le pedimos al usuario que introduzca el socket
        String socketPlacaBase = MiEntradaSalida.solicitarCadena("Introduce el socket de la placa base");
        pb.setSocket(socketPlacaBase);

        // Bloque 'Microprocesador'

        // Solicitamos la marca
        String marcaMicroprocesador = MiEntradaSalida.solicitarCadena("Introduce la marca" +
                " del Microprocesador");
        mp.setMarca(marcaMicroprocesador);

        // Solicitamos el modelo
        String modeloMicroprocesador = MiEntradaSalida.solicitarCadena("Introduce el modelo" +
                " del Microprocesador");
        mp.setModelo(modeloMicroprocesador);

        // Solicitamos el número de núcleos
        int numeroNucleos = MiEntradaSalida.solicitarEnteroPositivo("Introduce el número de" +
                " núcleos del microprocesador");
        mp.setNumeroDeNucleos(numeroNucleos);

        // Solicitamos la velocidad base
        boolean error;

        do {
            error = true;
            try {
                System.out.println("Introduce la velocidad base del microprocesador");
                float velocidadBaseMicroprocesador = sc.nextFloat();
                mp.setVelocidadBase(velocidadBaseMicroprocesador);
                error = false;

            } catch (PlacaBaseException e) {
                System.out.println(e.getMessage());
            }
        } while (error);

        // Solicitamos el socket y comprabamos que sea compatible con el de la 'PlacaBase'

        boolean error2;

        do {
            error2 = true;
            String socketMicroprocesador = MiEntradaSalida.solicitarCadena("Introduce el socket" +
                    " del microprocesador");
            mp.setSocket(socketMicroprocesador);
            try {
                pb.esCompatible(mp);
                error2 = false;

            } catch (PlacaBaseException e) {
                System.out.println(e.getMessage());
            }
        } while (error2);


        // Bloque 'DiscoDuro'

        //Solicitamos la marca
        String marcaDiscoDuro = MiEntradaSalida.solicitarCadena("Introduzca la marca del disco duro");
        dd.setMarca(marcaDiscoDuro);

        //Solicitamos el tipo
        String tipoDiscoDuro = MiEntradaSalida.solicitarCadena("Introduce el tipo del disco duro");
        dd.setTipo(tipoDiscoDuro);

        //Solicitamos la capacidad
        int capacidadDiscoDuro = MiEntradaSalida.solicitarEnteroPositivo("Introduce la capacidad" +
                " del disco duro");
        dd.setCapacidad(capacidadDiscoDuro);

        // Bloque tarjeta gráfica

        // Solicitamos la marca
        String marcaTarjetaGrafica = MiEntradaSalida.solicitarCadena("Introduce la marca" +
                " de la tarjeta gráfica");
        tg.setMarca(marcaTarjetaGrafica);

        // Solicitamos el modelo
        String modeloTarjetaGrafica = MiEntradaSalida.solicitarCadena("Introduce el modelo de la" +
                " tarjeta gráfica");
        tg.setModelo(modeloTarjetaGrafica);

        // Solicitamos el número de núcleos
        int numeroDeNucleos = MiEntradaSalida.solicitarEnteroPositivo("Introduce el número de núcleos de la" +
                " tarjeta gráfica");
        tg.setNumeroDeNucleos(numeroDeNucleos);

        //Solicitamos la velocidad

        boolean error3;

        do {
            error3 = true;
            try {
                System.out.println("Introduce la velocidad de la tarjeta gráfica");
                float velocidadTarjetaGrafica = sc.nextFloat();
                tg.setVelocidad(velocidadTarjetaGrafica);
                error3 = false;

            } catch (PlacaBaseException e) {
                System.out.println(e.getMessage());
            }
        } while (error3);

        // Solicitamos la cantidad de memoria
        int cantidadMemoriaTarjetaGrafica = MiEntradaSalida.solicitarEnteroPositivo("Introduce la memoria" +
                " de la tarjeta gráfica");
        tg.setCantidadDeMemoria(cantidadMemoriaTarjetaGrafica);

        // Creamos la clase ordenador, pasándole sus perisféricos
        Ordenador o = new Ordenador(pb, mp, dd, tg);
        // Imprimimos los atributos de todos los componentes del ordenador

        System.out.println(o);

        // Bloque de videojuego

        // Creamos la clase videojuego
        Videojuego vd;

        try {
            // Le damos valores a los atributos de la clase 'videojuego' y comprabamos su compatibilidad
            vd = new Videojuego("Fallout 76", "Bethesda",
                    2018, 8, 3.6F, 96,
                    8, 100);
            vd.compatibilidad(o);
            System.out.println(vd.getNombre() + " de " + vd.getCompaniaCreadora() + " del año "
                    + vd.getAnoCreacion() + " es compatible");

        } catch (VideojuegoException e) {
            System.out.println(e.getMessage());
        }
    }
}