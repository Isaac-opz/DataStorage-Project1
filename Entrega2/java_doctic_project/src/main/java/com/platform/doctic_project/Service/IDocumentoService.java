package com.platform.doctic_project.Service;

import com.platform.doctic_project.Model.Documento;

import java.util.List;

public interface IDocumentoService {
    Documento createDocument(Documento documento, Integer userId);
    List<Documento> listUserDocuments(Long userId);
    List<Documento> searchDocuments(String searchQuery);
    Documento updateDocumentVisibility(Integer documentId, String visibility);
}
