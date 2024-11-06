package com.backend.doctic_mongo.Model;

import org.bson.types.ObjectId;
import com.backend.doctic_mongo.Model.ENUM.PublicoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutorModel {
    private ObjectId idUsuario;
    private PublicoEnum publico; // Indica si el autor es el que publicó el documento (SI o NO)
}
