package Boletin3.JuegoDeCartas;

public class Carta {

    // Atributos
    private String palo;
    private String numero;

    // Hacemos el constructor
    public Carta(String palo, String numero) {
        this.palo = palo;
        this.numero = numero;
    }

    // Hacemos un toString, para poder imprimir las cartas que contiene la baraja en la clase 'Juego'
    @Override
    public String toString() {
        return "Carta{" +
                "palo='" + palo + '\'' +
                ", numero='" + numero + '\'' +
                '}';
    }
}