package com.platform.doctic_project.Controller;

import com.platform.doctic_project.Model.Comentario;
import com.platform.doctic_project.Service.IComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private IComentarioService comentarioService;

    @GetMapping("/{documentId}")
    public List<Comentario> listDocumentComments(@PathVariable Integer documentId) {
        return comentarioService.listDocumentComments(documentId);
    }

    @PostMapping("/add")
    public Comentario addComment(@RequestBody Comentario comentario) {
        return comentarioService.addComment(comentario);
    }

    @DeleteMapping("/delete/{commentId}")
    public void deleteComment(@PathVariable Integer commentId) {
        comentarioService.deleteComment(commentId);
    }
}
