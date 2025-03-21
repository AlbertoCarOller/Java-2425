package Boletin3.Mensajeria;

public class Persona {

    // Atributos
    private String nombre;
    private Mensaje[] mensajesRecibidos;
    private Mensaje[] mensajesEnviados;

    // Útiles
    private int espacioOcupadoBuzonEntrega;
    private int espacioOcupadoBuzonEnvio;
    private static final int TAMANO_BUZON = 5;

    // Generamos el constructor, solo introducimos por parámetros el nombre de la persona
    public Persona(String nombre) {
        this.nombre = nombre;
        // Creamos los buzones con capacidad de 5 mensajes
        mensajesRecibidos = new Mensaje[TAMANO_BUZON];
        mensajesEnviados = new Mensaje[TAMANO_BUZON];
        espacioOcupadoBuzonEnvio = 0;
        espacioOcupadoBuzonEntrega = 0;
    }

    // Hacemos los set
    public void setMensajesRecibidos(Mensaje[] mensajesRecibidos) {
        this.mensajesRecibidos = mensajesRecibidos;
    }

    public void setMensajesEnviados(Mensaje[] mensajesEnviados) {
        this.mensajesEnviados = mensajesEnviados;
    }

    public void setEspacioOcupadoBuzonEntrega(int espacioOcupadoBuzonEntrega) {
        this.espacioOcupadoBuzonEntrega = espacioOcupadoBuzonEntrega;
    }

    public void setEspacioOcupadoBuzonEnvio(int espacioOcupadoBuzonEnvio) {
        this.espacioOcupadoBuzonEnvio = espacioOcupadoBuzonEnvio;
    }

    // Hacemos los get
    public String getNombre() {
        return nombre;
    }

    public Mensaje[] getMensajesRecibidos() {
        return mensajesRecibidos;
    }

    public Mensaje[] getMensajesEnviados() {
        return mensajesEnviados;
    }

    public int getEspacioOcupadoBuzonEntrega() {
        return espacioOcupadoBuzonEntrega;
    }

    public int getEspacioOcupadoBuzonEnvio() {
        return espacioOcupadoBuzonEnvio;
    }

    // Hacemos un método para enviar un mensaje
    public void enviarMensaje(Persona destinatario, String asunto, String cuerpo) throws MensajeException {

        if (asunto.isBlank()) {

            throw new MensajeException("El mensaje debe tener asunto");
        }

        if (cuerpo.isBlank()) {

            throw new MensajeException("El mensaje debe tener cuerpo");
        }

        assert destinatario != null: "El destinatario no puede ser nulo";

        if (destinatario == this) {

            throw new MensajeException("No puedes enviarte un correo a tí mismo");
        }

        // Con esto comprobamos que los dos tengan el espacio suficiente, enviar y recibir el mensaje
        int enviarMensaje = -1;
        int recibirMensaje = -1;

        // Le añadimos el mensaje tanto al remitente como al destinatario en sus respectivos buzones
        for (int i = 0; i < this.mensajesEnviados.length; i++) {

            if (this.mensajesEnviados[i] == null) {
                // Podemos enviar el mensaje
                enviarMensaje = i;
                // Para parar el bucle
                break;
            }
        }

        for (int i = 0; i < destinatario.mensajesRecibidos.length; i++) {

            if (destinatario.mensajesRecibidos[i] == null) {

                recibirMensaje = i;
                break;
            }
        }

        // Si no hay espacio en alguno de los buzones saltará una excepción
        if (enviarMensaje == -1 || recibirMensaje == -1) {

            throw new MensajeException("No hay espacio en alguno de los buzones");
        }

        // Creamos el mensaje aquí, para no tener que creearlo dos veces el mismo
        Mensaje mensaje = new Mensaje(asunto, cuerpo, this, destinatario);

        // Le doy el valor del mensaje al array en la posición correspondiente en mensajes enviados
        this.mensajesEnviados[enviarMensaje] = mensaje;
        // Se le suma 1 cada vez que se ocupa un espacio
        this.espacioOcupadoBuzonEnvio++;
        // Le doy el valor del mensaje al buzón del destinatario en mensajes recibidos
        destinatario.mensajesRecibidos[recibirMensaje] = mensaje;
        // Se le suma 1 cada vez que se ocupa un espacio
        destinatario.espacioOcupadoBuzonEntrega++;
    }

    // Hacemos un método para borrar el mensaje enviado más antiguo del buzón
    public void borrarMensajeEnviadoMasAntiguo() throws MensajeException {

        if (this.espacioOcupadoBuzonEnvio == 0) {

            throw new MensajeException("El buzón de envios está vacío");
        }

        // Recorremos el buzón
        for (int i = 0; i < this.mensajesEnviados.length - 1; i++) {

            // En la posición y le damos el valor de la posición siguiente
            this.mensajesEnviados[i] = this.mensajesEnviados[i + 1];
        }
        // Una vez cambiada las posiciones le damos a la última posición el valor de null
        this.mensajesEnviados[this.mensajesEnviados.length - 1] = null;
        // Borramos 1 a la capacidad del buzón de enviados
        this.espacioOcupadoBuzonEnvio--;
    }

    // Hacemos un método para borrar el mensaje más antiguo recibido del buzón
    public void borrarMensajeRecibidoMasAntiguo() throws MensajeException {

        if (this.espacioOcupadoBuzonEntrega == 0) {

            throw new MensajeException("El buzón de mensajes entregados está vacío");
        }

        // Recorremos el buzón de mensajes recibidos
        for (int i = 0; i < this.mensajesRecibidos.length - 1; i++) {

            // Le damos a la posición 'i' la posición que está por delante
            this.mensajesRecibidos[i] = this.mensajesRecibidos[i + 1];
        }
        /* Una vez hemos cambiado las posiciones del buzón, le damos null a la última,
         ya que se repetirá con la posición 4 */
        this.mensajesRecibidos[this.mensajesRecibidos.length - 1] = null;
        // Borramos 1 a la capacidad del buzón de entregas
        this.espacioOcupadoBuzonEntrega--;
    }
}