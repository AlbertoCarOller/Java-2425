package Extra.Ejercicio8;

public class BibliotecaApp {
    public static void main(String[] args) {
        try {
            // Creamos la biblioteca
            BibliotecaMagica bibliotecaMagica = new BibliotecaMagica("Chelu´s library");
            // Creamos grimorios
            Grimorio grimorio = new Grimorio("Chelu´s grimorio", false);
            Grimorio grimorio1 = new Grimorio("Atisbedo´s grimorio", false);
            Grimorio grimorio2 = new Grimorio("Carles Xavier´s grimorio", false);
            Grimorio grimorio3 = new Grimorio("Saragarcon´s grimorio", true);
            Grimorio grimorio4 = new Grimorio("Lolitogoku´s grimorio", true);
            Grimorio grimorio5 = new Grimorio("Respicio godefrio´s grimorio", true);
            // Añadimos los grimorios a la biblioteca
            bibliotecaMagica.registrarGrimorio(grimorio);
            bibliotecaMagica.registrarGrimorio(grimorio1);
            bibliotecaMagica.registrarGrimorio(grimorio2);
            bibliotecaMagica.registrarGrimorio(grimorio3);
            bibliotecaMagica.registrarGrimorio(grimorio4);
            bibliotecaMagica.registrarGrimorio(grimorio5);
            // Llamamos a los métodos para consultar los grimorios
            bibliotecaMagica.consultarGrimorio();
            bibliotecaMagica.consultarListaNegra();
            // Llamamos a los métodos para mostrar las pilas de grimorios
            System.out.println();
            bibliotecaMagica.mostrarPila('C');
            System.out.println();
            bibliotecaMagica.mostrarListaNegra();
            System.out.println();
            System.out.println(bibliotecaMagica.grimorioPorS(bibliotecaMagica.getPilaGrimorios()));

        } catch (GrimorioException e) {
            System.out.println(e.getMessage());
        }
    }
}