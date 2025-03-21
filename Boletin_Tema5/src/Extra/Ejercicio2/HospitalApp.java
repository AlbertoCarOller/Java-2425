package Extra.Ejercicio2;

public class HospitalApp {
    public static void main(String[] args) {
        try {
            // Creamos un hospital
            Hospital hospital = new Hospital("Chelu´s hospital");
            // Creamos varios pacientes
            Paciente paciente1 = new Paciente("65467546W", "Chelu", 27, 3);
            Paciente paciente2 = new Paciente("94175084T", "Atisbedo", 100, 1);
            Paciente paciente3 = new Paciente("72081406X", "Sensei Chelu", 34, 5);

            hospital.anadirPaciente(paciente1);
            hospital.anadirPaciente(paciente2);
            hospital.anadirPaciente(paciente3);
            hospital.mostrarDatosEstadisticos();

            hospital.atenderPaciente();
            hospital.mostrarDatosEstadisticos();

        } catch (PacienteException e) {
            System.out.println(e.getMessage());
        }
    }
}