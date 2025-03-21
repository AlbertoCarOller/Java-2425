package ExamenInstrumentos;

import java.util.ArrayList;
import java.util.List;

public class InstrumentoApp {
    static List<Instrumento> instrumentos = new ArrayList<>();

    public static void main(String[] args) {
        try {
            // Creamos los instrumentos y los añadimos a la lista de instrumentos
            instrumentos.add(new GuitarraAcustica("Guitarra española", Material.MADERA, false, 2.5));
            instrumentos.add(new GuitarraElectrica("Fender Stratocaster", Material.METAL, true, 3.5));
            instrumentos.add(new Piano("Yamaha Clavinova", Material.MADERA, false));
            instrumentos.add(new Flauta("ExamenInstrumentos.Flauta dulce", Material.PLASTICO, true, 0.5));
            instrumentos.add(new Bateria("Batería acústica", Material.METAL, false));
            // Llamamos a los métodos que comprueban el tipo y llama a sus respectivos métodos
            llamarAfinableYTocable();
            llamarPortables();
            llamarAmplificable();
        } catch (InstrumentoException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void llamarAfinableYTocable() {
        System.out.println("\nPrueba afinación y tocar");
        for (Instrumento instrumento : instrumentos) {
            instrumento.afinar();
            if (instrumento.isAfinado()) {
                System.out.println(instrumento.getNombre() + " está afinado");

            } else {
                System.out.println(instrumento.getNombre() + " no está afinado");
            }
            instrumento.tocar();
        }
    }
    public static void llamarPortables() {
        System.out.println("\nPrueba portabilidad");
        for (Instrumento instrumento : instrumentos) {
            if (instrumento instanceof Portable portable) {
                if (portable.mostrarFacilidad()) {
                    System.out.println(instrumento.getNombre() + " es portable");

                } else {
                    System.out.println(instrumento.getNombre() + " no es portable");
                }
            }
        }
    }

    public static void llamarAmplificable() {
        System.out.println("\nPrueba amplificable");
        for (Instrumento instrumento : instrumentos) {
            if (instrumento instanceof Amplificable amplificable) {
                amplificable.conectarAmplificador();
                amplificable.ajustarVolumen();
            }
        }
    }
}