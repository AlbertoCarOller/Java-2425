package Boletin1.Ejercicio7;

public class RecetarioApp {
    public static void main(String[] args) {
        try {
            Recetario recetario = new Recetario();
            Receta receta = new Receta("Tortilla de papas", 3);
            Receta receta1 = new Receta("Chocolate con leche", 1);
            Receta receta2 = new Receta("Vainilla", 2);
            receta.anadirIngrediente(new Ingrediente("villalba"));
            receta1.anadirIngrediente(new Ingrediente("chocobon"));
            receta2.anadirIngrediente(new Ingrediente("Villalba"));
            receta2.anadirIngrediente(new Ingrediente("Papas"));
            recetario.anadirReceta(receta);
            recetario.anadirReceta(receta1);
            recetario.anadirReceta(receta2);
            System.out.println(recetario);
            System.out.println();
            System.out.println(recetario.listadoRecetasOrdenadasAlfabeticamente());
            System.out.println();
            System.out.println("Recetas ordenadas con el ingrediente: " + recetario.
                    listadoRecetasConIngredienteOrdenadasPorTiempoPreparacion("Villalba"));

        } catch (IngredienteException e) {
            System.out.println(e.getMessage());
        }
    }
}