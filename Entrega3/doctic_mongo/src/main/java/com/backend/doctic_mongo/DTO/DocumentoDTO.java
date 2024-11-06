package com.backend.doctic_mongo.DTO;


import com.backend.doctic_mongo.Model.ENUM.VisibilidadEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoDTO {
    private String nombreDocumento;
    private String descripcion;
    private String url;
    private VisibilidadEnum visibilidad;
    // Getters y Setters
}
