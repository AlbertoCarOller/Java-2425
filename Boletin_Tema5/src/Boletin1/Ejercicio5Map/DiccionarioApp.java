package Boletin1.Ejercicio5Map;

public class DiccionarioApp {
    public static void main(String[] args) {
        Diccionario diccionario = new Diccionario();
        try {
            diccionario.anadirPalabra("Villalba", "Segundo mejor profesor de la historia");
            diccionario.anadirPalabra("Villalba", "Von neumann");
            diccionario.anadirPalabra("Atisbedo", "Leyenda de la Edad Media");
            diccionario.anadirPalabra("Alberto", "Persona muy atractiva");
            System.out.println(diccionario.buscarPalabra("Villalba"));
            System.out.println();
            System.out.println(diccionario.listaOrdenada("A"));
            System.out.println();
            diccionario.eliminarPalabra("Villalba");
            System.out.println(diccionario);
            System.out.println();

        } catch (DiccionarioException e) {
            System.out.println(e.getMessage());
        }
    }
}