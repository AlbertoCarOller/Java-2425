import java.util.Arrays;
import java.util.Objects;

public class Usuario {

    // Atributos
    private String nombre;
    private String apellido;
    private String dni;
    private int numeroSocio;

    /* Creamos esta variable estática para poder asignarle de forma correcta un número de socio a los usuarios sin
     que se repitan, ya que si incrementamos 'numeroSocio++' esto solo nos dará 0 ya que en el constructor se inicia
      con -1 y se le suma 1, lo que siempre se reiniciaría a -1 al crear un nuevo usuario */
    private static int numeroSocioAsigando = 1;

    // Creamos un almacén donde guardaremos los diferentes libros (biblioteca)
    private static final Libro[] biblioteca = new Libro[5];

    // Hacemos el constructor
    public Usuario(String nombre, String apellido, String dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.numeroSocio = -1;
    }

    // Hacemos los set
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNumeroSocio(int numeroSocio) {
        this.numeroSocio = numeroSocio;
    }

    // Hacemos los get
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public int getNumeroSocio() {
        return numeroSocio;
    }

    // Hacemos un método para hacer a un usuario socio
    public void convertirASocio(Usuario usuario) throws UsuarioException {

        // Comprobamos si son la misma persona
        if (this.dni.equals(usuario.dni)) {

            // En caso de que sean la misma persona comprobamos si es socio
            if (this.numeroSocio != -1 || usuario.numeroSocio != -1) {

                throw new UsuarioException("No puedes convertirte en socio porque ya lo eres");
            }

        } else {
            // Comprobamos si es socio
            if (this.numeroSocio != -1) {

                throw new UsuarioException("Ya eres socio");
            }
        }

        // Le asignamos un número de socio totalmente distinto a cada socio
        this.numeroSocio = numeroSocioAsigando;
        numeroSocioAsigando++;
    }

    // Hacemos un método para añadir un libro a la biblioteca
    public void introducirLibro(Libro libroAMeter) throws UsuarioException {

        int hayEspacio = -1;
        boolean esDiferente = true;
        int posicionMismoLibro = 0;

        // Comprobamos que el libro sea distinto
        for (int i = 0; i < biblioteca.length; i++) {

            if (libroAMeter.equals(biblioteca[i])) {

                esDiferente = false;
                posicionMismoLibro = i;
            }
            // Una vez que comprobamos que sea distinto, comprobamos que haya espacio
            if (biblioteca[i] == null && hayEspacio == -1) {

                hayEspacio = i;
            }
        }

        // Si es diferente y hay espacio lo guarda en la posición disponible
        if (esDiferente && hayEspacio != -1) {

            biblioteca[hayEspacio] = libroAMeter;

            // Si no es diferente le sumamos un número de ejemplar a los ejemplares que ya había de ese mismo libro
        } else if (!esDiferente) {

            biblioteca[posicionMismoLibro].setNumeroEjemplares(biblioteca[posicionMismoLibro].getNumeroEjemplares() + 1);
            biblioteca[posicionMismoLibro].setEjemplaresDisponibles(biblioteca[posicionMismoLibro].getNumeroEjemplares() + 1);
            // Si no hay espacio y es diferente pues no se podrá almacenar el libro
        } else {

            throw new UsuarioException("No hay espacio en la biblioteca");
        }
    }

    // Hacemos un método que permita realizar el préstamo de un libro
    public void prestamoLibro(Libro libro) throws UsuarioException {

        boolean libroEncontrado = false;

        // Miramos si el libro existe en la biblioteca
        for (int i = 0; i < biblioteca.length; i++) {

            // Si el libro existe comprobamos que el número de ejemplares disponibles sea mayor a 0
            if (libro.equals(biblioteca[i])) {

                libroEncontrado = true;

                if (biblioteca[i].getEjemplaresDisponibles() > 0) {

                    // Le restamos 1 al número de ejemplares y al número de ejemplares disponibles
                    biblioteca[i].setNumeroEjemplares(biblioteca[i].getNumeroEjemplares() - 1);
                    biblioteca[i].setEjemplaresDisponibles(biblioteca[i].getEjemplaresDisponibles() - 1);
                    break;
                } else {

                    throw new UsuarioException("No hay ejemplares disponibles del libro");
                }
            }
        }

        if (!libroEncontrado) {

            throw new UsuarioException("El libro no se encuentra en la biblioteca");
        }
    }

    // Hacemos un método que permita hacer la devolución de un libro
    public void devolucionLibro(Libro libro) throws UsuarioException {

        boolean libroEncontrado = false;

        for (int i = 0; i < biblioteca.length; i++) {

            if (biblioteca[i].equals(libro)) {

                biblioteca[i].setNumeroEjemplares(biblioteca[i].getNumeroEjemplares() + 1);
                biblioteca[i].setEjemplaresDisponibles(biblioteca[i].getEjemplaresDisponibles() + 1);
                libroEncontrado = true;
            }
        }

        if (!libroEncontrado) {

            throw new UsuarioException("El libro no está en la biblioteca");
        }
    }

    // Hacemos un método para imprimir el contenido de biblioteca
    public void imprimirBiblioteca() {

        System.out.println(Arrays.toString(biblioteca));
    }
}