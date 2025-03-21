package Ejercicio18;

import java.util.ArrayList;
import java.util.List;

public class PeliculaApp {
    static List<Pelicula> peliculas = new ArrayList<>();

    public static void main(String[] args) {
        try {
            PeliculaComedia peliculaComedia = new PeliculaComedia("Chelu gracioso", 2.5, 23.5, 2);
            PeliculaAccion peliculaAccion = new PeliculaAccion("Chelu acción", 3, 10.3, 5);
            peliculas.add(peliculaComedia);
            peliculas.add(peliculaAccion);
            llamarMetodos();

        } catch (PeliculaException e) {
            System.out.println(e.getMessage());
        }
    }

    // Hacemos un método para llamar a los métodos dependiendo del tipo de película que sea
    public static void llamarMetodos() {
        for (Pelicula pelicula : peliculas) {
            if (pelicula instanceof Adaptable adaptable) {
                adaptable.adaptar();
                System.out.println(pelicula.getTitulo() + " ha adaptado los substítulos");
            }
            if (pelicula instanceof Verificable verificable) {
                boolean podido = verificable.verificar();
                if (podido) {
                    System.out.println(pelicula.getTitulo() + " ha podido ser verificado");

                } else {
                    System.out.println(pelicula.getTitulo() + " no se ha podido verificar");
                }
            }
            System.out.println(pelicula.getTitulo() + " tiene un coste total de " + pelicula.calcularCostoTotal());
        }
    }
}