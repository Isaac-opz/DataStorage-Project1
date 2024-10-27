package com.backend.doctic_mongo.Model.ENUM;

public enum EstadoEnum {
    ACTIVA("activa"),
    INACTIVA("inactiva");

    private final String valor;

    EstadoEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}