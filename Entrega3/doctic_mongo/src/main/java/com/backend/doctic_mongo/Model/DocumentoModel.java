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
    private Categoria categoria;
    private List<AutorModel> autores;           // Lista de autores embebidos
    private List<ValoracionModel> valoraciones; // Lista de valoraciones embebidas
    private List<DescargaModel> descargas;      // Lista de descargas embebidas
    private List<VistaModel> vistas;            // Lista de vistas embebidas

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Categoria {
        private ObjectId idCategoria;
        private String categoria;
        private ObjectId idMetacategoria;
    }
}
