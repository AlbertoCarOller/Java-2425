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
                // Con los entrySet hace falta inferir el tipo y cuando cogemos directamente la clave no es necesario
                .sorted(Map.Entry.comparingByKey())
                /* Implementamos el .toMap() sobrecargado, que acepta también una función y un suplier, la función
                 coge el value más antiguo en caso de keys repetidas y el suplier permite crear un LinkedHashMap
                  y así mantener el orden al transformarlo en un mapa */
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (v1, v2) -> v1, LinkedHashMap::new));
    }

    // Hacemos un método para registrar un curso en la academia
    public void registrarCurso(String curso) throws EstudianteException {
        if (!Character.isUpperCase(curso.charAt(0))) {
            throw new EstudianteException("La primera letra debe estar en mayúsculas");
        }
        for (int i = 1; i < curso.length(); i++) {
            if (!Character.isAlphabetic(curso.charAt(i)) && !Character.isSpaceChar(curso.charAt(i))) {
                throw new EstudianteException("El nombre debe estar compuesto por letras");
            }
        }
        if (cursos.containsKey(curso)) {
            throw new EstudianteException("El curso ya está registrado en la academia");
        }
        cursos.put(curso, new HashSet<>());
    }

    // Hacemos un método para devolver los estudiantes de un curso ordenados alfabéticamente
    public List<Estudiante> estudiantesOrdenadosDeCurso(String curso) throws EstudianteException {
        List<Estudiante> estudiantesOrdenados = cursos.keySet().stream().filter(curso::equalsIgnoreCase)
                .flatMap(c -> cursos.get(c).stream())
                .sorted(Comparator.comparing(Estudiante::getNombre)).toList();
        if (estudiantesOrdenados.isEmpty()) {
            throw new EstudianteException("No se encuentran resultados");
        }
        return estudiantesOrdenados;
    }

    // Hacemos un método para unir dos cursos
    public Map<String, Set<Estudiante>> fusionarCursosV1(String curso1, String curso2) throws EstudianteException {
        if (!(cursos.containsKey(curso1) || cursos.containsKey(curso2))) {
            throw new EstudianteException("Los cursos no se encuentran registrados");
        }
        return cursos.keySet().stream().filter(curso1::equalsIgnoreCase)
                .collect(Collectors.toMap(c -> c.concat("|").concat(curso2),
                        c -> cursos.keySet().stream().filter(c1 -> c1.equalsIgnoreCase(curso1) || c1.equalsIgnoreCase(curso2))
                                .flatMap(c1 -> cursos.get(c1).stream()).collect(Collectors.toSet())));
    }

    // Hacemos un método para unir dos cursos de forma fácil
    public Set<Estudiante> fusionarCursosV2(String curso1, String curso2) throws EstudianteException {
        return Optional.of(cursos.keySet().stream().filter(c -> c.equalsIgnoreCase(curso1) || c.equalsIgnoreCase(curso2))
                        .flatMap(c -> cursos.get(c).stream())
                        .collect(Collectors.toSet()))
                .orElseThrow(() -> new EstudianteException("No se han encontrado resultados"));
    }

    // Hacemos un método para obtener los estudiantes que estén en dos cursos a la vez
    public List<Estudiante> estudiantesCoincidentesEnCursosV1(String curso1, String curso2) throws EstudianteException {
        return Optional.of(cursos.keySet().stream().filter(c -> c.equalsIgnoreCase(curso1) || c.equalsIgnoreCase(curso2))
                        .flatMap(c -> cursos.get(c).stream())
                        .filter(e -> cursos.get(curso1).contains(e) && cursos.get(curso2).contains(e)).toList())
                .orElseThrow(() -> new EstudianteException("No se han encontrado resultados"));
    }

    // Hacemos un método para obtener los estudiantes que estén en dos cursos a la vez (forma difícil)
    public List<Estudiante> estudiantesCoincidentesEnCursosV2(String curso1, String curso2) throws EstudianteException {
        return Optional.of(cursos.keySet().stream().filter(c -> c.equalsIgnoreCase(curso1))
                .flatMap(c -> {
                    List<Estudiante> estudiantes = new ArrayList<>();
                    Iterator<Estudiante> it = cursos.get(curso1).iterator();
                    while (it.hasNext()) {
                        Iterator<Estudiante> it2 = cursos.get(curso2).iterator();
                        Estudiante estudiante1 = it.next();
                        while (it2.hasNext()) {
                            Estudiante estudiante2 = it2.next();
                            if (estudiante1.equals(estudiante2)) {
                                estudiantes.add(estudiante1);
                            }
                        }
                    }
                    return estudiantes.stream();
                }).toList()).orElseThrow(() -> new EstudianteException("No se encuentran resultados"));
    }
}