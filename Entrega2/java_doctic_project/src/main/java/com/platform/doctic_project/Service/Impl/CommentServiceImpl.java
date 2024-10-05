package com.platform.doctic_project.Service.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.doctic_project.Exception.RecursoNoEncontradoException;
import com.platform.doctic_project.Model.Comentario;
import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Model.Usuario;
import com.platform.doctic_project.Repository.ComentarioRepository;
import com.platform.doctic_project.Repository.DocumentoRepository;
import com.platform.doctic_project.Repository.UsuarioRepository;
import com.platform.doctic_project.Service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired UsuarioRepository  usuarioRepository;


    @Override
    public Comentario addComment(Comentario comentario) {
        // Verificar que el documento exista
        Documento documento = documentoRepository.findById(comentario.getDocumento().getIdDocumento())
                .orElseThrow(() -> new RecursoNoEncontradoException("Documento no encontrado con ID: " + comentario.getDocumento().getIdDocumento()));

        // Verificar que el usuario exista
        Usuario usuario = usuarioRepository.findById(comentario.getUsuario().getIdUsuario())
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con ID: " + comentario.getUsuario().getIdUsuario()));
        
        // Verificar si el documento es público
        if (documento.getVisibilidad() != Documento.Visibilidad.publico) {
            throw new IllegalArgumentException("El documento no está disponible para comentar porque es privado.");
        }

        // Asignar la fecha actual si no se proporciona
        if (comentario.getFechaComentario() == null) {
            comentario.setFechaComentario(LocalDateTime.now());
        }

        // Guardar el comentario
        comentario.setDocumento(documento);
        comentario.setUsuario(usuario);
        Comentario nuevoComentario = comentarioRepository.save(comentario);

        // Actualizar el contador de comentarios del documento
        documento.setNumComentarios(documento.getNumComentarios() + 1);
        documentoRepository.save(documento);

        return nuevoComentario;
    }

    
    @Override
    public Comentario replyToComment(Integer parentCommentId, Comentario comentario) {
        // Verificar que el comentario padre exista
        Comentario comentarioPadre = comentarioRepository.findById(parentCommentId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Comentario no encontrado con ID: " + parentCommentId));

        // Verificar que el documento exista
        Documento documento = documentoRepository.findById(comentario.getDocumento().getIdDocumento())
                .orElseThrow(() -> new RecursoNoEncontradoException("Documento no encontrado con ID: " + comentario.getDocumento().getIdDocumento()));

        // Verificar que el usuario exista
        Usuario usuario = usuarioRepository.findById(comentario.getUsuario().getIdUsuario())
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con ID: " + comentario.getUsuario().getIdUsuario()));
        
        // Verificar si el documento es público
        if (documento.getVisibilidad() != Documento.Visibilidad.publico) {
            throw new IllegalArgumentException("El comentario no está disponible para comentar porque su documento es privado.");
        }

        // Asignar la fecha actual si no se proporciona
        if (comentario.getFechaComentario() == null) {
            comentario.setFechaComentario(LocalDateTime.now());
        }

        // Establecer el comentario padre
        comentario.setMetacomentario(comentarioPadre);

        // Asignar el documento y usuario al comentario
        comentario.setDocumento(documento);
        comentario.setUsuario(usuario);

        // Guardar la respuesta
        Comentario nuevoComentario = comentarioRepository.save(comentario);

        // Actualizar el contador de comentarios del documento
        documento.setNumComentarios(documento.getNumComentarios() + 1);
        documentoRepository.save(documento);

        return nuevoComentario;
    }



    @Override
    public List<Comentario> listDocumentComments(Integer documentId) {
        // Verificar que el documento exista
        Documento documento = documentoRepository.findById(documentId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Documento no encontrado con ID: " + documentId));

        // Obtener los comentarios del documento
        return comentarioRepository.findByDocumento(documento);
    }


    @Override
    public void deleteComment(Integer commentId) {
        // Verificar que el comentario exista
        Comentario comentario = comentarioRepository.findById(commentId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Comentario no encontrado con ID: " + commentId));

        // Obtener el documento asociado al comentario
        Documento documento = comentario.getDocumento();

        // Eliminar el comentario
        comentarioRepository.delete(comentario);

        // Actualizar el contador de comentarios del documento
        if (documento != null) {
            documento.setNumComentarios(documento.getNumComentarios() - 1);
            documentoRepository.save(documento);
        }
    }

}
