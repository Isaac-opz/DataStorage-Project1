package com.backend.doctic_mongo.Service;

import com.backend.doctic_mongo.DTO.DocumentoDTO;
import com.backend.doctic_mongo.Exceptions.CustomException;
import com.backend.doctic_mongo.Model.DocumentoModel;

public interface IDocumentoService {
    void publicarDocumento(DocumentoDTO documentoDTO) throws CustomException;

    DocumentoModel descargarDocumento(String documentoId, String usuarioId) throws CustomException;
}