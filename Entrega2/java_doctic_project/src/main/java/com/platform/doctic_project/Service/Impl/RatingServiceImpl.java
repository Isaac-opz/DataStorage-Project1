package com.platform.doctic_project.Service.Impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.doctic_project.Exception.RecursoNoEncontradoException;
import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Model.Usuario;
import com.platform.doctic_project.Model.Valoracion;
import com.platform.doctic_project.Repository.DocumentoRepository;
import com.platform.doctic_project.Repository.UsuarioRepository;
import com.platform.doctic_project.Repository.ValoracionRepository;
import com.platform.doctic_project.Service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private ValoracionRepository valoracionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Override
    public Valoracion rateDocument(Integer userId, Integer documentId, Integer estrellas) {
        // Validar que el número de estrellas esté entre 1 y 5
        if (estrellas < 1 || estrellas > 5) {
            throw new IllegalArgumentException("La valoración debe estar entre 1 y 5 estrellas.");
        }

        // Verificar si el usuario existe
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con ID: " + userId));

        // Verificar si el documento existe
        Documento documento = documentoRepository.findById(documentId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Documento no encontrado con ID: " + documentId));

        // Verificar si el usuario ya ha valorado el documento
        Optional<Valoracion> valoracionExistente = valoracionRepository.findByUsuarioAndDocumento(usuario, documento);

        Valoracion valoracion;

        if (valoracionExistente.isPresent()) {
            // Actualizar la valoración existente
            valoracion = valoracionExistente.get();
            valoracion.setEstrellas(estrellas);
        } else {
            // Crear una nueva valoración
            valoracion = new Valoracion();
            valoracion.setUsuario(usuario);
            valoracion.setDocumento(documento);
            valoracion.setEstrellas(estrellas);
        }

        // Guardar la valoración
        return valoracionRepository.save(valoracion);
    }
    public List<Valoracion> listDocumentRatings(Integer documentId) {
        // Verificar si el documento existe
        Documento documento = documentoRepository.findById(documentId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Documento no encontrado con ID: " + documentId));
    
        // Obtener todas las valoraciones del documento
        List<Valoracion> valoraciones = valoracionRepository.findByDocumento(documento);
    
        if (valoraciones.isEmpty()) {
            throw new RecursoNoEncontradoException("Este documento no ha sido valorado aún.");
        }
    
        return valoraciones;
    }
}
