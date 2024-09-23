package com.platform.doctic_project.Controller;

import com.platform.doctic_project.Exception.RecursoNoEncontradoException;
import com.platform.doctic_project.Model.Comentario;
import com.platform.doctic_project.Service.IComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comentarios") // Endpoint base para comentarios
public class ComentarioController {

    @Autowired
    private IComentarioService comentarioService; // Usamos la interfaz

    // Endpoint para añadir un comentario
    @PostMapping("/insertar")
    public ResponseEntity<String> addComment(@RequestParam Long documentId, @RequestParam Long userId, @RequestBody String commentText) {
        try {
            comentarioService.addComment(documentId, userId, commentText);
            return new ResponseEntity<>("Comentario añadido con éxito.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para responder a un comentario existente
    @PostMapping("/responder")
    public ResponseEntity<String> replyToComment(@RequestParam Long parentCommentId, @RequestParam Long userId, @RequestBody String replyText) {
        try {
            comentarioService.replyToComment(parentCommentId, userId, replyText);
            return new ResponseEntity<>("Respuesta añadida con éxito.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para obtener los comentarios de un documento
    @GetMapping("/documento/{documentId}")
    public ResponseEntity<?> listDocumentComments(@PathVariable Long documentId) {
        try {
            List<Comentario> comentarios = comentarioService.listDocumentComments(documentId);
            return ResponseEntity.ok(comentarios);
        } catch (RecursoNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para eliminar un comentario
    @DeleteMapping("/eliminar/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        try {
            comentarioService.deleteComment(commentId);
            return new ResponseEntity<>("Comentario eliminado con éxito.", HttpStatus.OK);
        } catch (RecursoNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
