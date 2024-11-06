package com.backend.doctic_mongo.Model;

import java.util.Date;
import org.bson.types.ObjectId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplicaModel {
    private ObjectId idUsuario;
    private Date fechaComentario;
    private String contenido;
}
