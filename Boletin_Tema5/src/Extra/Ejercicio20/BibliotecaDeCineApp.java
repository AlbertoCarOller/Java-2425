package Extra.Ejercicio20;

import java.time.LocalDate;

public class BibliotecaDeCineApp {
    public static void main(String[] args) {
        try {
            // Creamos la biblioteca
            BibliotecaDeCine bibliotecaDeCine = new BibliotecaDeCine();
            // Creamos frases
            Frase frase = new Frase("Hey Chelu", "La gran muralla Chelu", "Chelu",
                    LocalDate.of(2023, 7, 22), 5);
            Frase frase1 = new Frase("Quítate las manos de los bolsillos", "La locura en el trabajo",
                    "Antonio", LocalDate.of(2025, 2, 13), 0);
            Frase frase2 = new Frase("Voy a hacerle una oferta que no podrá rechazar", "El padrino",
                    "Marlon Brando", LocalDate.of(1972, 12, 8), 4);
            Frase frase3 = new Frase("Me parece que ya no estamos en Kansas", "El Mago de Oz",
                    "Judy Garlan", LocalDate.of(1939, 6, 12), 3);
            Frase frase4 = new Frase("Alégrame el día", "Impacto súbito", "Clint Eastwood",
                    LocalDate.of(1983, 10, 23), 4);
            Frase frase5 = new Frase("Que la fuerza te acompañe", "La guerra de las galaxias",
                    "Harrison Ford", LocalDate.of(1977, 3, 7), 5);
            Frase frase6 = new Frase("Hablas conmigo", "Taxi driver", "Robert De Niro",
                    LocalDate.of(1976, 11, 6), 3);
            Frase frase7 = new Frase("Enséñame la pasta", "Jerry Maguire", "Cuba Gooding",
                    LocalDate.of(1996, 4, 11), 2);

            // Añadimos categorías a la biblioteca
            bibliotecaDeCine.addCategoria("Acción");
            bibliotecaDeCine.addCategoria("Fantasía");
            // Añadimos las películas a las categorías
            bibliotecaDeCine.addFrase(frase, "Acción", "Fantasía");
            bibliotecaDeCine.addFrase(frase1, "Acción");
            bibliotecaDeCine.addFrase(frase2, "Fantasía");
            bibliotecaDeCine.addFrase(frase3, "Fantasía");
            bibliotecaDeCine.addFrase(frase4, "Acción", "Fantasía");
            bibliotecaDeCine.addFrase(frase5, "Acción");
            bibliotecaDeCine.addFrase(frase6, "Acción");
            bibliotecaDeCine.addFrase(frase7, "Acción", "Fantasía");
            // Probamos los diferentes métodos para comprobar su correcto funcionamiento
            System.out.println(bibliotecaDeCine.categoriasSinFraseValor());
            System.out.println();
            System.out.println(bibliotecaDeCine.categoriasDeFrase(frase2));
            System.out.println();
            System.out.println(bibliotecaDeCine.frasesOrdenadasPorValoracion());
            System.out.println();
            bibliotecaDeCine.eliminarFrases(frase4);
            System.out.println();
            System.out.println(bibliotecaDeCine.frasesUltimoAno());
            System.out.println();
            System.out.println(bibliotecaDeCine.frasesConValor(4));
            System.out.println();
            System.out.println(bibliotecaDeCine.valoracionPromediaFrases());
            System.out.println();
            System.out.println(bibliotecaDeCine.mayorValoracionFrase());
            System.out.println();
            System.out.println(bibliotecaDeCine.mapaDeMin());
            System.out.println();
            System.out.println(bibliotecaDeCine.infoTextos());
            System.out.println();
            System.out.println(bibliotecaDeCine.frasesOrdenadasPorPeliculaAlfabeticamente());
            System.out.println();
            System.out.println(bibliotecaDeCine.mapaConActorYTextos());
            System.out.println();
            System.out.println(bibliotecaDeCine.peliculasRepresentadas());

        } catch (FraseException e) {
            System.out.println(e.getMessage());
        }
    }
}
