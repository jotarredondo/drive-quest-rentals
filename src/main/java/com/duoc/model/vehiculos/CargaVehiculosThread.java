package com.duoc.model.vehiculos;

import com.duoc.service.impl.GestionVehiculosImpl;

public class CargaVehiculosThread extends Thread {
    private final GestionVehiculosImpl servicio;

    public CargaVehiculosThread(GestionVehiculosImpl servicio) {
        this.servicio = servicio;
    }

    @Override
    public void run() {
        System.out.println("Cargando vehículos desde CSV en segundo plano...");
        servicio.cargarVehiculosDesdeCSV();
    }
}
