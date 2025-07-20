package com.duoc.service.impl;

import com.duoc.model.arriendos.Arriendo;
import com.duoc.model.arriendos.Boleta;
import com.duoc.model.vehiculos.Vehiculo;
import com.duoc.service.GestionArriendos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionArriendosImpl implements GestionArriendos {

    public GestionArriendosImpl(GestionVehiculosImpl vehiculos) {
        this.vehiculos = vehiculos;
    }

    private final GestionVehiculosImpl vehiculos;
    private final List<Arriendo> listaArriendos = new ArrayList<>();

    @Override
    public void crearArriendo(Scanner sc) {
        List<Vehiculo> lista = vehiculos.getListaVehiculos();
        try {
            System.out.println("Ingrse rut del cliente : ");
            String rut = sc.nextLine();
            System.out.println("Ingrse nombre y apellido del cliente : ");
            String nombre = sc.nextLine();
            Vehiculo vehiculoSeleccionado = null;
            int opcion;
            do {
                vehiculosDisponibles(lista);
                System.out.println("Ingrese codigo de vehiculo para arrendar : ");
                opcion = sc.nextInt();
                sc.nextLine();
                for (Vehiculo v : lista) {
                    if (v.getId() == opcion && v.isDisponible()) {
                        vehiculoSeleccionado = v;
                        break;
                    }
                }
                if (vehiculoSeleccionado == null) {
                    System.out.println("ID inválido o vehículo no disponible. Intente nuevamente.");
                }
            } while (vehiculoSeleccionado == null);
            System.out.println("Ingrese días de arriendo del vehiculo : ");
            int dias = sc.nextInt();
            sc.nextLine();
            vehiculoSeleccionado.setDiasArriendo(dias);
            Boleta boleta = new Boleta(vehiculoSeleccionado);
            Arriendo arriendo = new Arriendo(rut, boleta, vehiculoSeleccionado, nombre);
            listaArriendos.add(arriendo);
            vehiculoSeleccionado.setDisponible(false);
            System.out.println("Arriendo N° " + arriendo.getId() + " creado exitosamente");
            boleta.mostrarDetalleBoleta();
        } catch (Exception e) {
            System.out.println("Error al crear arriendo: " + e.getMessage());
        }
    }

    @Override
    public void listarArriendos() {
        if (listaArriendos.isEmpty()) {
            System.out.println("No existen registros de arriendos");
        } else {
            for (Arriendo arriendo : listaArriendos) {
                if (arriendo.getVehiculo().getDiasArriendo() >= 7) {
                    arriendo.getVehiculo().mostrarDatos();
                }
            }
        }
    }

    @Override
    public void mostrarBoleta(Scanner sc) {
        if (listaArriendos.isEmpty()) {
            System.out.println("No existen registros de boletas.");
            return;
        }
        System.out.println("Ingrese RUT del cliente o N° de arriendo:");
        String opcion = sc.nextLine().trim();
        boolean encontrado = false;
        for (Arriendo arriendo : listaArriendos) {
            if (String.valueOf(arriendo.getId()).equals(opcion) || arriendo.getRut().equalsIgnoreCase(opcion)) {
                arriendo.getBoleta().mostrarDetalleBoleta();
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontró ninguna boleta con ese ID o RUT.");
        }
    }

    @Override
    public void exportarArriendosAExcel() {
        if (listaArriendos.isEmpty()) {
            System.out.println("No hay arriendos para exportar.");
            return;
        }
        File carpeta = new File("data");
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
        File archivo = new File(carpeta, "arriendos.csv");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            bw.write("ID,RUT,Nombre,Patente,Marca,Modelo,Días Arriendo,Total a Pagar");
            bw.newLine();
            for (Arriendo a : listaArriendos) {
                Vehiculo v = a.getVehiculo();
                Boleta b = a.getBoleta();
                bw.write(a.getId() + "," +
                        a.getRut() + "," +
                        a.getNombre() + "," +
                        v.getPatente() + "," +
                        v.getMarca() + "," +
                        v.getModelo() + "," +
                        v.getDiasArriendo() + "," +
                        String.format("%.2f", b.getTotal()));
                bw.newLine();
            }
            System.out.println("Arriendos exportados exitosamente a 'data/arriendos.csv'.");
        } catch (IOException e) {
            System.out.println("Error al exportar arriendos: " + e.getMessage());
        }
    }

    private void vehiculosDisponibles(List<Vehiculo> lista) {
        for (Vehiculo vehiculo : lista) {
            if (vehiculo.isDisponible()) {
                vehiculo.mostrarDatos();
            }
        }
    }
}
