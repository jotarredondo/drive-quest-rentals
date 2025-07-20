package com.duoc.service.impl;

import com.duoc.main.Main;
import com.duoc.model.vehiculos.Carga;
import com.duoc.model.vehiculos.Pasajero;
import com.duoc.model.vehiculos.Vehiculo;
import com.duoc.service.GestionVehiculos;

import java.io.*;
import java.util.*;

public class GestionVehiculosImpl implements GestionVehiculos {

    private final Map<String, Vehiculo> listaVehiculos = new HashMap<>();

    @Override
    public void  crearVehiculoManual(Scanner sc) {
        try {
            int opcion, year, diasArriendo, capacidadCarga, maxPasajeros;
            String marca, modelo;
            do {
                System.out.println("Presione 1 para agregar un vehiculo de carga ");
                System.out.println("Presione 2 para agregar un vehiculo de pasajeros ");
                opcion = sc.nextInt();
            } while (opcion > 2 || opcion < 1);
            sc.nextLine();
            System.out.println("Ingrese marca del vehiculo: ");
            marca = sc.nextLine();
            System.out.println("Ingrese modelo del vehiculo: ");
            modelo = sc.nextLine();
            do {
                System.out.println("Ingrese año del vehiculo : ");
                year = sc.nextInt();
            } while (year < 1940 || year > 2025);
            if (opcion == 1) {
                System.out.println("Ingrese capacidad máxima de carga del vehiculo (KG): ");
                capacidadCarga = sc.nextInt();
                Carga carga = new Carga(marca,modelo, year,capacidadCarga);
                agregarVehiculo(carga);
                System.out.println("Se ha creado exitosamente");
                carga.mostrarDatos();
            } else {
                System.out.println("Ingrese cantidad máxima de pasajeros : ");
                maxPasajeros = sc.nextInt();
                Pasajero pasajero = new Pasajero(marca, modelo,year, maxPasajeros);
                agregarVehiculo(pasajero);
                System.out.println("Se ha creado exitosamente");
                pasajero.mostrarDatos();
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void listarVehiculos() {
        if (listaVehiculos.isEmpty()) {
            System.out.println("No hay vehículos registrados.");
        } else {
            for (Vehiculo v : listaVehiculos.values()) {
                v.mostrarDatos();
            }
        }
    }

    @Override
    public void listarVehiculosPorTipo(Scanner sc) {
        try {
            if (listaVehiculos.isEmpty()) {
                System.out.println("No hay vehículos registrados.");
            } else {
                int opcion;
                do {
                    System.out.println("Ingrese 1 para listar vehiculos de carga : ");
                    System.out.println("Ingrese 2 para listar vehiculos de pasajeros : ");
                    opcion = sc.nextInt();
                } while (opcion > 2 || opcion < 1);
                if (opcion == 1) {
                    for (Vehiculo carga : listaVehiculos.values()) {
                        if (carga instanceof Carga){
                            carga.mostrarDatos();
                        }
                    }
                } else {
                    for (Vehiculo pasajero : listaVehiculos.values()) {
                        if (pasajero instanceof Pasajero) {
                            pasajero.mostrarDatos();
                        }
                    }
                }
                sc.nextLine();
            }
        } catch (RuntimeException e) {
            System.out.println("Error inesperado: " + e.getMessage());
            sc.nextLine();
        }
    }

    public void cargarVehiculosDesdeCSV() {
        InputStream rutaArchivo = Main.class.getClassLoader().getResourceAsStream("vehiculos.csv");

        if (rutaArchivo == null) {
            System.out.println("Archivo 'vehiculos.csv' no encontrado en recursos.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(rutaArchivo))) {
            String linea = br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length < 5) {
                    System.out.println("Línea inválida (faltan columnas): " + linea);
                    continue;
                }
                String tipo = datos[0].trim();
                String marca = datos[1].trim();
                String modelo = datos[2].trim();
                int year = Integer.parseInt(datos[3].trim());

                if (tipo.equalsIgnoreCase("Carga")) {
                    double capacidad = Double.parseDouble(datos[4].trim());
                    Carga carga = new Carga(marca, modelo, year, capacidad);
                    agregarVehiculo(carga);
                } else if (tipo.equalsIgnoreCase("Pasajero")) {
                    int maxPasajeros = Integer.parseInt(datos[4].trim());
                    Pasajero pasajero = new Pasajero(marca, modelo, year, maxPasajeros);
                    agregarVehiculo(pasajero);
                } else {
                    System.out.println("Tipo desconocido: " + tipo);
                }
            }
            System.out.println("Vehículos cargados exitosamente desde archivo CSV.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al cargar vehículos desde CSV: " + e.getMessage());
        }
    }




        private void agregarVehiculo(Vehiculo vehiculo) {
        String patente = vehiculo.getPatente();
        while (listaVehiculos.containsKey(patente)) {
            System.out.println("Patente duplicada detectada: " + patente + ". Generando una nueva...");
            patente = vehiculo.generarPatenteUnica();
            vehiculo.setPatente(patente);
        }
        listaVehiculos.put(patente, vehiculo);
    }

    public List<Vehiculo> getListaVehiculos() {
        return new ArrayList<>(listaVehiculos.values());
    }
}

