package Extra.Ejercicio17;

import java.util.*;
import java.util.stream.Collectors;

public class Academia {

    // Creamos los atributos
    private String nombre;
    private Map<String, Set<Estudiante>> cursos;

    // Creamos el constructor
    public Academia(String nombre) {
        this.nombre = nombre;
        this.cursos = new LinkedHashMap<>();
    }

    // Hacemos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) throws EstudianteException {
        this.nombre = nombre;
    }

    // Hacemos un método para inscribir un estudiante en un curso
    public void inscribirEstudiante(String curso, Estudiante estudiante) throws EstudianteException {
        if (!cursos.containsKey(curso)) {
            throw new EstudianteException("El curso no se encuentra en la academia");
        }
        if (!cursos.get(curso).add(estudiante)) {
            throw new EstudianteException("El estudiante ya se encuentra en el curso");
        }
    }

    // Hacemos un método para eliminar un estudiante de un curso
    public void eliminarEstudiante(String curso, Estudiante estudiante) throws EstudianteException {
        if (!cursos.containsKey(curso)) {
            throw new EstudianteException("No se encuentra el curso en la academia");
        }
        if (!cursos.get(curso).remove(estudiante)) {
            throw new EstudianteException("No se encuentra el estudiante en el curso");
        }
    }

    // Hacemos un método para consultar en qué curso o cursos está un estudiante
    public List<String> consultarCursosEstudiante(Estudiante estudiante) throws EstudianteException {
        List<String> cursosEstudiante = cursos.keySet().stream().filter(c -> cursos.get(c).contains(estudiante))
                .toList();
        if (cursosEstudiante.isEmpty()) {
            throw new EstudianteException("No se han encontrado cursos");
        }
        return cursosEstudiante;
    }

    /* Hacemos un método que va a devolver un Mapa ordenado alfabéticamente por el curso y el número
     de estudiantes de cada curso */
    public Map<String, Integer> mapaCursoNumEstudiantes() {
        return cursos.keySet().stream().map(c -> Map.entry(c, cursos.get(c).size()))
                // TODO: Preguntar sobre la falta de '<>' aquí y en el ejercicio anterior sí estaban
                .sorted(Map.Entry.comparingByKey())
                /* Implementamos el .toMap() sobrecargado, que acepta también una función y un suplier, la función
                 coge el value más antiguo en caso de keys repetidas y el suplier permite crear un LinkedHashMap
                  y así mantener el orden al transformarlo en un mapa */
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (v1, v2) -> v1, LinkedHashMap::new));
    }
}