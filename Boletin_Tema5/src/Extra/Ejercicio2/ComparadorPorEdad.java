package Extra.Ejercicio2;

import java.util.Comparator;

public class ComparadorPorEdad implements Comparator<Paciente> {

    // Implementamos el compare
    @Override
    public int compare(Paciente o1, Paciente o2) {
        return Integer.compare(o1.getEdad(), o2.getEdad());
    }
}