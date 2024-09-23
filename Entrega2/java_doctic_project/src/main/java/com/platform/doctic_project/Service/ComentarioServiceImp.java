package com.platform.doctic_project.Service;

import com.platform.doctic_project.Model.Comentario;
import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Model.Usuario;
import com.platform.doctic_project.Repository.ComentarioRepository;
import com.platform.doctic_project.Repository.DocumentoRepository;
import com.platform.doctic_project.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ComentarioServiceImp implements IComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // 1. Servicio para añadir un comentario a un documento
    @Override
    public Comentario addComment(Integer documentId, Integer userId, String content) {
        Optional<Documento> documento = documentoRepository.findById(documentId);
        Optional<Usuario> usuario = usuarioRepository.findById(userId);

        if (documento.isEmpty()) {
            throw new IllegalArgumentException("Documento no encontrado.");
        }

        if (usuario.isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado.");
        }

        Comentario comentario = new Comentario();
        comentario.setDocumento(documento.get());
        comentario.setUsuario(usuario.get());
        comentario.setComentario(content);
        return comentarioRepository.save(comentario);
    }

    // 2. Servicio para responder a un comentario existente
    @Override
    public Comentario replyToComment(Integer commentId, Integer userId, String content) {
        Optional<Comentario> originalComment = comentarioRepository.findById(commentId);
        Optional<Usuario> usuario = usuarioRepository.findById(userId);

        if (originalComment.isEmpty()) {
            throw new IllegalArgumentException("Comentario original no encontrado.");
        }

        if (usuario.isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado.");
        }

        Comentario respuesta = new Comentario();
        respuesta.setDocumento(originalComment.get().getDocumento());
        respuesta.setUsuario(usuario.get());
        respuesta.setComentario(content);
        respuesta.setIdMetacomentario(originalComment.get()); // Esto es para hacer referencia al comentario original
        return comentarioRepository.save(respuesta);
    }
}
