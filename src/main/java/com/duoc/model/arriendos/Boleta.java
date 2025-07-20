package com.duoc.model.arriendos;

import com.duoc.model.vehiculos.Carga;
import com.duoc.model.vehiculos.Pasajero;
import com.duoc.model.vehiculos.Vehiculo;

public class Boleta {
    private double total;
    private double subtotal;
    private double impuesto;
    private double descuento;

    public Boleta() {}

    public Boleta(Vehiculo vehiculo) {
        int dias = vehiculo.getDiasArriendo();

        if (vehiculo instanceof Carga) {
            subtotal = dias * 19999;
            descuento = subtotal * 0.07;
        } else if (vehiculo instanceof Pasajero) {
            subtotal = dias * 14999;
            descuento = subtotal * 0.12;
        }
        impuesto = subtotal * 0.19;
        total = (subtotal + impuesto) - descuento;
    }

    public double getTotal() {
        return total;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public double getDescuento() {
        return descuento;
    }

    public void mostrarDetalleBoleta() {
        System.out.println("------ Detalle de Boleta ------");
        System.out.printf("Subtotal        : $%.2f\n", subtotal);
        System.out.printf("Descuento aplicado: $%.2f\n", descuento);
        System.out.printf("Impuesto (19%%)  : $%.2f\n", impuesto);
        System.out.printf("Total a pagar   : $%.2f\n", total);
    }
}
