package Boletin1.Ejercicio5;

public class DiccionarioApp {
    public static void main(String[] args) {
        // Creamos un diccionario
        Diccionario diccionario = new Diccionario();
        try {
            // Creamos varias palabras
            Palabra palabra1 = new Palabra("Atisbedo", "Persona atractiva");
            Palabra palabra2 = new Palabra("Atisbedo", "Persona con gran conocimiento");
            Palabra palabra3 = new Palabra("Bermudo", "El mejor profesor de programación que jamás ha existido");
            Palabra palabra4 = new Palabra("Villalba", "Von Neumann");
            Palabra palabra5 = new Palabra("Villanueva", "Una villa nueva");
            Palabra palabra6 = new Palabra("RespicioGodefrío", "OK");

            // Llamamos al método para añadir palabras al diccionario
            diccionario.anadirPalabra(palabra1);
            diccionario.anadirPalabra(palabra2);
            diccionario.anadirPalabra(palabra3);
            diccionario.anadirPalabra(palabra4);
            diccionario.anadirPalabra(palabra5);
            System.out.println("----------------------------------------------------");
            System.out.println(diccionario.getPalabras());

            // Llamamos al método para buscar una palabra en el diccionario
            System.out.println("----------------------------------------------------");
            System.out.println(diccionario.buscarPalabra(palabra2));

            // Llamamos al método que devuelve la lista ordenada de las palbras con el prefijo elegido
            System.out.println("----------------------------------------------------");
            System.out.println(diccionario.copiaOrdenada("Villa"));

            // Llamamos al método para eliminar una palabra
            diccionario.eliminarPalabra(palabra5);
            System.out.println("----------------------------------------------------");
            System.out.println(diccionario.getPalabras());

        } catch (DireccionExcepcion e) {
            System.out.println(e.getMessage());
        }
    }
}