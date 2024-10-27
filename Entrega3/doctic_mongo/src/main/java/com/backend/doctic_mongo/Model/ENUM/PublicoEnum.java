package com.backend.doctic_mongo.Model.ENUM;

public enum PublicoEnum {
    SI("Si"),
    NO("No");

    private final String valor;

    PublicoEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}