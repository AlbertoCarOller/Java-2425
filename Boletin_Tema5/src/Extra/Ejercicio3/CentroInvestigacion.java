package Extra.Ejercicio3;

import java.time.LocalDate;
import java.util.*;

public class CentroInvestigacion {
    // Creamos los atributos
    private String nombre;
    private Map<String, List<Avistamiento>> especies;

    // Creamos el constructor
    public CentroInvestigacion(String nombre) {
        this.especies = new HashMap<>();
        this.nombre = nombre;
    }

    // Creamos los get y set
    public Map<String, List<Avistamiento>> getEspecies() {
        return especies;
    }

    private void setEspecies(Map<String, List<Avistamiento>> especies) {
        this.especies = especies;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Creamos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CentroInvestigacion that = (CentroInvestigacion) o;
        return Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Creamos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %s, Especies: %s", this.nombre, this.especies);
    }

    // Hacemos un método para añadir una especie
    public void anadirEspecie(String nombreEspecie) throws AvistamientoException {
        if (!Character.isUpperCase(nombreEspecie.charAt(0))) {
            throw new AvistamientoException("La primera letra del nombre de la especia debe estar en mayúsculas");
        }
        boolean esMismo = false;
        for (String especie : especies.keySet()) {
            if (nombreEspecie.equalsIgnoreCase(especie)) {
                esMismo = true;
            }
        }
        if (esMismo) {
            throw new AvistamientoException("La especie ya está añadida");
        }
        especies.put(nombreEspecie, new ArrayList<>());
    }

    // Hacemos un método para añadir un avistamiento
    public void anadirAvistamiento(Avistamiento avistamiento) throws AvistamientoException {
        if (especies.isEmpty()) {
            throw new AvistamientoException("No hay especies todavía añadidas");
        }
        boolean encontrada = false;
        for (String especie : especies.keySet()) {
            if (avistamiento.getNombreEspecie().equalsIgnoreCase(especie)) {
                encontrada = true;
                especies.get(especie).add(avistamiento);
            }
        }
        if (!encontrada) {
            throw new AvistamientoException("No se ha encontrado la especie");
        }
    }

    // Hacemos un método que va a devolver una lista de las especies ordenadas alfabéticamente
    public Set<String> ordenarAlfabeticamente() throws AvistamientoException {
        if (especies.isEmpty()) {
            throw new AvistamientoException("No hay especies");
        }
        return new TreeSet<>(especies.keySet());
    }

    // Hacemos un método que va a devolver una lista de las especies que han sido avistadas en una ubicación determinada
    public List<String> avistamientosEnUbicacion(String ubicacion) throws AvistamientoException {
        boolean encontrado = false;
        List<String> especiesEnUbicacion = new LinkedList<>();
        for (String especie : especies.keySet()) {
            for (int i = 0; i < especies.get(especie).size(); i++) {
                if (especies.get(especie).get(i).getLugar().equalsIgnoreCase(ubicacion)) {
                    encontrado = true;
                    especiesEnUbicacion.add(especie);
                    break;
                }
            }
        }
        if (!encontrado) {
            throw new AvistamientoException("No se ha encontrado ningún avistamiento en esa ubicación");
        }
        return especiesEnUbicacion;
    }

    // Hacemos un método que va a devolver la especie con más avistamientos
    public String especieConMasAvistamientos() throws AvistamientoException {
        if (especies.isEmpty()) {
            throw new AvistamientoException("No hay especies registradas");
        }
        String especieMayor = null;
        int numAvistamientosMayor = -1;
        for (String especie : especies.keySet()) {
            if (numAvistamientosMayor < especies.get(especie).size()) {
                numAvistamientosMayor = especies.get(especie).size();
                especieMayor = especie;
            }
            if (numAvistamientosMayor == especies.get(especie).size()) {
                if (especies.get(especie).isEmpty() || especies.get(especieMayor).isEmpty()) {
                    continue;
                }
                LocalDate fechaAntes1 = null;
                boolean primeraVez1 = true;
                for (int i = 0; i < especies.get(especie).size(); i++) {
                    if (primeraVez1) {
                        primeraVez1 = false;
                        fechaAntes1 = especies.get(especie).get(i).getFecha();
                    }
                    if (fechaAntes1.isAfter(especies.get(especie).get(i).getFecha())) {
                        fechaAntes1 = especies.get(especie).get(i).getFecha();
                    }
                }
                LocalDate fechaAntes2 = null;
                boolean primeraVez2 = true;
                for (int i = 0; i < especies.get(especieMayor).size(); i++) {
                    if (primeraVez2) {
                        primeraVez2 = false;
                        fechaAntes2 = especies.get(especieMayor).get(i).getFecha();
                    }
                    if (fechaAntes2.isAfter(especies.get(especieMayor).get(i).getFecha())) {
                        fechaAntes2 = especies.get(especieMayor).get(i).getFecha();
                    }
                }
                if (fechaAntes1 != null && fechaAntes2 != null) {
                    if (fechaAntes1.isBefore(fechaAntes2)) {
                        especieMayor = especie;
                    }
                }
            }
        }
        return especieMayor;
    }
}