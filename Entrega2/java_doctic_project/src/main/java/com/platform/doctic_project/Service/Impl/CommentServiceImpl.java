package com.platform.doctic_project.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.doctic_project.Exception.RecursoNoEncontradoException;
import com.platform.doctic_project.Model.Comentario;
import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Repository.ComentarioRepository;
import com.platform.doctic_project.Repository.DocumentoRepository;
import com.platform.doctic_project.Service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Override
    public Comentario addComment(Comentario comentario) {
        // Verificar que el documento exista
        Documento documento = documentoRepository.findById(comentario.getDocumento().getIdDocumento())
                .orElseThrow(() -> new RecursoNoEncontradoException("Documento no encontrado con ID: " + comentario.getDocumento().getIdDocumento()));

        // Guardar el comentario
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

        // Establecer el comentario padre
        comentario.setMetacomentario(comentarioPadre);

        // Guardar la respuesta
        return addComment(comentario);
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

        // Eliminar el comentario
        comentarioRepository.delete(comentario);

        // Actualizar el contador de comentarios del documento
        Documento documento = comentario.getDocumento();
        documento.setNumComentarios(documento.getNumComentarios() - 1);
        documentoRepository.save(documento);
    }
}
