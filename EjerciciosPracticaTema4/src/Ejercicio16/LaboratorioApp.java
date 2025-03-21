package Ejercicio16;

public class LaboratorioApp {
    public static void main(String[] args) {
        try {
            Laboratorio laboratorio = new Laboratorio("Laboratorio Chelu");
            Equipo equipo1 = new Equipo("fsdfsd", 20);
            EquipoAutorizable equipoAutorizable1 = new EquipoAutorizable("fdhgfh", 40, false);
            EquipoAutorizableMantenible equipoAutorizableMantenible1 = new EquipoAutorizableMantenible("uyjhg", 10,
                    false, false);
            EquipoMantenible equipoMantenible = new EquipoMantenible("bvchkh", 60, false);

            equipoMantenible.reparar();

            equipoAutorizableMantenible1.autorizar();
            equipoAutorizableMantenible1.reparar();

            equipoAutorizable1.autorizar();

            equipoAutorizable1.autorizar();

            laboratorio.registrarEquipo(equipo1);
            laboratorio.registrarEquipo(equipoAutorizable1);
            laboratorio.registrarEquipo(equipoAutorizableMantenible1);
            laboratorio.registrarEquipo(equipoMantenible);

        } catch (EquipoException e) {
            System.out.println(e.getMessage());
        }
    }
}