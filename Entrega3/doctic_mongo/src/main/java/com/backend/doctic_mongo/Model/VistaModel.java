package com.backend.doctic_mongo.Model;

import java.util.Date;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("Vistas")
@TypeAlias("vistas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VistaModel {
    @Id
    private ObjectId id;
    private ObjectId idDocumento;
    private ObjectId idUsuario;
    private Date fechaHora;

    public String getIdAsString() {
        return id != null ? id.toHexString() : null;
    }
}