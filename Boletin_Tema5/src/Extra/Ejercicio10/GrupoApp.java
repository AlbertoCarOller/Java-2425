package Extra.Ejercicio10;

public class GrupoApp {
    public static void main(String[] args) {
        try {
            // Creamos un grupo
            Grupo grupo = new Grupo("Chelu´s group");
            // Añadimos los miembros al grupo
            grupo.registrarMiembro("Lolitogoku");
            grupo.registrarMiembro("Chelu");
            grupo.registrarMiembro("Carles xavier");
            // Añadimos eventos al grupo
            grupo.registrarEvento("Natalio peluqueros event");
            grupo.registrarEvento("Xavier´s event");
            grupo.registrarEvento("Atisbedísimo event");
            // Completamos un evento
            grupo.eliminarEvento();
            // Llamamos a los métodos para mostrar
            System.out.println(grupo.mostrarEventos());
            System.out.println(grupo.mostrarMiembros());
            System.out.println(grupo.ordenarEventos());
            // Llamamos al método que nos va a decir si existe un evento o no
            if (grupo.buscarEvento("Xavier´s event")) {
                System.out.println("El evento existe");

            } else {
                System.out.println("No existe el evento");
            }

        } catch (GrupoException e) {
            System.out.println(e.getMessage());
        }
    }
}