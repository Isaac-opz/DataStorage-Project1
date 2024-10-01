package com.platform.doctic_project.Service;

import java.util.List;

import com.platform.doctic_project.Model.Documento;

public interface DocumentService {
    Documento createDocument(Documento documento, Integer userId);
    List<Documento> listUserDocuments(Integer userId);
    void updateDocumentVisibility(Integer documentId, String visibilidad);
}
