import utils.MiEntradaSalida;

public class Mundial {

    // Atributos
    private String nombreMundial;

    // Creamos un almacén con capacidad para 4 equipos
    private Equipo[] equipos;

    // Creamos una variable donde guardaremos el total de todos los goles
    private int numGolesMundial;

    // Hacemos el constructor
    public Mundial() {
        this.nombreMundial = "Copa Mundial de Chapas en Casa-Puerta Isla Cristina 2024";
        this.equipos = new Equipo[4];
        this.numGolesMundial = 0;
    }

    // Hacemos un get para poder acceder al nombre del mundial
    public String getNombreMundial() {
        return nombreMundial;
    }

    // Hacemos un método para añadir un equipo a la lista
    private void anadirEquipo(Equipo equipo) throws EquipoException {

        boolean esDistinto = true;
        int hayEspacio = -1;

        for (int i = 0; i < equipos.length; i++) {

            if (equipo.equals(equipos[i])) {
                esDistinto = false;
            }

            if (equipos[i] == null && hayEspacio == -1) {
                hayEspacio = i;
            }
        }

        if (hayEspacio != -1 && esDistinto) {
            equipos[hayEspacio] = equipo;

        } else if (!esDistinto) {
            throw new EquipoException("El equipo ya está en la lista");

        } else {
            throw new EquipoException("No hay espacio en la lista de equipos");
        }
    }

    // Hacemos un método para quitar a un equipo de la lista
    private void eliminarEquipo(Equipo equipo) throws EquipoException {

        boolean encontrado = false;

        for (int i = 0; i < equipos.length; i++) {

            if (equipo.equals(equipos[i])) {
                equipos[i] = null;
                encontrado = true;
            }
        }
        if (!encontrado) {
            throw new EquipoException("El equipo no se encuentra en la lista del mundial");
        }
    }

    // Hacemos un método para sumar todos los goles de todos los equipos
    public void golesMundial() {

        for (int i = 0; i < equipos.length; i++) {

            numGolesMundial += equipos[i].getNumGolesTotal();
        }
        System.out.println("Los goles totales del mundial son " + numGolesMundial);
    }

    // Hacemos un método para averiguar el equipo con más goles
    public void equipoGoleador() throws EquipoException {

        comprobarEquipos();

        int equipoMaxGoleador = equipos[0].getNumGolesTotal();
        int indice = 0;

        for (int i = 0; i < equipos.length - 1; i++) {

            if (equipoMaxGoleador < equipos[i + 1].getNumGolesTotal()) {
                equipoMaxGoleador = equipos[i + 1].getNumGolesTotal();
                indice = i + 1;
            }
        }
        System.out.println("El equipo con más goles es el de " + equipos[indice].getNombreEntrenador());
    }

    // Hacemos un método para obtener el jugador con más goles
    public void jugadorGoleador() throws EquipoException {

        comprobarEquipos();

        int golesMaxJugador = equipos[0].getListaJugadores()[0].getNumGolesMarcados();
        int indice = 0;
        int indice2 = 0;

        for (int i = 0; i < equipos.length; i++) {

            for (int j = 0; j < equipos[i].getListaJugadores().length - 1; j++) {

                if (equipos[i].getListaJugadores()[j + 1].getNumGolesMarcados() > golesMaxJugador) {

                    golesMaxJugador = equipos[i].getListaJugadores()[j + 1].getNumGolesMarcados();
                    indice = i;
                    indice2 = j + 1;
                }
            }
        }
        System.out.println("El jugador con más goles es " + equipos[indice].getListaJugadores()[indice2].getNombre());
    }

    // Hacemos un método para comprobar que estén los 4 equipos con sus 11 jugadores cada uno
    public void comprobarEquipos() throws EquipoException {

        for (int i = 0; i < equipos.length; i++) {

            if (equipos[i] == null) {
                throw new EquipoException("No están los 4 equipos");
            }

            for (int j = 0; j < equipos[i].getListaJugadores().length; j++) {

                if (equipos[i].getListaJugadores()[j] == null) {
                    throw new EquipoException("El equipo no está completo");
                }
            }
        }
    }

