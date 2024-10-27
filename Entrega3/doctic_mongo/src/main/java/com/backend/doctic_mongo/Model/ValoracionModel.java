package com.backend.doctic_mongo.Model;

import java.util.Date;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("Valoraciones")
@TypeAlias("valoraciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValoracionModel {
    @Id
    private ObjectId id;
    private int estrellas;
    private Date fechaValoracion;
    private ObjectId idDocumento;
    private ObjectId idUsuario;

    public String getIdAsString() {
        return id != null ? id.toHexString() : null;
    }
}