package com.backend.doctic_mongo.Service;

import com.backend.doctic_mongo.DTO.DocumentoDTO;
import com.backend.doctic_mongo.Exceptions.CustomException;
import com.backend.doctic_mongo.Model.DescargaModel;
import com.backend.doctic_mongo.Model.DocumentoModel;
import com.backend.doctic_mongo.Repository.IDocumentoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;


import java.util.Optional;

@Service
public class DocumentoServiceImpl implements IDocumentoService {

    @Autowired
    private IDocumentoRepository documentoRepository;

    @Override
    public void publicarDocumento(DocumentoDTO documentoDTO) {
        DocumentoModel documento = new DocumentoModel();
        // Mapeo y asignación de datos de documentoDTO a documentoModel
        documentoRepository.save(documento);
    }

    @Override
    public DocumentoModel descargarDocumento(String documentoId, String usuarioId) {
        Optional<DocumentoModel> documentoOpt = documentoRepository.findById(new ObjectId(documentoId));
        if (documentoOpt.isEmpty()) {
            throw new CustomException("Documento no encontrado");
        }

        DocumentoModel documento = documentoOpt.get();

        // Verificar si el usuario ya descargó el documento
    boolean yaDescargado = documento.getDescargas().stream()
            .anyMatch(d -> d.getIdUsuario().equals(new ObjectId(usuarioId)));

    if (!yaDescargado) {
        // Inicializar la lista de descargas si es null
        if (documento.getDescargas() == null) {
            documento.setDescargas(new ArrayList<>());
        }
        
        // Registrar la primera descarga del usuario
        documento.getDescargas().add(new DescargaModel(new Date(), new ObjectId(usuarioId)));
        documentoRepository.save(documento);
    }

    // Crear una copia del documento con solo la información necesaria para mostrar
    DocumentoModel documentoParaMostrar = new DocumentoModel();
    documentoParaMostrar.setId(documento.getId());
    documentoParaMostrar.setNombreDocumento(documento.getNombreDocumento());
    documentoParaMostrar.setDescripcion(documento.getDescripcion());
    documentoParaMostrar.setFechaPublicacion(documento.getFechaPublicacion());
    documentoParaMostrar.setAutores(documento.getAutores()); // Incluir solo los autores

    return documentoParaMostrar; // Retornar solo la información necesaria

        }
}
