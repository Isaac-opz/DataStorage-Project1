package com.platform.doctic_project.Service;

import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Model.VistoPor;
import com.platform.doctic_project.Model.Usuario;
import com.platform.doctic_project.Repository.DocumentoRepository;
import com.platform.doctic_project.Repository.VisualizacionRepository;
import com.platform.doctic_project.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VisualizacionServiceImp implements IVisualizacionService {

    @Autowired
    private VisualizacionRepository visualizacionRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // 1. Servicio para registrar cuando un documento ha sido visto por un usuario
    @Override
    public void recordDocumentView(Integer documentId, Integer userId) {
        Documento documento = documentoRepository.findById(documentId)
                .orElseThrow(() -> new IllegalArgumentException("Documento no encontrado."));
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));

        VistoPor visualizacion = new  Visualizacion();
        visualizacion.setDocumento(documento);
        visualizacion.setUsuario(usuario);
        visualizacion.setFechaHora(LocalDateTime.now());

        visualizacionRepository.save(visualizacion);
    }

    // 2. Servicio para obtener el historial de visualizaciones de un usuario
    @Override
    public List<Visualizacion> getUserViewHistory(Integer userId) {
        return visualizacionRepository.findByUsuarioId(userId);
    }
}
