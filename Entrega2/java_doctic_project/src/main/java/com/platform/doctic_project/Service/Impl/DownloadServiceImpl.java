package com.platform.doctic_project.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.doctic_project.Exception.RecursoNoEncontradoException;
import com.platform.doctic_project.Model.Descarga;
import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Model.Usuario;
import com.platform.doctic_project.Repository.DescargaRepository;
import com.platform.doctic_project.Repository.DocumentoRepository;
import com.platform.doctic_project.Repository.UsuarioRepository;
import com.platform.doctic_project.Service.DownloadService;

@Service
public class DownloadServiceImpl implements DownloadService {

    @Autowired
    private DescargaRepository descargaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Override
    public void recordDocumentDownload(Integer documentId, Integer userId) {
        // Verificar que el usuario exista
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con ID: " + userId));

        // Verificar que el documento exista
        Documento documento = documentoRepository.findById(documentId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Documento no encontrado con ID: " + documentId));

        // Verificar si el documento es público
        if (documento.getVisibilidad() != Documento.Visibilidad.publico) {
            throw new IllegalArgumentException("El documento no está disponible para descarga porque es privado.");
        }

        // Registrar la descarga
        Descarga descarga = new Descarga();
        descarga.setUsuario(usuario);
        descarga.setDocumento(documento);
        descargaRepository.save(descarga);

        // Actualizar el contador de descargas en el documento
        documento.setNumDescargas(documento.getNumDescargas() + 1);
        documentoRepository.save(documento);
    }

    @Override
    public List<Descarga> getUserDownloadHistory(Integer userId) {
        // Verificar que el usuario exista
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con ID: " + userId));

        // Obtener el historial de descargas
        List<Descarga> historialDescargas = descargaRepository.findByUsuario(usuario);
        
        // Verificar si el usuario no tiene descargas
        if (historialDescargas.isEmpty()) {
            throw new IllegalArgumentException("El usuario no tiene descargas registradas.");
        }

        return historialDescargas;
    }

}
