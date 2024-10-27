package com.backend.doctic_mongo.Service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.doctic_mongo.Model.DocumentoModel;
import com.backend.doctic_mongo.Repository.IDocumentoRepository;

@Service
public class DocumentoServiceImpl implements IDocumentoService {

    @Autowired
    private IDocumentoRepository documentoRepository;

    @Override
    public DocumentoModel publicarDocumento(DocumentoModel documento) {
        // Validaciones
        if (documento.getNombreDocumento() == null || documento.getNombreDocumento().isEmpty()) {
            throw new IllegalArgumentException("El nombre del documento no puede estar vacío");
        }
        if (documento.getIdCategoria() == null) {
            throw new IllegalArgumentException("Debe especificar una categoría válida");
        }

        // Establecer la fecha de publicación
        documento.setFechaPublicacion(new Date());
        documento.setValoracion(0);
        documento.setNumDescargas(0);
        documento.setNumVistas(0);
        documento.setNumComentarios(0);

        // Guardar el documento en la base de datos
        return documentoRepository.save(documento);
    }
}