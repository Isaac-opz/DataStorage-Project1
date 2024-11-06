package com.backend.doctic_mongo.Service;

import com.backend.doctic_mongo.DTO.DocumentoDTO;

public interface IDocumentoService {
    void publicarDocumento(DocumentoDTO documentoDTO);
    void descargarDocumento(String documentoId, String usuarioId);
}
