package com.duoc.model.exception;

public class TipoVehiculoInvalidoException extends RuntimeException {
    public TipoVehiculoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
