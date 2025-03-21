package Boletin2.Partido;

public class Partido {

    // Atributos
    private int jornada;
    private String quiniela;
    private String estadio;
    private Equipo equipoLocal;
    private Equipo equipoVisitante;

    // Comprobar si el partido se ha jugado
    private boolean jugado;

    // Le pasaremos por parámetros el nombre del estadio y el número de la jornada
    public Partido(int jornada, String estadio, Equipo equipoLocal, Equipo equipoVisitante) throws EquipoException {
        setJornada(jornada);
        this.estadio = estadio;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.jugado = false;
    }

    // Hacemos los get para devolver los valores de los atributos
    public int getJornada() {
        return jornada;
    }

    public String getQuiniela() {
        return quiniela;
    }

    public String getEstadio() {
        return estadio;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    // Hacemos los set para moidificar los atributos
    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public void setJornada(int jornada) throws EquipoException {

        if (jornada < 1 || jornada > 38) {

            throw new EquipoException("Este número no es válido para una jornada");
        }

        this.jornada = jornada;
    }

    public void ponerResultado(String resultado) {

        // Si se ha jugado el partido pasará a true
        jugado = true;

        // A partir de un String sacamos los goles y lo pasamos a int
        String[] resultados = resultado.split("-");
        int golesEquipoLocal = Integer.parseInt(resultados[0]);
        int golesEquipoVisitante = Integer.parseInt(resultados[1]);

        // Actualizamos los goles y la quiniela
        this.equipoLocal.setGoles(golesEquipoLocal);
        this.equipoVisitante.setGoles(golesEquipoVisitante);
        this.quiniela = resultado;

        // Comprobamos que equipo gana y le sumamos 1 a los partidos ganados
        if (golesEquipoLocal > golesEquipoVisitante) {

            this.equipoLocal.sumarPartidoGanado();

        } else if (golesEquipoLocal < golesEquipoVisitante) {

            this.equipoVisitante.sumarPartidoGanado();
        }
    }

    // Hacemos un toString
    @Override
    public String toString() {

        if (jugado) {
            return "Partido{" +
                    "jornada=" + jornada +
                    ", quiniela='" + quiniela + '\'' +
                    ", estadio='" + estadio + '\'' +
                    ", equipoLocal=" + equipoLocal +
                    ", equipoVisitante=" + equipoVisitante +
                    '}';

        } else {
            return "Partido{" +
                    "jornada=" + jornada +
                    ", equipoLocal=" + equipoLocal.getNombre() +
                    ", equipoVisitante=" + equipoVisitante.getNombre() +
                    '}' + " Aún no se ha jugado";
        }
    }
}