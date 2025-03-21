package RedSocial;

import java.util.Arrays;
import java.util.Objects;

public class Usuario {

    // Creamos los atributos
    private String nombre;
    private static final int MAX_MENSAJES = 10;
    private Mensaje[] mensajes;
    private static final int NUM_AMIGOS = 15;
    private Usuario[] listaAmigos;
    //private String amigos;

    // Hacemos el constructor
    public Usuario(String nombre) {
        this.nombre = nombre;
        this.mensajes = new Mensaje[MAX_MENSAJES];
        this.listaAmigos = new Usuario[NUM_AMIGOS];
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Mensaje[] getMensajes() {
        return mensajes;
    }

    public void setMensajes(Mensaje[] mensajes) {
        this.mensajes = mensajes;
    }

    public Usuario[] getListaAmigos() {
        return listaAmigos;
    }

    public void setListaAmigos(Usuario[] listaAmigos) {
        this.listaAmigos = listaAmigos;
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", mensajes=" + Arrays.toString(mensajes) +
                ", listaAmigos=" + Arrays.toString(listaAmigos) +
                '}';
    }

    // Hacemos un equals para comparar los usuarios por su nombre
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(nombre, usuario.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    /**
     * Recorremos la lista de amigos, en caso de que haya espacio y el usuario no se haya añadido ya
     * lo añadimos a la lista
     *
     * @param usuario el usuario a añadir
     * @throws MensajeException
     */
    public void agregarAmigo(Usuario usuario) throws MensajeException {
        //this.amigos += usuario.getNombre() + ";";
        boolean esMismo = false;
        int hayEspacio = -1;

        for (int i = 0; i < listaAmigos.length && !esMismo; i++) {

            if (usuario.equals(listaAmigos[i])) {
                esMismo = true;
            }

            if (listaAmigos[i] == null && hayEspacio == -1) {
                hayEspacio = i;
            }
        }

        if (!esMismo && hayEspacio != -1) {
            listaAmigos[hayEspacio] = usuario;

        } else {
            throw new MensajeException("No se puede añadir el usuario");
        }
    }

    /**
     * Este método crea el mensaje y busca un espacio en el array de mensajes del usuario
     *
     * @param mensaje el cuerpo del mensaje
     * @param publico si el mensaje es público o no
     * @throws MensajeException
     */
    public void publicarMensaje(String mensaje, boolean publico) throws MensajeException {

        Mensaje mensajeAMeter = new Mensaje(mensaje, publico);
        int hayEspacio = -1;

        for (int i = 0; i < mensajes.length; i++) {

            if (mensajes[i] == null && hayEspacio == -1) {
                hayEspacio = i;
            }
        }

        if (hayEspacio != -1) {
            mensajes[hayEspacio] = mensajeAMeter;

        } else {
            throw new MensajeException("No hay espacio para el mensaje");
        }
    }
    /*
    public boolean somosAmigos(Usuario otro){
        String[] misAmigos = this.amigos.split(";");
    }
    */

    /**
     * Al mensaje introducido por parámetros modificamos su valor de likes, introduciendo los likes
     * que tenía y sumándole uno
     *
     * @param mensaje el mensaje al cual se le va a dar el like
     */
    public void darLike(Mensaje mensaje) {
        // Le sumamos uno al contador de likes del mensaje
        mensaje.setMeGustas(mensaje.getMeGustas() + 1);
    }

    /**
     * El método comprueba si son amigos, si son amigos, podrá ver los mensajes privados,
     * si no son amigos dependerá de si lo tiene en público o privado
     *
     * @param usuario el usuario del cuál vamos a mirar sus mensajes
     */
    public void verMensajes(Usuario usuario) {

        boolean amigos = false;

        for (int i = 0; i < usuario.listaAmigos.length; i++) {

            if (usuario.listaAmigos[i] == null) {
                continue;
            }

            /* Si el nombre del usuario aparece en la lista de amigos del otro usuario podrá acceder a todos
             los mensajes públicos o privados */
            if (this.nombre.equalsIgnoreCase(usuario.listaAmigos[i].getNombre())) {
                amigos = true;
                break;
            }
        }

        for (int i = 0; i < usuario.mensajes.length; i++) {

            if (usuario.mensajes[i] == null) {
                continue;
            }

            if (usuario.mensajes[i].isPublico()) {
                System.out.println("- " + usuario.mensajes[i]);

            } else if (amigos && !usuario.mensajes[i].isPublico()) {
                System.out.println("- " + usuario.mensajes[i]);
            }
        }
    }
}