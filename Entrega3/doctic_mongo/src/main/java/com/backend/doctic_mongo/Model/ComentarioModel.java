package com.backend.doctic_mongo.Model;

import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("Comentarios")
@TypeAlias("comentarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioModel {
    @Id
    private ObjectId id;
    private ObjectId idDocumento;
    private ObjectId idUsuario;
    private String comentario;
    private Date fechaComentario;
    private List<ReplicaModel> replica; // Lista de réplicas embebidas

    public String getIdAsString() {
        return id != null ? id.toHexString() : null;
    }
}
