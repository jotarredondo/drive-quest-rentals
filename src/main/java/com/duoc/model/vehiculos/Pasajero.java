package com.duoc.model.vehiculos;

import static com.duoc.model.vehiculos.TipoVehiculo.PASAJEROS;

public class Pasajero extends Vehiculo {

    private int maxPasajeros;

    public Pasajero(int maxPasajeros) {
        this.maxPasajeros = maxPasajeros;
    }

    public Pasajero(String marca, String modelo, int year, int maxPasajeros) {
        super(String.valueOf(PASAJEROS), marca, modelo, year);
        this.maxPasajeros = maxPasajeros;
    }

    public int getMaxPasajeros() {
        return maxPasajeros;
    }

    public void setMaxPasajeros(int maxPasajeros) {
        this.maxPasajeros = maxPasajeros;
    }

    @Override
    public void mostrarDatos() {
        System.out.println("Vehículo de Pasajeros :");
        mostrarDatosBase();
        System.out.println("Capacidad de Pasajeros : " + maxPasajeros );
        System.out.println("-------------------");
    }
}
