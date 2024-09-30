package com.platform.doctic_project.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return comentarioRepository.save(comentario);
    }

    @Override
    public Comentario replyToComment(Integer parentCommentId, Comentario comentario) {
        // Lógica para responder a un comentario
        return null;
    }

    @Override
    public List<Comentario> listDocumentComments(Integer documentId) {
        // Recuperar el objeto Documento por su ID
        Optional<Documento> documentoOpt = documentoRepository.findById(documentId);
        
        if (documentoOpt.isPresent()) {
            Documento documento = documentoOpt.get();
            // Pasar el objeto Documento al repositorio de comentarios
            return comentarioRepository.findByDocumento(documento);
        } else {
            throw new IllegalArgumentException("Documento no encontrado con ID: " + documentId);
        }
    }

    @Override
    public void deleteComment(Integer commentId) {
        comentarioRepository.deleteById(commentId);
    }
}
