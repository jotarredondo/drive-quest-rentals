package com.duoc.model.vehiculos;

import java.util.Random;

import static com.duoc.constants.AppConstants.LETRAS;

public abstract class Vehiculo {
    private static int contador = 1;

    protected int id;
    protected String tipo;
    protected String marca;
    protected String modelo;
    protected String patente;
    protected int year;
    protected int diasArriendo;
    protected boolean disponible;

    public Vehiculo() {}

    public Vehiculo(String tipo, String marca, String modelo, int year) {
        this.id = contador++;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.patente = generarPatenteUnica();
        this.year = year;
        this.diasArriendo = 0;
        this.disponible = true;
    }

    public abstract void mostrarDatos();

    protected void mostrarDatosBase() {
        System.out.println("Codigo Vehiculo: " + this.id);
        System.out.println("Tipo : " + tipo);
        System.out.println("Patente : " + patente);
        System.out.println("Marca : " + marca);
        System.out.println("Modelo : " + modelo);
        System.out.println("Año : " + year);
        System.out.println("Disponible : " + disponible);
    }

    public String generarPatenteUnica() {
        String letras = LETRAS;
        Random random = new Random();
        char letra1 = letras.charAt(random.nextInt(letras.length()));
        char letra2 = letras.charAt(random.nextInt(letras.length()));
        int numero = 1000 + random.nextInt(9000);
        return String.valueOf(letra1).concat(String.valueOf(letra2).concat(String.valueOf(numero)));
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDiasArriendo() {
        return diasArriendo;
    }

    public void setDiasArriendo(int diasArriendo) {
        this.diasArriendo = diasArriendo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
