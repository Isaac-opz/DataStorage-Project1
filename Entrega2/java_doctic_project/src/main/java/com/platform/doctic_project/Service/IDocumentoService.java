package com.platform.doctic_project.Service;

import java.util.List;

import com.platform.doctic_project.Model.Documento;

public interface IDocumentoService {
    Documento createDocument(Documento documento, Integer userId);
    List<Documento> listUserDocuments(Integer userId);
    List<Documento> searchDocuments(String searchQuery);
    Documento updateDocumentVisibility(Integer documentId, String visibility);
    void assignCategoryToDocument(Integer documentId, Integer categoryId);
}
