package Ejercicio3;

import java.time.Year;

public class Dispositivo {

    // Creamos los atributos
    private String modelo;
    private String marca;
    private Year anoFabricacion;

    // Creamos el constructor
    public Dispositivo(String modelo, String marca, Year anoFabricacion) throws DispositivoExcepcion {
        this.modelo = modelo;
        this.marca = marca;
        setAnoFabricacion(anoFabricacion);
    }

    // Creamos los get y set
    public String getModelo() {
        return modelo;
    }

    private void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    private void setMarca(String marca) {
        this.marca = marca;
    }

    public Year getAnoFabricacion() {
        return anoFabricacion;
    }

    private void setAnoFabricacion(Year anoFabricacion) throws DispositivoExcepcion {
        if (anoFabricacion.isAfter(Year.now())) {
            throw new DispositivoExcepcion("El año no puede ser mayor que el año actual");
        }
        this.anoFabricacion = anoFabricacion;
    }
}