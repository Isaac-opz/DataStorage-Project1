package com.platform.doctic_project.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.doctic_project.Exception.RecursoNoEncontradoException;
import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Model.Usuario;
import com.platform.doctic_project.Model.VistoPor;
import com.platform.doctic_project.Repository.DocumentoRepository;
import com.platform.doctic_project.Repository.UsuarioRepository;
import com.platform.doctic_project.Repository.VistoPorRepository;
import com.platform.doctic_project.Service.ViewService;

@Service
public class ViewServiceImpl implements ViewService {

    @Autowired
    private VistoPorRepository vistoPorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Override
    public void recordDocumentView(Integer documentId, Integer userId) {
        // Verificar que el usuario exista
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con ID: " + userId));

        // Verificar que el documento exista
        Documento documento = documentoRepository.findById(documentId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Documento no encontrado con ID: " + documentId));

        // Verificar la visibilidad del documento
        if (documento.getVisibilidad() == Documento.Visibilidad.privado) {
            throw new IllegalArgumentException("El documento es privado y no se puede visualizar.");
        }

        // Registrar la visualización
        VistoPor vistoPor = new VistoPor();
        vistoPor.setUsuario(usuario);
        vistoPor.setDocumento(documento);
        vistoPorRepository.save(vistoPor);

        // Actualizar el contador de vistas en el documento
        documento.setNumVistas(documento.getNumVistas() + 1);
        documentoRepository.save(documento);
    }


    @Override
    public List<VistoPor> getUserViewHistory(Integer userId) {
        // Verificar que el usuario exista
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con ID: " + userId));

        return vistoPorRepository.findByUsuario(usuario);
    }
}
