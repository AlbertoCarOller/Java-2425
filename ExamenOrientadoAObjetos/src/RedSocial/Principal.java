package RedSocial;

public class Principal {
    public static void main(String[] args) {
        Usuario usuario1 = new Usuario("Juan");
        Usuario usuario2 = new Usuario("Pedro");
        Usuario usuario3 = new Usuario("Maria");

        /*
        Juan y Pedro son amigos
        Juan y Maria son amigos
        María agregó a Pedro como amigo, pero Pedro no la agregó a ella, por tanto, ni María ni Pedro pueden ver sus
        respectivos mensajes privados

         */
        try {
            usuario1.agregarAmigo(usuario2);
            usuario1.agregarAmigo(usuario3);
            usuario2.agregarAmigo(usuario1);
            usuario3.agregarAmigo(usuario1);
            usuario3.agregarAmigo(usuario2);


            usuario1.publicarMensaje("Hola a todos", true);
            usuario1.publicarMensaje("Pasando el finde en casa", false);
            usuario1.publicarMensaje("Estudiando mucho", true);
            usuario2.publicarMensaje("Hola amigos", false);
            usuario2.publicarMensaje("Estoy jugando al LOL, ¿alguien para una partidita?", true);
            usuario3.publicarMensaje("¡Buenos días!", true);
            usuario3.publicarMensaje("¡¡Mañana me voy de vacaciones!!", false);
        } catch (MensajeException e) {
            System.out.println(e.getMessage());
        }

        try {
            usuario2.publicarMensaje("Este mensaje supera la longitud máxima permitida, porque tiene más caracteres del número máximo que se indica en la clase. De hecho, tiene un char más.", true);
        } catch (MensajeException e) {
            System.out.println(e.getMessage());
        }

        usuario1.darLike(usuario2.getMensajes()[0]);
        usuario1.darLike(usuario3.getMensajes()[0]);
        usuario2.darLike(usuario1.getMensajes()[0]);
        usuario3.darLike(usuario3.getMensajes()[0]);

        System.out.println("Juan ve los mensajes de Pedro:");
        usuario1.verMensajes(usuario2);
        System.out.println();
        System.out.println("Juan ve los mensajes de Maria:");
        usuario1.verMensajes(usuario3);
        System.out.println();
        System.out.println("Pedro ve los mensajes de Juan:");
        usuario2.verMensajes(usuario1);
        System.out.println();
        System.out.println("Pedro ve los mensajes de Maria:");
        usuario2.verMensajes(usuario3);
        System.out.println();
        System.out.println("Maria ve los mensajes de Juan:");
        usuario3.verMensajes(usuario1);
        System.out.println();
        System.out.println("Maria ve los mensajes de Pedro:");
        usuario3.verMensajes(usuario2);
    }
}