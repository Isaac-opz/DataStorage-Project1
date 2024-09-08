package com.platform.doctic_project.Controller;

import com.platform.doctic_project.Model.Comentario;
import com.platform.doctic_project.Service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @PostMapping("/add")
    public ResponseEntity<String> addComment(@RequestParam Long documentId, @RequestParam Long userId, @RequestBody String commentText) {
        comentarioService.addComment(documentId, userId, commentText);
        return ResponseEntity.ok("Comentario añadido con éxito.");
    }

    @PostMapping("/reply")
    public ResponseEntity<String> replyToComment(@RequestParam Long parentCommentId, @RequestParam Long userId, @RequestBody String replyText) {
        comentarioService.replyToComment(parentCommentId, userId, replyText);
        return ResponseEntity.ok("Respuesta añadida con éxito.");
    }

    @GetMapping("/document/{documentId}")
    public ResponseEntity<List<ComentarioModel>> listDocumentComments(@PathVariable Long documentId) {
        List<ComentarioModel> comentarios = comentarioService.listDocumentComments(documentId);
        return ResponseEntity.ok(comentarios);
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        comentarioService.deleteComment(commentId);
        return ResponseEntity.ok("Comentario eliminado con éxito.");
    }
}
