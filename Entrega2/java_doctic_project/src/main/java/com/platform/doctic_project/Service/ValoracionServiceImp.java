package com.platform.doctic_project.Service;

import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Model.Valoracion;
import com.platform.doctic_project.Model.Usuario;
import com.platform.doctic_project.Repository.DocumentoRepository;
import com.platform.doctic_project.Repository.ValoracionRepository;
import com.platform.doctic_project.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValoracionServiceImp implements IValoracionService {

    @Autowired
    private ValoracionRepository valoracionRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // 1. Servicio para añadir una valoración a un documento
    @Override
    public void rateDocument(Integer documentId, Integer userId, int estrellas) {
        Documento documento = documentoRepository.findById(documentId)
                .orElseThrow(() -> new IllegalArgumentException("Documento no encontrado."));
        
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));
        
        // Crear una nueva valoración
        Valoracion valoracion = new Valoracion();
        valoracion.setDocumento(documento);
        valoracion.setUsuario(usuario);
        valoracion.setEstrellas(estrellas);
        
        // Guardar la nueva valoración
        valoracionRepository.save(valoracion);
        
        // Actualizar la valoración promedio del documento después de añadir una nueva valoración
        double promedio = calculateAverageRating(documentId);
        documento.setValoracion(promedio);
        documentoRepository.save(documento);
    }

    // 2. Servicio para calcular la valoración promedio de un documento
    @Override
    public double calculateAverageRating(Integer documentId) {
        List<Valoracion> valoraciones = valoracionRepository.findByDocumentoId(documentId);
        return valoraciones.stream()
                .mapToDouble(Valoracion::getEstrellas)
                .average()
                .orElse(0.0);
    }

    // Método para buscar valoraciones por ID de documento (opcional si es necesario)
    @Override
    public List<Valoracion> findByDocumentoId(Integer documentId) {
        return valoracionRepository.findByDocumentoId(documentId);
    }
}
