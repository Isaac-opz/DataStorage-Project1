package com.backend.doctic_mongo.Service;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.doctic_mongo.DTO.DocumentoDTO;
import com.backend.doctic_mongo.Exceptions.CustomException;
import com.backend.doctic_mongo.Model.DocumentoModel;
import com.backend.doctic_mongo.Repository.IDocumentoRepository;

@Service
public class DocumentoServiceImpl implements IDocumentoService {

    @Autowired
    private IDocumentoRepository documentoRepository;

    @Override
    public void publicarDocumento(DocumentoDTO documentoDTO) {
        try {
            DocumentoModel documento = new DocumentoModel();
            // Mapeo de campos correctos en DocumentoModel
            documento.setNombreDocumento(documentoDTO.getTitulo()); // Cambiado a setNombreDocumento
            documento.setDescripcion(documentoDTO.getContenido());  // Cambiado a setDescripcion
            documento.setFechaPublicacion(documentoDTO.getFechaPublicacion());

            documentoRepository.save(documento);
        } catch (Exception e) {
            throw new CustomException("Error al publicar el documento: " + e.getMessage());
        }
    }

    @Override
    public DocumentoModel descargarDocumento(String documentoId, String usuarioId) {
        try {
            ObjectId id = new ObjectId(documentoId); // Conversión de String a ObjectId
            Optional<DocumentoModel> documentoOpt = documentoRepository.findById(id);

            if (documentoOpt.isPresent()) {
                return documentoOpt.get();
            } else {
                throw new CustomException("Documento no encontrado con ID: " + documentoId);
            }
        } catch (Exception e) {
            throw new CustomException("Error al descargar el documento: " + e.getMessage());
        }
    }
}
