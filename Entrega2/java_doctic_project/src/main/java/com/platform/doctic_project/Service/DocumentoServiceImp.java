package com.platform.doctic_project.Service;

import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Model.Usuario;
import com.platform.doctic_project.Repository.DocumentoRepository;
import com.platform.doctic_project.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentoServiceImp implements IDocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // 1. Servicio para subir un nuevo documento
    @Override
    public Documento createDocument(Documento documento, Integer userId) {
        // Verificar si el usuario existe
        Optional<Usuario> usuario = usuarioRepository.findById(userId);
        if (usuario.isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado.");
        }

        // Establecer el usuario como autor del documento
        documento.setUsuario(usuario.get());
        return documentoRepository.save(documento);
    }

    // 2. Servicio para listar todos los documentos de un usuario autor
    @Override
    public List<Documento> listUserDocuments(String autorNombre) {
        return documentoRepository.findByUsuarioNombreUsuario(autorNombre);
    }

    // 3. Servicio para buscar documentos por nombre o autor
    @Override
    public List<Documento> searchDocuments(String searchQuery) {
        return documentoRepository.findByNombreDocumentoContainingOrUsuarioNombreUsuarioContaining(searchQuery, searchQuery);
    }

    // 4. Servicio para actualizar la visibilidad de un documento
    @Override
    public Documento updateDocumentVisibility(Integer documentId, String visibility) {
        Optional<Documento> documento = documentoRepository.findById(documentId);
        if (documento.isEmpty()) {
            throw new IllegalArgumentException("Documento no encontrado.");
        }

        // Actualizar la visibilidad del documento
        documento.get().setVisibilidad(visibility);
        return documentoRepository.save(documento.get());
    }
}
