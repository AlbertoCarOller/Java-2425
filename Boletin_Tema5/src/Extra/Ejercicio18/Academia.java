package Extra.Ejercicio18;

import java.util.*;
import java.util.stream.Collectors;

public class Academia {

    // Creamos los atributos
    private String nombre;
    private Set<AlumnoMusical> alumnos;
    private Set<GrupoMusical> grupos;

    // Creamos el constructor
    public Academia(String nombre) throws EstudianteMusicalException {
        setNombre(nombre);
        this.alumnos = new LinkedHashSet<>();
        this.grupos = new LinkedHashSet<>();
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) throws EstudianteMusicalException {
        if (!Character.isUpperCase(nombre.charAt(0))) {
            throw new EstudianteMusicalException("La primera letra debe estar en mayúsculas");
        }
        for (int i = 1; i < nombre.length(); i++) {
            if (!Character.isLowerCase(nombre.charAt(i))) {
                throw new EstudianteMusicalException("El resto de caracteres deben estar en minúscula y ser letras");
            }
        }
        this.nombre = nombre;
    }

    // Hacemos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Academia academia = (Academia) o;
        return Objects.equals(nombre, academia.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Hacemos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Alumnos: %s, Grupos: %s", this.nombre, this.alumnos, this.grupos);
    }

    // Hacemos un método para registrar un alumno
    public void registrarAlumno(AlumnoMusical alumnoMusical) throws EstudianteMusicalException {
        if (!alumnos.add(alumnoMusical)) {
            throw new EstudianteMusicalException("El alumno ya está registrado en la academia");
        }
    }

    // Hacemos un método para registrar un grupo
    public void registrarGrupo(GrupoMusical grupoMusical) throws EstudianteMusicalException {
        if (!grupos.add(grupoMusical)) {
            throw new EstudianteMusicalException("El grupo ya está registrado el grupo");
        }
    }

    // Hacemos un método para que un alumno realiza una audición
    public void realizarAudicion(AlumnoMusical alumnoMusical, Audicion audicion) throws EstudianteMusicalException {
        if (!alumnos.contains(alumnoMusical)) {
            throw new EstudianteMusicalException("El alumno no está registrado en la academia");
        }
        alumnoMusical.getAudiciones().add(audicion);
    }

    /* Hacemos un método para seleccinar los n primeros alumnos con mejor puntuación de su audición
     más reciente (difícil) */
    public void seleccionarMejoresAlumnosV1(GrupoMusical grupoMusical, int n) throws EstudianteMusicalException {
        if (!grupos.contains(grupoMusical)) {
            throw new EstudianteMusicalException("El grupo musical no se encuentra registrado en la academia");
        }
        if (n < 1 || n > alumnos.size()) {
            throw new EstudianteMusicalException("El valor de n no es correcto");
        }
        List<AlumnoMusical> mejoresAlumnos = Optional.of(alumnos.stream().filter(a ->
                                grupos.stream().flatMap(g -> g.getAlumnos().stream())
                                        .noneMatch(a1 -> a1.equals(a)))
                        .sorted(Comparator.comparingDouble(a -> {
                            try {
                                return a.getAudiciones().stream()
                                        .max(Comparator.comparing(Audicion::getFecha))
                                        .orElseThrow(() -> new EstudianteMusicalException(""))
                                        .getPuntuaje();

                            } catch (EstudianteMusicalException e) {
                                return 0.0;
                            }
                        })).toList()).orElseThrow(() -> new EstudianteMusicalException("No se han encontrado resultados"))
                .reversed().stream().limit(n).toList();
        grupoMusical.getAlumnos().addAll(mejoresAlumnos);
    }

