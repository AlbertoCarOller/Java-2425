package Extra.ExamenCollectionsV2;

public class PlataformaCriticaApp {
    public static void main(String[] args) {
        try {
            // Crear plataforma
            PlataformaCriticas plataforma = new PlataformaCriticas("CineCríticas");

            // Crear películas
            Pelicula p1 = new Pelicula("Interstellar", 2014, "Christopher Nolan", "Ciencia Ficción");
            Pelicula p2 = new Pelicula("El Padrino", 1975, "Francis Ford Coppola", "Drama");
            Pelicula p3 = new Pelicula("Inception", 2010, "Christopher Nolan", "Ciencia Ficción");
            Pelicula p4 = new Pelicula("Inside Out", 2015, "Pete Docter", "Animación");

            // Añadir películas
            plataforma.addPelicula(p1);
            plataforma.addPelicula(p2);
            plataforma.addPelicula(p3);
            plataforma.addPelicula(p4);

            // Crear reseñas
            Resena r1 = new Resena("Ana", 9, "Muy buena.");
            Resena r2 = new Resena("Carlos", 6, "Entretenida.");
            Resena r3 = new Resena("Lucía", 2, "No me gustó.");
            Resena r4 = new Resena("Ana", 10, "Obra maestra.");
            Resena r5 = new Resena("Pedro", 3, "Meh.");
            Resena r6 = new Resena("Carlos", 8, "Muy interesante.");

            // Añadir reseñas
            plataforma.addResena("Interstellar", 2014, r1);
            plataforma.addResena("Interstellar", 2014, r2);
            plataforma.addResena("El Padrino", 1975, r3);
            plataforma.addResena("Inception", 2010, r4);
            plataforma.addResena("Inside Out", 2015, r5);
            plataforma.addResena("Inside Out", 2015, r6);

            // Mostrar películas por género exacto
            System.out.println("\nPelículas de Ciencia Ficción:");
            plataforma.getPeliculasConGeneroV1("Ciencia Ficción").forEach(System.out::println);

            // Mostrar películas por varios géneros
            System.out.println("\nPelículas de Ciencia Ficción o Animación:");
            plataforma.getPeliculaConGeneroV2("Ciencia Ficción", "Animación").forEach(System.out::println);

            // Media valoraciones
            System.out.println("\nMedia valoraciones de Interstellar:");
            System.out.println(plataforma.mediaValoraciones("Interstellar", 2014));

            // Usuarios con reseñas recientes
            System.out.println("\nUsuarios con reseñas recientes:");
            plataforma.usuariosConResenasReciente().forEach(System.out::println);

            // Películas ordenadas por valoración media
            System.out.println("\nPelículas ordenadas por valoración media:");
            plataforma.getPeliculasOrdenadasPorValoracionMedia().forEach(System.out::println);

            // Películas con reseñas negativas
            System.out.println("\nPelículas con reseñas negativas:");
            plataforma.peliculasConResenaNegativa().forEach(System.out::println);

            // Eliminar reseñas de un usuario (versión 1)
            plataforma.eliminarResenasDeUsuarioV1("Carlos");
            System.out.println("\nDespués de eliminar reseñas de Carlos (v1):");
            System.out.println(plataforma);

            // Eliminar reseñas de un usuario (versión 2)
            plataforma.eliminarResenasDeUsuarioV2("Ana");
            System.out.println("\nDespués de eliminar reseñas de Ana (v2):");
            System.out.println(plataforma);

            // Mapa de géneros y número de reseñas
            System.out.println("\nMapa de géneros (V1):");
            System.out.println(plataforma.mapaGenerosV1());

            // Géneros más famosos (mayor cantidad de reseñas)
            System.out.println("\nGéneros más famosos:");
            plataforma.generosFamososV1().forEach(System.out::println);

            // Mapa de géneros ordenado por cantidad de películas (V2)
            System.out.println("\nMapa de géneros ordenado por cantidad de películas:");
            plataforma.mapaGenerosV2().forEach(System.out::println);

        } catch (PeliculaException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}