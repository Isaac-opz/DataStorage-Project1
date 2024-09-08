package com.platform.doctic_project.Service;

import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Model.Descarga;
import com.platform.doctic_project.Model.Usuario;
import com.platform.doctic_project.Repository.DocumentoRepository;
import com.platform.doctic_project.Repository.DescargaRepository;
import com.platform.doctic_project.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DescargaService {

    @Autowired
    private DescargaRepository DescargaRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // 1. Servicio para registrar cuando un documento ha sido descargado por un usuario
    public void recordDocumentDownload(Integer documentId, Integer userId) {
        Documento documento = documentoRepository.findById(documentId).orElseThrow(() -> new IllegalArgumentException("Documento no encontrado."));
        Usuario usuario = usuarioRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));

        HistorialDescarga descarga = new HistorialDescarga();
        descarga.setDocumento(documento);
        descarga.setUsuario(usuario);
        descarga.setFechaHora(LocalDateTime.now());

        DescargaRepository.save(descarga);
    }

    // 2. Servicio para obtener el historial de descargas de un usuario
    public List<HistorialDescarga> getUserDownloadHistory(Integer userId) {
        return DescargaRepository.findByUsuarioId(userId);
    }
}
