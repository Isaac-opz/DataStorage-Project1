package com.backend.doctic_mongo.Model;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("Usuarios")
@TypeAlias("usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioModel {
    @Id
    private ObjectId id;
    private String nombreUsuario;
    private String correoElectronico;
    private String ciudad;
    private String departamento;
    private String preguntaSecreta;
    private String respuestaSecreta;
    private List<ContrasenaHistorialModel> historialContrasenas;

    public String getIdAsString() {
        return id != null ? id.toHexString() : null;
    }
}
