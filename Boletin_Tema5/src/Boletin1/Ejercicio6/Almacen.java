package Boletin1.Ejercicio6;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Almacen {

    // Creamos los atributos
    Map<Caja, List<Cliente>> cajas;

    // Creamos el constructor
    public Almacen() {
        this.cajas = new HashMap<>(20);
    }

    // Hacemos los get y set
    public Map<Caja, List<Cliente>> getCajas() {
        return cajas;
    }

    private void setCajas(Map<Caja, List<Cliente>> cajas) {
        this.cajas = cajas;
    }

    // Hacemos el toString
    @Override
    public String toString() {
        return String.format("Las cajas son: %s", this.cajas);
    }

    // Hacemos un método para abrir una caja
    public void abrirCaja(int numCaja) throws AlmacenException {
        boolean ningunaCajaAAbrir = true;
        for (Caja caja : cajas.keySet()) {
            if (caja.getNumCaja() == numCaja && !caja.isAbierta()) {
                caja.setAbierta(true);
                ningunaCajaAAbrir = false;
                break;
            }
        }
        if (ningunaCajaAAbrir) {
            throw new AlmacenException("No se puede abrir ninguna caja");
        }
    }

    // Hacemos un método para cerrar una caja
    public void cerrarCaja(int numCaja) throws AlmacenException {
        boolean ningunaCajaACerrar = true;
        for (Caja caja : cajas.keySet()) {
            if (caja.getNumCaja() == numCaja && cajas.get(caja).isEmpty() && caja.isAbierta()) {
                caja.setAbierta(false);
                ningunaCajaACerrar = false;
            }
        }
        if (ningunaCajaACerrar) {
            throw new AlmacenException("No se puede cerrar ninguna caja");
        }
    }

    // Hacemos un método para añadir un cliente
    public String anadirCliente() throws AlmacenException {
        // Guardamos la caja que tenga el menor número de clientes
        Caja cajaSizeMenor = null;
        // Guardamos la caja con el número de caja menor
        Caja cajaNumMenor = null;
        // Guardamos el menor número de clientes
        int sizeMenor = 0;
        boolean algunaAbierta = false;
        boolean primeraVez = true;
        StringBuilder sb = new StringBuilder();
        // Recorremos las cajas
        for (Caja caja : cajas.keySet()) {
            // Si la caja está abierta entra
            if (caja.isAbierta()) {
                algunaAbierta = true;

                /* Hacemos esto para darle un valor inicial tamaño menor, ya que si lo dejamos en 0 nunca entrará en
                 * el segundo if, ya que sizeMenor simpre tendrá el mismo valor, entonces le damos el valor del número
                 * de clientes de la primera caja, la primera caja es donde comenzarán a guardarse los clientes */
                if (primeraVez) {
                    primeraVez = false;
                    sizeMenor = cajas.get(caja).size();
                    cajaSizeMenor = caja;
                }

                /* Si sizeMenor es mayor que la caja que está viendo, le damos el valor de la que tiene menos clientes
                 * aparte guardamos el número de clientes */
                if (sizeMenor > cajas.get(caja).size()) {
                    sizeMenor = cajas.get(caja).size();
                    cajaSizeMenor = caja;
                    // Le ponemos null por si más adelante encontrara una caja con menos clientes
                    cajaNumMenor = null;

                    /* Si el número de clientes de la caja con menos clientes es igual que la de alguna caja
                     * y el número de la caja es menor que la de cajaSizeMenor guardamos la caja actual */
                } else if (sizeMenor == cajas.get(caja).size() && cajaSizeMenor.getNumCaja() > caja.getNumCaja()) {
                    cajaNumMenor = caja;
                }
            }
        }

        // Si no había ninguna caja abierta salta una excepción
        if (!algunaAbierta) {
            throw new AlmacenException("No hay ninguna caja abierta");
        }

        // Si cajaNumMenor es null quiere decir que la caja con menos clientes está guardada en cajaSizeMenor
        if (cajaNumMenor == null) {
            Cliente cliente1 = new Cliente();
            cajas.get(cajaSizeMenor).add(cliente1);
            sb.append("Es usted el cliente número ").append(cliente1.getNumeroCliente()).append
                    (" y debe ir a la caja número ").append(cajaSizeMenor.getNumCaja());

            // Si no es así, cajaNumMenor tiene la caja con el número menor de caja
        } else {
            Cliente cliente2 = new Cliente();
            cajas.get(cajaNumMenor).add(cliente2);
            sb.append("Es usted el cliente número ").append(cliente2.getNumeroCliente()).append
                    (" y debe ir a la caja número ").append(cajaNumMenor.getNumCaja());
        }
        // Devolvemos el mensaje
        return sb.toString();
    }

    // Hacemos un método para atender a un cliente
    public String atenderCliente(int numCaja) throws AlmacenException {
        // Guardamos el menor número del cliente
        int numClienteMenor = 0;
        // Guardamos el índice del cliente en la lista
        int indiceCliente = -1;
        boolean primeraVez = true;
        boolean algunaCaja = false;
        // Guardamos la caja que ha coincidido con el número de la caja pasada por parámetros
        Caja cajaAtender = null;
        StringBuilder sb = new StringBuilder();
        // Recorremos las cajas
        for (Caja caja : cajas.keySet()) {
            // Si se encuentra la caja con ese número entra
            if (caja.getNumCaja() == numCaja) {
                // Si la caja está abierta y no está vacía entra
                if (caja.isAbierta() && !cajas.get(caja).isEmpty()) {
                    // Guardamos la caja
                    cajaAtender = caja;
                    algunaCaja = true;
                    // Recorremos la lista de clientes de la caja
                    for (int i = 0; i < cajas.get(caja).size(); i++) {
                        // Hacemos esto para guardar valores para algunas variables
                        if (primeraVez) {
                            numClienteMenor = cajas.get(caja).get(i).getNumeroCliente();
                            indiceCliente = i;
                            primeraVez = false;
                        }
                        // Si encuentra un cliente con el número de cliente menor que numClienteMenor, se guarda
                        if (numClienteMenor > cajas.get(caja).get(i).getNumeroCliente()) {
                            numClienteMenor = cajas.get(caja).get(i).getNumeroCliente();
                            // Guardamos el índice de donde se encuentra el cliente
                            indiceCliente = i;
                        }
                    }
                }
            }
        }
        // Si no se ha encontrado la caja o la caja está vacía o está cerrada salta la excepción
        if (!algunaCaja) {
            throw new AlmacenException("No se ha podido acceder a la caja");
        }
        // Si no salta la excepción eliminamos al cliente
        sb.append("Se ha atendido al cliente con número ").append(cajas.get(cajaAtender).get(indiceCliente).getNumeroCliente());
        cajas.get(cajaAtender).remove(indiceCliente);
        return sb.toString();
    }
}