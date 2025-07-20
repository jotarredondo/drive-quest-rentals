package com.duoc.main;

import com.duoc.model.vehiculos.CargaVehiculosThread;
import com.duoc.service.impl.GestionArriendosImpl;
import com.duoc.service.impl.GestionVehiculosImpl;

import java.util.Scanner;

import static com.duoc.utils.Util.mostrarMenu;

public class Main {
    public static void main(String[] args) {

        GestionVehiculosImpl vehiculosService = new GestionVehiculosImpl();
        GestionArriendosImpl arriendoService = new GestionArriendosImpl(vehiculosService);

        Thread cargaThread = new CargaVehiculosThread(vehiculosService);
        cargaThread.start();

        try {
            cargaThread.join();
        } catch (InterruptedException e) {
            System.out.println("Error al esperar carga de vehículos: " + e.getMessage());
        }

        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            mostrarMenu();
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1 -> vehiculosService.crearVehiculoManual(sc);
                case 2 -> vehiculosService.listarVehiculos();
                case 3 -> vehiculosService.listarVehiculosPorTipo(sc);
                case 4 -> arriendoService.crearArriendo(sc);
                case 5 -> arriendoService.listarArriendos();
                case 6 -> arriendoService.mostrarBoleta(sc);
                case 7 -> arriendoService.exportarArriendosAExcel();
                case 8 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opcion no valida");
            }
        } while (opcion != 8);
        sc.close();
    }
}