    // Hacemos un menú para la clase principal
    public void menu(int op) throws EquipoException {

        switch (op) {

            case 1:
                String pais = MiEntradaSalida.solicitarCadena("Cuál es el país del equipo");
                String nombreEntrenador = MiEntradaSalida.solicitarCadena("Cuál es el nombre del entrenador");
                Equipo equipo = new Equipo(pais, nombreEntrenador);
                anadirEquipo(equipo);
                break;

            case 2:

                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < equipos.length; i++) {

                    if (equipos[i] == null) {
                        sb.append(i + 1).append(". ").append("Falta equipo").append("\n");

                    } else {
                        sb.append(i).append(". ").append(equipos[i].getNombreEntrenador()).append("\n");
                    }
                }
                System.out.println(sb);
                int op2 = MiEntradaSalida.solicitarEnteroPositivo("Elija el entrenador para seleccionar" +
                        " el equipo");

                if (equipos[op2] == null) {
                    throw new EquipoException("Aún no se ha creado el equipo");

                } else {
                    String nombre = MiEntradaSalida.solicitarCadena("Introduce el nombre");
                    int edad = MiEntradaSalida.solicitarEnteroPositivo("Introduce la edad");
                    int goles = MiEntradaSalida.solicitarEnteroPositivo("Goles marcados");
                    Jugador jugador = new Jugador(nombre, edad, goles);
                    equipos[op2].anadirJugador(jugador);
                }
                break;

            case 3:
                String nombreEntrenadorABuscar = MiEntradaSalida.solicitarCadena("Introduce el nombre" +
                        " del entrenador");
                String nombrePaisRepresenta = MiEntradaSalida.solicitarCadena("Introduce el país");
                Equipo equipoABuscar = new Equipo(nombrePaisRepresenta, nombreEntrenadorABuscar);

                int error = -1;

                for (int i = 0; i < equipos.length; i++) {

                    if (equipoABuscar.equals(equipos[i])) {
                        equipos[i].totalGoles();
                        error = i;
                        break;
                    }
                }

                if (error == -1) {
                    throw new EquipoException("No se ha encontrado el equipo");
                }
                break;

            case 4:
                String nombreEntrenadorABuscar2 = MiEntradaSalida.solicitarCadena("Introduce el nombre" +
                        " del entrenador");
                String nombrePaisRepresenta2 = MiEntradaSalida.solicitarCadena("Introduce el país");
                Equipo equipoABuscar2 = new Equipo(nombrePaisRepresenta2, nombreEntrenadorABuscar2);

                int errorEquipo = -1;
                int errorJugador = -1;

                for (int i = 0; i < equipos.length; i++) {

                    if (equipoABuscar2.equals(equipos[i])) {
                        String jugadorEnBusca = MiEntradaSalida.solicitarCadena("Introduce el nombre" +
                                " del jugador");
                        Jugador jugador = new Jugador(jugadorEnBusca, 1, 1);
                        errorEquipo = i;

                        for (int j = 0; j < equipos[i].getListaJugadores().length; j++) {

                            if (jugador.equals(equipos[i].getListaJugadores()[j])) {
                                errorJugador = j;
                                System.out.println(equipos[i].getListaJugadores()[j].getNombre() + " tiene " +
                                        equipos[i].getListaJugadores()[j].getNumGolesMarcados() + " goles");
                                break;
                            }
                        }
                    }
                }

                if (errorEquipo == -1) {
                    throw new EquipoException("El equipo no se encuentra");

                } else if (errorJugador == -1) {
                    throw new EquipoException("El jugador no se encuentra");
                }
                break;

            case 5:
                equipoGoleador();
                break;

            case 6:
                jugadorGoleador();
                break;

            case 7:
                System.out.println("Adiós");
                break;

            default:
                System.out.println("No has seleccionado ninguna opción");
                break;
        }
    }
}