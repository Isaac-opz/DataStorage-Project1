package com.platform.doctic_project.Service.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.doctic_project.Exception.RecursoNoEncontradoException;
import com.platform.doctic_project.Model.AutorDocumento;
import com.platform.doctic_project.Model.Categoria;
import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Model.Documento.Visibilidad;
import com.platform.doctic_project.Model.Usuario;
import com.platform.doctic_project.Repository.AutorDocumentoRepository;
import com.platform.doctic_project.Repository.CategoriaRepository;
import com.platform.doctic_project.Repository.DocumentoRepository;
import com.platform.doctic_project.Repository.UsuarioRepository;
import com.platform.doctic_project.Service.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;


    @Autowired
    private AutorDocumentoRepository autorDocumentoRepository;

    @Override
    public Documento createDocument(Documento documento, Integer userId) {
        // Verificar que el usuario exista
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con ID: " + userId));
    
        // Asignar el usuario al documento
        documento.setUsuario(usuario);
    
        // Verificar que la categoría exista
        Categoria categoria = categoriaRepository.findById(documento.getCategoria().getIdCategoria())
                .orElseThrow(() -> new RecursoNoEncontradoException("Categoría no encontrada con ID: " + documento.getCategoria().getIdCategoria()));
    
        // Asignar la categoría al documento
        documento.setCategoria(categoria);
    
        // Asignar la fecha de publicación actual (si no está ya asignada)
        if (documento.getFechaPublicacion() == null) {
            documento.setFechaPublicacion(LocalDateTime.now());
        }
    
        // Guardar el documento
        Documento nuevoDocumento = documentoRepository.save(documento);
    
        // Crear relación en AutorDocumento
        AutorDocumento autorDocumento = new AutorDocumento();
        autorDocumento.setDocumento(nuevoDocumento);
        autorDocumento.setUsuario(usuario);
        autorDocumento.setPublico(AutorDocumento.Publico.Si);  // Ajustar según tu enum de visibilidad
    
        autorDocumentoRepository.save(autorDocumento);
    
        return nuevoDocumento;
    }
    
    



    @Override
    public List<Documento> listUserDocuments(Integer userId) {
        // Verificar que el usuario exista
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con ID: " + userId));

        // Obtener los documentos donde el usuario es autor
        return documentoRepository.findByAutores_Usuario(usuario);
    }

    @Override
    public void updateDocumentVisibility(Integer documentId, String visibilidad) {
        // Verificar que el documento exista
        Documento documento = documentoRepository.findById(documentId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Documento no encontrado con ID: " + documentId));

        try {
            // Asegurar que el valor es 'publico' o 'privado'
            Visibilidad nuevaVisibilidad = Visibilidad.valueOf(visibilidad.toLowerCase());
            documento.setVisibilidad(nuevaVisibilidad);
            documentoRepository.save(documento);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Valor de visibilidad inválido: " + visibilidad);
        }
    }
}
