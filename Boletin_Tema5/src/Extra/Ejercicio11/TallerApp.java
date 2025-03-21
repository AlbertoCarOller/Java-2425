package Extra.Ejercicio11;

import java.time.LocalDate;

public class TallerApp {
    public static void main(String[] args) {
        try {
            // Creamos el taller
            Taller taller = new Taller("Chelu´s taller", LocalDate.of(2020, 5, 12));
            // Creamos los participantes
            Participante participante = new Participante("Chelu", "chelu@gmail.com", 604710892);
            Participante participante1 = new Participante("Carles Xavier", "xavier@outlook.com", 901296350);
            Participante participante2 = new Participante("Atisbedo", "atisbedobedo@gmail.com", 670981560);
            Participante participante3 = new Participante("Villalba", "villalba@hotmail.com", 922649107);
            // Añadimos a los participantes
            taller.registrarParticipante(participante);
            taller.registrarParticipante(participante1);
            taller.registrarParticipante(participante2);
            taller.registrarParticipante(participante3);
            // Llamamos a los diferentes métodos
            System.out.println(taller.buscarParticipantesPorLetra('C'));
            System.out.println(taller.buscarParticipante("chelu@gmail.com"));
            System.out.println(taller.participantesOrdenadosAlfabeticamente());
            System.out.println(taller.telefonosPorDominio("@hotmail.com"));

        } catch (ParticipanteException e) {
            System.out.println(e.getMessage());
        }
    }
}