package Boletin2.Videojuego;

import Boletin2.Ordenador.DiscoDuro;
import Boletin2.Ordenador.Microprocesador;
import Boletin2.Ordenador.Ordenador;
import Boletin2.Ordenador.TarjetaGrafica;

public class Videojuego {

    // Atributos
    private String nombre;
    private String companiaCreadora;
    private int anoCreacion;

    // Requisitos mínimos del ordenador
    private int nucleosMinimosMicroprocesador;
    private float velocidadMinimaMicroprocesador;
    private int espacioMinimoDiscoDuro;
    private int nucleosMinimosTarjetaGrafica;
    private int espacioMinimoTarjetaGrafica;

    // Constantes
    private static final int ANO_ACTUAL = 2024;
    // Año de la creación del primer videojuego
    private static final int ANO_MINIMO = 1952;

    // Creamos el constructor
    public Videojuego(String nombre, String companiaCreadora, int anoCreacion,
                      int nucleosMinimosMicroprocesador, float velocidadMinimaMicroprocesador,
                      int espacioMinimoDiscoDuro, int nucleosMinimosTarjetaGrafica,
                      int espacioMinimoTarjetaGrafica) throws VideojuegoException {
        this.nombre = nombre;
        this.companiaCreadora = companiaCreadora;
        setAnoCreacion(anoCreacion);
        setNucleosMinimosMicroprocesador(nucleosMinimosMicroprocesador);
        setVelocidadMinimaMicroprocesador(velocidadMinimaMicroprocesador);
        setEspacioMinimoDiscoDuro(espacioMinimoDiscoDuro);
        setNucleosMinimosTarjetaGrafica(nucleosMinimosTarjetaGrafica);
        setEspacioMinimoTarjetaGrafica(espacioMinimoTarjetaGrafica);
    }

    // Comprueba que el año de creación del videojuego tenga consistencia
    public void setAnoCreacion(int anoCreacion) throws VideojuegoException {

        if (anoCreacion > ANO_ACTUAL || anoCreacion < ANO_MINIMO) {

            throw new VideojuegoException("Este año no es válido");
        }

        this.anoCreacion = anoCreacion;
    }

    // Comprueba que el número de núcleos del microprocesador no sea negativo ni 0
    public void setNucleosMinimosMicroprocesador(int nucleosMinimosMicroprocesador) throws VideojuegoException {

        if (nucleosMinimosMicroprocesador < 1) {

            throw new VideojuegoException("Introduce una cantidad válida");
        }

        this.nucleosMinimosMicroprocesador = nucleosMinimosMicroprocesador;
    }

    // Comprueba que la velocidad del microprocesador no sea negativo ni 0
    public void setVelocidadMinimaMicroprocesador(float velocidadMinimaMicroprocesador) throws VideojuegoException {

        if (velocidadMinimaMicroprocesador < 1) {

            throw new VideojuegoException("Introduce una cantidad válida");
        }

        this.velocidadMinimaMicroprocesador = velocidadMinimaMicroprocesador;
    }

    // Comprueba que el espacio del disco duro no sea negativo ni 0
    public void setEspacioMinimoDiscoDuro(int espacioMinimoDiscoDuro) throws VideojuegoException {

        if (espacioMinimoDiscoDuro < 1) {

            throw new VideojuegoException("Introduce una cantidad válida");
        }

        this.espacioMinimoDiscoDuro = espacioMinimoDiscoDuro;
    }

    // Comprueba que los núcleos de la tarjeta gráfica no sea negativo ni 0
    public void setNucleosMinimosTarjetaGrafica(int nucleosMinimosTarjetaGrafica) throws VideojuegoException {

        if (nucleosMinimosTarjetaGrafica < 1) {

            throw new VideojuegoException("Introduce una cantidad válida");
        }

        this.nucleosMinimosTarjetaGrafica = nucleosMinimosTarjetaGrafica;
    }

    // Comprueba que el espacio de la tarjeta gráfica no sea negativo
    public void setEspacioMinimoTarjetaGrafica(int espacioMinimoTarjetaGrafica) throws VideojuegoException {

        if (espacioMinimoTarjetaGrafica < 0) {

            throw new VideojuegoException("Introduce una cantidad válida");
        }

        this.espacioMinimoTarjetaGrafica = espacioMinimoTarjetaGrafica;
    }

    // Hacemos los get para mostrar los valores de estos atributos
    public String getNombre() {
        return nombre;
    }

    public String getCompaniaCreadora() {
        return companiaCreadora;
    }

    public int getAnoCreacion() {
        return anoCreacion;
    }

    // Creamos un método para comparar los requisitos del videojuego con el ordenador
    public void compatibilidad(Ordenador o) throws VideojuegoException {

        // Creamos objetos del tipo según el objeto
        Microprocesador mc = o.getMicroprocesador();
        DiscoDuro dd = o.getDiscoDuro();
        TarjetaGrafica tg = o.getTarjetaGrafica();

        // Comprueba que se cumplan los requisitos de núcleos del microprocesador
        if (mc.getNumeroDeNucleos() < this.nucleosMinimosMicroprocesador) {

            throw new VideojuegoException("Los nucleos del microprocesador no cumple con los requisitos");
        }

        // Comprueba que se cumplan los requisitos de velocidad del microprocesador
        if (mc.getVelocidadBase() < this.velocidadMinimaMicroprocesador) {

            throw new VideojuegoException("La velocidad del microprocesador no cumple los requisitos");
        }

        //Comprueba que se cumplan los requisitos de espacio en el disco duro
        if (dd.getCapacidad() < this.espacioMinimoDiscoDuro) {

            throw new VideojuegoException("El espacio del disco duro es insuficiente");
        }

        // Comprueba que se cumplan los requisitos de espacio en la tarjeta gráfica
        if (tg.getCantidadDeMemoria() < this.espacioMinimoTarjetaGrafica) {

            throw new VideojuegoException("La memoria de la tarjeta grafica es insuficiente");
        }

        // Comprueba que se cumplan los requisitos de núcleos en la tarjeta gráfica
        if (tg.getNumeroDeNucleos() < this.nucleosMinimosTarjetaGrafica) {

            throw new VideojuegoException("Los nucleos de la tarjeta gráfica no cumple los requisitos");
        }
    }
}