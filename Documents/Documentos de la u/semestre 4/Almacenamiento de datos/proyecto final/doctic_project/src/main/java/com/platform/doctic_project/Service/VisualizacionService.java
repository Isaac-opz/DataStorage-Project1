package com.platform.doctic_project.Service;

import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Model.Visualizacion;
import com.platform.doctic_project.Model.Usuario;
import com.platform.doctic_project.Repository.DocumentoRepository;
import com.platform.doctic_project.Repository.VisualizacionRepository;
import com.platform.doctic_project.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VisualizacionService {

    @Autowired
    private VisualizacionRepository VisualizacionRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // 1. Servicio para registrar cuando un documento ha sido visto por un usuario
    public void recordDocumentView(Integer documentId, Integer userId) {
        Documento documento = documentoRepository.findById(documentId).orElseThrow(() -> new IllegalArgumentException("Documento no encontrado."));
        Usuario usuario = usuarioRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));

        HistorialVisualizacion visualizacion = new HistorialVisualizacion();
        visualizacion.setDocumento(documento);
        visualizacion.setUsuario(usuario);
        visualizacion.setFechaHora(LocalDateTime.now());

        VisualizacionRepository.save(visualizacion);
    }

    // 2. Servicio para obtener el historial de visualizaciones de un usuario
    public List<HistorialVisualizacion> getUserViewHistory(Integer userId) {
        return VisualizacionRepository.findByUsuarioId(userId);
    }
}
