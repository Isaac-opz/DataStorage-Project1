package com.backend.doctic_mongo.Model;

import java.util.Date;

import com.backend.doctic_mongo.Model.ENUM.EstadoEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContrasenaHistorialModel {
    private String contrasena;
    private Date fechaCambio;
    private EstadoEnum estado;
}