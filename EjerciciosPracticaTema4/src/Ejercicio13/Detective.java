package Ejercicio13;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

public abstract class Detective implements Solucionador {

    // Creamos los atributos
    private String nombre;
    private LocalDate fechaIngreso;
    private Caso[] casosAsignados;
    private static final int MAX_CASOS = 6;
    private int casosResueltos;
    private int anosTrascurridos;

    // Creamos el constructor
    public Detective(String nombre, LocalDate fechaIngreso) {
        this.nombre = nombre;
        this.fechaIngreso = fechaIngreso;
        this.casosAsignados = new Caso[MAX_CASOS];
        this.casosResueltos = 0;
        this.anosTrascurridos = 0;
    }

    // Creamos los get y set
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    private void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Caso[] getCasosAsignados() {
        return casosAsignados;
    }

    private void setCasosAsignados(Caso[] casosAsignados) {
        this.casosAsignados = casosAsignados;
    }

    public int getCasosResueltos() {
        return casosResueltos;
    }

    protected void setCasosResueltos(int casosResueltos) {
        this.casosResueltos = casosResueltos;
    }

    public int getAnosTrascurridos() {
        return anosTrascurridos;
    }

    protected void setAnosTrascurridos(int anosTrascurridos) {
        this.anosTrascurridos = anosTrascurridos;
    }

    // Creamos un equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Detective detective = (Detective) o;
        return Objects.equals(nombre, detective.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }

    // Creamos un toString
    @Override
    public String toString() {
        return String.format("Nombre: %S, Fecha ingreso: %s, Casos asignados: %s, Número de casos resueltos: %d, Años trascurridos: %s", this.nombre,
                this.fechaIngreso.format(DateTimeFormatter.ofPattern("yy/MM/dd")), Arrays.toString(this.casosAsignados), this.casosResueltos,
                this.anosTrascurridos);
    }

    @Override
    public void resolver(Caso caso, Academia academia) throws DetectiveException {
        if (academia.buscarDetective(this.getNombre()) == null) {
            throw new DetectiveException("No puedes resolver ningún caso, no formas parte de la academia");
        }
        boolean encontrado = false;
        this.anosTrascurridos++;
        for (int i = 0; i < casosAsignados.length; i++) {
            if (caso.equals(casosAsignados[i])) {
                encontrado = true;
                if (casosAsignados[i].isResuelto()) {
                    casosAsignados[i] = null;
                    throw new DetectiveException("El caso ya fue resuelto, eliminando...");
                }
                if (this instanceof DetectiveExperto) {
                    System.out.println(this.nombre + " ha resuelto el caso " + casosAsignados[i].getNombre());

                } else {
                    if (casosAsignados[i].getNivelDificultad() != Dificultad.MEDIO && casosAsignados[i].getNivelDificultad() != Dificultad.BAJO) {
                        throw new DetectiveException("No puedes resolver un caso que sea de nivel alto");
                    }
                }
                casosAsignados[i].setResuelto(true);
                casosAsignados[i] = null;
                this.casosResueltos++;
            }
        }
        if (!encontrado) {
            throw new DetectiveException("No se ha encontrado el caso");
        }
    }
}