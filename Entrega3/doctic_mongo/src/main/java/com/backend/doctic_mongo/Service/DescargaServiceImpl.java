package com.backend.doctic_mongo.Service;

import java.util.Date;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.doctic_mongo.Model.DocumentoModel;
import com.backend.doctic_mongo.Model.DescargaModel;
import com.backend.doctic_mongo.Repository.IDescargaRepository;
import com.backend.doctic_mongo.Repository.IDocumentoRepository;

@Service
public class DescargaServiceImpl implements IDescargaService {

    @Autowired
    private IDocumentoRepository documentoRepository;
    
    @Autowired
    private IDescargaRepository descargaRepository;

    @Override
    public String registrarDescarga(String documentoId, String usuarioId) {
        DocumentoModel documento = documentoRepository.findById(new ObjectId(documentoId))
            .orElseThrow(() -> new IllegalArgumentException("Documento no encontrado"));

        // Registrar la descarga
        DescargaModel descarga = new DescargaModel();
        descarga.setIdDocumento(documento.getId());
        descarga.setIdUsuario(new ObjectId(usuarioId));
        descarga.setFechaHora(new Date());
        descargaRepository.save(descarga);

        // Incrementar el contador de descargas
        documento.setNumDescargas(documento.getNumDescargas() + 1);
        documentoRepository.save(documento);

        return documento.getUrl();
    }
}