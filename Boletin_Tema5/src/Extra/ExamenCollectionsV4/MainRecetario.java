package Extra.ExamenCollectionsV4;

import java.util.List;
import java.util.Set;

public class MainRecetario {
    public static void main(String[] args) {
        RecetarioV2 recetario = new RecetarioV2();

        try {
            // Crear recetas
            RecetaV2 receta1 = new RecetaV2("Tortilla Española", "Juan", "Plato Principal");
            RecetaV2 receta2 = new RecetaV2("Gazpacho", "Ana", "Entrante");
            RecetaV2 receta3 = new RecetaV2("Tarta de Queso", "Juan", "Postre");

            // Añadir recetas al recetario
            recetario.addReceta(receta1);
            recetario.addReceta(receta2);
            recetario.addReceta(receta3);

            // Añadir ingredientes
            receta1.anadirIngrediente("Huevos");
            receta1.anadirIngrediente("Patatas");
            receta2.anadirIngrediente("Tomates");
            receta2.anadirIngrediente("Pepino");
            receta3.anadirIngrediente("Queso");
            receta3.anadirIngrediente("Galletas");

            // Añadir valoraciones
            ValoracionV2 valoracion1 = new ValoracionV2("Carlos", 5, "Deliciosa!");
            ValoracionV2 valoracion2 = new ValoracionV2("Laura", 4, "Muy buena.");
            ValoracionV2 valoracion3 = new ValoracionV2("Carlos", 2, "No me gustó.");

            recetario.addValoracion("Tortilla Española", "Juan", valoracion1);
            recetario.addValoracion("Gazpacho", "Ana", valoracion2);
            recetario.addValoracion("Tarta de Queso", "Juan", valoracion3);

            // Obtener recetas por categoría
            List<RecetaV2> recetasPlatoPrincipal = recetario.getRecetasPorCategoria("Plato Principal");
            System.out.println("Recetas de Plato Principal:");
            recetasPlatoPrincipal.forEach(System.out::println);

            // Calcular media de valoraciones
            double mediaTortilla = recetario.mediaValoraciones("Tortilla Española", "Juan");
            System.out.println("Media de valoraciones de Tortilla Española: " + mediaTortilla);

            // Obtener usuarios activos en la última semana
            Set<String> usuariosActivos = recetario.usuariosActivosUltimaSemana();
            System.out.println("Usuarios activos en la última semana: " + usuariosActivos);

            // Obtener categorías disponibles
            Set<String> categoriasDisponibles = recetario.getCategoriasDisponibles();
            System.out.println("Categorías disponibles: " + categoriasDisponibles);

            // Obtener recetas de un autor específico
            List<RecetaV2> recetasJuan = recetario.getRecetasDeAutor("Juan");
            System.out.println("Recetas de Juan:");
            recetasJuan.forEach(System.out::println);

            // Buscar recetas por ingrediente
            List<RecetaV2> recetasConQueso = recetario.buscarRecetasPorIngrediente("Queso");
            System.out.println("Recetas que contienen Queso:");
            recetasConQueso.forEach(System.out::println);

            // Obtener recetas con valoraciones bajas
            Set<RecetaV2> recetasBajas = recetario.recetasConValoracionesBajas();
            System.out.println("Recetas con valoraciones bajas:");
            recetasBajas.forEach(System.out::println);

            // Obtener ingredientes más usados
            List<String> ingredientesMasUsados = recetario.ingredientesMasUsados();
            System.out.println("Ingredientes más usados:");
            ingredientesMasUsados.forEach(System.out::println);

            // Obtener recetas ordenadas por puntuación media
            List<RecetaV2> recetasOrdenadas = recetario.getRecetasOrdenadasPorPuntuacionMedia();
            System.out.println("Recetas ordenadas por puntuación media:");
            recetasOrdenadas.forEach(System.out::println);

        } catch (RecetarioException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}