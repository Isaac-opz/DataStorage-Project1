package com.platform.doctic_project.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Repository.DocumentoRepository;
import com.platform.doctic_project.Service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentoRepository documentoRepository;

    @Override
    public Documento createDocument(Documento documento) {
        // Lógica para crear un documento
        return documentoRepository.save(documento);
    }

    @Override
    public List<Documento> listUserDocuments(Integer userId) {
        // Lógica para listar documentos de un usuario
        return null;
    }

    @Override
    public void updateDocumentVisibility(Integer documentId, String visibilidad) {
        // Lógica para actualizar la visibilidad
    }
}
