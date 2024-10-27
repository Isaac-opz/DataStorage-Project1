package com.backend.doctic_mongo.Service;

import com.backend.doctic_mongo.Model.DocumentoModel;

public interface IDocumentoService {
    DocumentoModel publicarDocumento(DocumentoModel documento);
}