    /* Hacemos un método para seleccinar los n primeros alumnos con mejor puntuación de su audición
     más reciente (fácil) */
    public int seleccionarMejoresAlumnosV2(GrupoMusical grupoMusical, int numAlumnos) throws EstudianteMusicalException {
        if (!grupos.contains(grupoMusical)) {
            throw new EstudianteMusicalException("El grupo no se encuentra registrado en la academia");
        }
        if (numAlumnos < 1 || numAlumnos > alumnos.size()) {
            throw new EstudianteMusicalException("El número de alumnos no está en un rango válido");
        }
        List<AlumnoMusical> alumnosSeleccionados = Optional.of(alumnos.stream().filter(a -> grupos.stream().flatMap(g ->
                                g.getAlumnos().stream()).noneMatch(a::equals))
                        .sorted(Comparator.comparingDouble(AlumnoMusical::obtenerUltimoPuntuaje))
                        .toList().reversed().stream().limit(numAlumnos).toList())
                .orElseThrow(() -> new EstudianteMusicalException("No hay alumnos encontrados"));
        grupoMusical.getAlumnos().addAll(alumnosSeleccionados);
        if (alumnosSeleccionados.size() < numAlumnos) {
            return alumnosSeleccionados.size();
        }
        return -1;
    }

    // Hacemos un método para eliminar un alumno de un grupo
    public void eliminarAlumno(GrupoMusical grupoMusical, AlumnoMusical alumnoMusical) throws EstudianteMusicalException {
        if (!grupos.contains(grupoMusical)) {
            throw new EstudianteMusicalException("El grupo no se encuentra registrado");
        }
        if (!grupoMusical.getAlumnos().remove(alumnoMusical)) {
            throw new EstudianteMusicalException("No se encuentra registrado el alumno");
        }
    }

    // Hacemos un método para buscar un alumno de un grupo
    public AlumnoMusical buscarAlumno(String nombre) throws EstudianteMusicalException {
        return grupos.stream().flatMap(g -> g.getAlumnos().stream())
                .filter(a -> a.getNombre().equalsIgnoreCase(nombre))
                .findFirst().orElseThrow(() -> new EstudianteMusicalException("No se encuentran resultados"));
    }

    // Hacemos un método para devolver una lista de las audiciones de un alumno ordenado por puntuaje
    public List<Audicion> listaAudicionesOrdenadas(AlumnoMusical alumnoMusical) throws EstudianteMusicalException {
        if (!alumnos.contains(alumnoMusical)) {
            throw new EstudianteMusicalException("El alumno no está registrado en la academia");
        }
        return Optional.of(alumnoMusical.getAudiciones().stream()
                        .sorted(Comparator.comparingDouble(Audicion::getPuntuaje)).toList())
                .orElseThrow(() -> new EstudianteMusicalException("No hay audiciones para el alumno " + alumnoMusical.getNombre()));
    }

    // Hacemos un método que va a devolver una lista de alumnos de un grupo ordenados alfabéticamente
    public List<AlumnoMusical> listaAlumnosOrdenados(GrupoMusical grupoMusical) throws EstudianteMusicalException {
        if (!grupos.contains(grupoMusical)) {
            throw new EstudianteMusicalException("El grupo no se encuentra registrado");
        }
        return Optional.of(grupoMusical.getAlumnos().stream().sorted(Comparator.comparing(AlumnoMusical::getNombre))
                .toList()).orElseThrow(() -> new EstudianteMusicalException("No hay alumnos"));
    }

    // Hacemos un método que va a devolver una lista de los alumnos de dos grupos
    public List<AlumnoMusical> fusionarGrupos(GrupoMusical grupoMusical1, GrupoMusical grupoMusical2) throws EstudianteMusicalException {
        if (!grupos.contains(grupoMusical1) || !grupos.contains(grupoMusical2)) {
            throw new EstudianteMusicalException("Alguno de los grupos no se encuentra registrado");
        }
        List<AlumnoMusical> alumnosFusionados = new ArrayList<>(grupoMusical1.getAlumnos());
        alumnosFusionados.addAll(grupoMusical2.getAlumnos());
        return alumnosFusionados;
    }

    // Hacemos un método que me va a devolver un mapa con los grupos y el número de alumnos de cada uno
    public Map<String, Integer> mapaDeGrupos() {
        return grupos.stream().map(g -> Map.entry(g.getNombre(), g.getAlumnos().size()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    // Hacemos un método genérico que devuelva una lista de los n primeros grupos ()
    public <T> List<T> primerosGruposOrdenado(List<T> lista, int n) throws EstudianteMusicalException {
        if (n < 1 || n > grupos.size()) {
            throw new EstudianteMusicalException("n está fuera de rango");
        }
        return Optional.of(lista.stream().limit(n).toList())
                .orElseThrow(() -> new EstudianteMusicalException("La lista está vacía"));
    }
}