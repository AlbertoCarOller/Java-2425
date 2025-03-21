package Ejercicio14;

import utils.MiEntradaSalida;

import java.util.ArrayList;
import java.util.List;

public class LaboratorioApp {
    static List<Cientifico> cientificos = new ArrayList<>();
    static List<Laboratorio> laboratorios = new ArrayList<>();

    public static void main(String[] args) {
        int op;
        do {
            op = MiEntradaSalida.seleccionarOpcion("Elija una opción", new String[]{"Crear científico",
                    "Crear laboratorio reproducido", "Crear laboratorio alto secreto", "Crear experimento reproducido",
                    "Crear experimento confidencial", "Repetir experimento reproducido", "Salir"});
            try {
                switch (op) {
                    case 1:
                        String nombreCientifico = MiEntradaSalida.solicitarCadena("Introduce el nombre del científico");
                        int op1 = MiEntradaSalida.seleccionarOpcion("¿Está autorizado para experimentos de alto secreto?",
                                new String[]{"Sí", "No"});
                        boolean autorizado;
                        if (op1 == 1) {
                            autorizado = true;

                        } else {
                            autorizado = false;
                        }
                        anadirCientifico(new Cientifico(nombreCientifico, autorizado));
                        break;

                    case 2:
                        String nombreLaboratorioReproducido = MiEntradaSalida.solicitarCadena("Introduce el nombre del laboratorio");
                        anadirLaboratio(new LaboratorioReproducido(nombreLaboratorioReproducido));
                        break;

                    case 3:
                        String nombreLaboratorioAltoSecreto = MiEntradaSalida.solicitarCadena("Introduce el nombre del laboratorio");
                        anadirLaboratio(new LaboratorioAltoSecreto(nombreLaboratorioAltoSecreto));
                        break;

                    case 4:
                        String nombreLaboratorioReprocudidoABuscar = MiEntradaSalida.solicitarCadena("Introduce el nombre del laboratorio");
                        String nombreExperimentoReproducido = MiEntradaSalida.solicitarCadena("Introduce el nombre del experimento");
                        String nombreCientificoABuscar = MiEntradaSalida.solicitarCadena("Introduce el nombre del científico");
                        Cientifico cientificoBuscado = buscarCientifico(nombreCientificoABuscar);
                        if (cientificoBuscado == null) {
                            System.out.println("No se ha encontrado el científico");
                            break;
                        }
                        anadirExperimento(nombreLaboratorioReprocudidoABuscar, nombreExperimentoReproducido, cientificoBuscado, false);
                        break;

                    case 5:
                        String nombreLaboratorioAltoSecretoABuscar = MiEntradaSalida.solicitarCadena("Introduce el nombre del laboratorio");
                        String nombreExperimentoAltoSecreto = MiEntradaSalida.solicitarCadena("Introduce el nombre del experimento");
                        String nombreCientificoABuscar2 = MiEntradaSalida.solicitarCadena("Introduce el nombre del científico");
                        Cientifico cientificoBuscado2 = buscarCientifico(nombreCientificoABuscar2);
                        if (cientificoBuscado2 == null) {
                            System.out.println("No se ha encontrado el científico");
                            break;
                        }
                        anadirExperimento(nombreLaboratorioAltoSecretoABuscar, nombreExperimentoAltoSecreto, cientificoBuscado2, true);
                        break;

                    case 6:
                        String nombreLaboratorioABuscar = MiEntradaSalida.solicitarCadena("Introduce el nombre" +
                                " del laboratorio donde está el experimento");
                        int idExperimento = MiEntradaSalida.solicitarEnteroPositivo("Introduce el id del experimento");
                        repetirExperimento(nombreLaboratorioABuscar, idExperimento);
                        break;

                    case 7:
                        System.out.println("Hasta pronto");
                        break;

                    default:
                        System.out.println("No has seleccionado ninguna opción");
                        break;
                }
            } catch (CientificoException e) {
                System.out.println(e.getMessage());
            }
        } while (op != 7);
    }

    // Hacemos un método para añadir un científico a la lista de científicos
    public static void anadirCientifico(Cientifico cientifico) throws CientificoException {
        boolean esIgual = false;
        for (Cientifico cientificoRecorrido : cientificos) {
            if (cientifico.equals(cientificoRecorrido)) {
                esIgual = true;
            }
        }
        if (esIgual) {
            throw new CientificoException("El científico ya existe en la lista");
        }
        cientificos.add(cientifico);
    }

    // Hacemos un método para añadir un laboratorio a la lista
    public static void anadirLaboratio(Laboratorio laboratorio) throws CientificoException {
        boolean esIgual = false;
        for (Laboratorio laboratorioRecorrido : laboratorios) {
            if (laboratorio.equals(laboratorioRecorrido)) {
                esIgual = true;
            }
        }
        if (esIgual) {
            throw new CientificoException("El laboratorio ya existe en el sistema");
        }
        laboratorios.add(laboratorio);
    }

    // Hacemos un método para devolver un científico por su nombre
    public static Cientifico buscarCientifico(String nombreCientifico) {
        for (Cientifico cientifico : cientificos) {
            if (nombreCientifico.equalsIgnoreCase(cientifico.getNombre())) {
                return cientifico;
            }
        }
        return null;
    }

    // Hacemos un método para introducir un experimento a un laboratorio
    public static void anadirExperimento(String nombreLaboratorio, String nombreExperimento, Cientifico cientifico,
                                         boolean altoSecreto) throws CientificoException {
        boolean encontrado = false;
        for (Laboratorio laboratorio : laboratorios) {
            if (nombreLaboratorio.equalsIgnoreCase(laboratorio.getNombre())) {
                encontrado = true;
                if (!altoSecreto) {
                    laboratorio.anadirExperimento(new ExperimentoReproducido(nombreExperimento, cientifico));

                } else {
                    laboratorio.anadirExperimento(new ExperimentoConfidencial(nombreExperimento, cientifico));
                }
            }
        }
        if (!encontrado) {
            throw new CientificoException("No se ha encontrado el laboratorio");
        }
    }

    // Hacemos un método para buscar un experimento y lo repetimos
    public static void repetirExperimento(String nombreLaboratorio, int idExperimento) throws CientificoException {
        boolean encontradoLaboratorio = false;
        for (Laboratorio laboratorio : laboratorios) {
            if (nombreLaboratorio.equalsIgnoreCase(laboratorio.getNombre())) {
                encontradoLaboratorio = true;
                if (!(laboratorio instanceof LaboratorioReproducido)) {
                    throw new CientificoException("No se puede repetir un experimento si el laboratorio no es uno reproducido");
                }
                boolean encontradoExperimento = false;
                for (Experimento experimento : laboratorio.getExperimentos()) {
                    if (idExperimento == experimento.getId()) {
                        encontradoExperimento = true;
                        if (experimento instanceof ExperimentoReproducido experimentoReproducido) {
                            experimentoReproducido.reproducir();
                            break;

                        } else {
                            throw new CientificoException("No puedes repetir un experimento que no sea reproducible");
                        }
                    }
                }
                if (!encontradoExperimento) {
                    throw new CientificoException("No se ha encontrado el experimento");
                }
            }
            if (encontradoLaboratorio) {
                break;
            }
        }
        if (!encontradoLaboratorio) {
            throw new CientificoException("No se ha encontrado el laboratorio");
        }
    }
}