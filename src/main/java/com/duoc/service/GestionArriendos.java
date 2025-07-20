package com.duoc.service;

import java.util.Scanner;

public interface GestionArriendos {

    void crearArriendo(Scanner sc);
    void listarArriendos();
    void mostrarBoleta(Scanner sc);
    void exportarArriendosAExcel();
}
