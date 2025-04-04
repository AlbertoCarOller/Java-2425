package Extra.Ejercicio21;

public class PlataformaStreamingApp {
    public static void main(String[] args) {
        try {
            PlataformaStreaming plataforma = new PlataformaStreaming("Mi Plataforma");

            // Crear usuarios
            Usuario u1 = new Usuario("Ana");
            Usuario u2 = new Usuario("Luis");
            Usuario u3 = new Usuario("Carlos");

            // Registrar usuarios
            plataforma.registrarUsuario(u1);
            plataforma.registrarUsuario(u2);
            plataforma.registrarUsuario(u3);

            // Crear series
            Serie s1 = new Serie("Breaking Code", "Drama", 5);
            Serie s2 = new Serie("The Debugger", "Comedia", 3);
            Serie s3 = new Serie("Null Point", "Drama", 4);
            Serie s4 = new Serie("Byte Hunters", "Acción", 6);

            // Registrar series
            plataforma.registrarSerie(s1);
            plataforma.registrarSerie(s2);
            plataforma.registrarSerie(s3);
            plataforma.registrarSerie(s4);

            // Añadir usuarios a las series
            plataforma.anadirUsuarioSerie(s1, u1);
            plataforma.anadirUsuarioSerie(s1, u2);
            plataforma.anadirUsuarioSerie(s2, u1);
            plataforma.anadirUsuarioSerie(s3, u3);
            plataforma.anadirUsuarioSerie(s3, u1);
            plataforma.anadirUsuarioSerie(s4, u2);
            plataforma.anadirUsuarioSerie(s4, u3);

            // Listar usuarios que siguen s1
            System.out.println("Usuarios que siguen Breaking Code:");
            plataforma.usuariosOrdenados(s1).forEach(System.out::println);

            // Series que sigue Ana ordenadas por temporadas
            System.out.println("\nSeries que sigue Ana (ordenadas por temporadas):");
            plataforma.seriesPorUsuario(u1).forEach(System.out::println);

            // Series más populares
            System.out.println("\nSeries más populares:");
            plataforma.seriesMasPopularesV1().forEach(System.out::println);

            // Género más popular
            System.out.println("\nGénero(s) más popular(es):");
            plataforma.generosMasFamososV2().forEach(System.out::println);

            // Eliminar una serie
            plataforma.eliminarSerie(s1);
            System.out.println("\nTras eliminar Breaking Code, series más populares:");
            plataforma.seriesMasPopularesV1().forEach(System.out::println);

        } catch (SerieException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}