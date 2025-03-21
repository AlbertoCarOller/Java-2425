package Ejercicio12;

public class EstacionApp {
    public static void main(String[] args) {
        try {
            Estacion estacion = new Estacion("Estación new Chelu´s");
            IngenerioEnergia ingenerioEnergia1 = new IngenerioEnergia("Sensei Vallejo", 55);
            IngenieroMaquinaria ingenieroMaquinaria1 = new IngenieroMaquinaria("Sensei Chlu", 39);
            IngenieroBiotecnologia ingenieroBiotecnologia1 = new IngenieroBiotecnologia("Atisbedo", 20);
            estacion.anadirIngeniero(ingenerioEnergia1);
            estacion.anadirIngeniero(ingenieroMaquinaria1);
            estacion.anadirIngeniero(ingenieroBiotecnologia1);
            ModuloCultivo moduloCultivo1 = new ModuloCultivo("Modulo cultivo Chelu Alfa");
            ModuloInvestigacion moduloInvestigacion1 = new ModuloInvestigacion("Modulo investigación Vallejo Beta");
            ModuloManufactura moduloManufactura = new ModuloManufactura("Modulo manufactura Atisbedo Alfa");
            estacion.anadirModulo(moduloManufactura);
            estacion.anadirModulo(moduloInvestigacion1);
            estacion.anadirModulo(moduloCultivo1);
            estacion.periodo();
            estacion.periodo();
            estacion.periodo();
            ingenerioEnergia1.mejorar(moduloInvestigacion1, estacion);
            estacion.periodo();
            estacion.periodo();
            ingenerioEnergia1.reparar(moduloInvestigacion1, estacion);
            estacion.periodo();
            estacion.periodo();
            estacion.periodo();

        } catch (ModuloException e) {
            System.out.println(e.getMessage());
        }
    }
}