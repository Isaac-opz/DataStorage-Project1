package com.backend.doctic_mongo.Model;

import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.backend.doctic_mongo.Model.ENUM.VisibilidadEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("Documentos")
@TypeAlias("documentos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoModel {
    @Id
    private ObjectId id;
    private String nombreDocumento;
    private String descripcion;
    private Date fechaPublicacion;
    private String url;
    private VisibilidadEnum visibilidad; 
    private int valoracion;
    private int numDescargas;
    private int numVistas;
    private int numComentarios;
    private ObjectId idCategoria;
    private ObjectId idMetacategoria;
    private List<AutorModel> autores;

    public String getIdAsString() {
        return id != null ? id.toHexString() : null;
    }
}