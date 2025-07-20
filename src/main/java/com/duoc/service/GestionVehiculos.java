package com.duoc.service;

import java.util.Scanner;

public interface GestionVehiculos {

    void crearVehiculoManual(Scanner sc);
    void listarVehiculos();
    void listarVehiculosPorTipo(Scanner sc);
    void cargarVehiculosDesdeCSV();
}
