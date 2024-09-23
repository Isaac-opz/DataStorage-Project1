package com.platform.doctic_project.Service;

import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Model.Valoracion;
import com.platform.doctic_project.Repository.DocumentoRepository;
import com.platform.doctic_project.Repository.ValoracionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValoracionServiceImp implements IValoracionService {

    @Autowired
    private ValoracionRepository valoracionRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    // 1. Servicio para añadir una valoración a un documento
    @Override
    public Valoracion rateDocument(Integer documentId, Valoracion valoracion) {
        Documento documento = documentoRepository.findById(documentId)
                .orElseThrow(() -> new IllegalArgumentException("Documento no encontrado."));
        valoracion.setDocumento(documento);
        Valoracion nuevaValoracion = valoracionRepository.save(valoracion);
        
        // Actualizar la valoración promedio del documento después de añadir una nueva valoración
        calculateAverageRating(documentId);
        
        return nuevaValoracion;
    }

    // 2. Servicio para calcular la valoración promedio de un documento
    @Override
    public void calculateAverageRating(Integer documentId) {
        List<Valoracion> valoraciones = valoracionRepository.findByDocumentoId(documentId);
        double promedio = valoraciones.stream().mapToDouble(Valoracion::getEstrellas).average().orElse(0.0);
        
        Documento documento = documentoRepository.findById(documentId)
                .orElseThrow(() -> new IllegalArgumentException("Documento no encontrado."));
        documento.setValoracion(promedio);
        documentoRepository.save(documento);
    }

    @Override
    public List<Valoracion> findByDocumentoId(Integer documentId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByDocumentoId'");
    }
}
