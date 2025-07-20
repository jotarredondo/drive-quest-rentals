package com.duoc.model.vehiculos;

import static com.duoc.model.vehiculos.TipoVehiculo.CARGA;

public class Carga extends Vehiculo {

    private double capacidadCarga;

    public Carga(String marca, String modelo, int year, double capacidadCarga) {
        super(String.valueOf(CARGA), marca, modelo, year);
        this.capacidadCarga = capacidadCarga;
    }

    public Carga(double capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(double capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    @Override
    public void mostrarDatos() {
        System.out.println("Vehículo de Carga :");
        mostrarDatosBase();
        System.out.println("Capacidad de carga : " + capacidadCarga + " kg");
        System.out.println("-------------------");
    }
}
