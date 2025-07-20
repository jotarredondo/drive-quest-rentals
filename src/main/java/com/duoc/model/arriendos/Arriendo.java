package com.duoc.model.arriendos;

import com.duoc.model.vehiculos.Vehiculo;

import java.util.Random;

public class Arriendo {

    private String id;
    private String rut;
    private String nombre;
    private Vehiculo vehiculo;
    private Boleta boleta;

    public Arriendo() {}

    public Arriendo(String rut, Boleta boleta, Vehiculo vehiculo, String nombre) {
        this.id = String.valueOf(100000 + new Random().nextInt(900000));
        this.rut = rut;
        this.boleta = boleta;
        this.vehiculo = vehiculo;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Boleta getBoleta() {
        return boleta;
    }

    public void setBoleta(Boleta boleta) {
        this.boleta = boleta;
    }
}
