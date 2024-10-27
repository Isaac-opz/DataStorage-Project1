package com.backend.doctic_mongo.Model.ENUM;


public enum VisibilidadEnum {
    PUBLICO("publico"),
    PRIVADO("privado");

    private final String valor;

    VisibilidadEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
