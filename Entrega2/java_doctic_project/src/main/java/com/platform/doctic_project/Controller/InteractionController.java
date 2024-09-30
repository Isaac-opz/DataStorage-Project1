package com.platform.doctic_project.Controller;

import java.util.List;

//ESTE ES DE DESCARGAS, VISUALIZACIONES Y COMENTARIOS

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.doctic_project.Model.Comentario;
import com.platform.doctic_project.Model.Descarga;
import com.platform.doctic_project.Model.VistoPor;
import com.platform.doctic_project.Service.CommentService;
import com.platform.doctic_project.Service.DownloadService;
import com.platform.doctic_project.Service.ViewService;

@RestController
@RequestMapping("/api/interactions")
public class InteractionController {

    @Autowired
    private DownloadService downloadService;

    @Autowired
    private ViewService viewService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/download")
    public ResponseEntity<Void> recordDocumentDownload(@RequestParam Integer documentId, @RequestParam Integer userId) {
        downloadService.recordDocumentDownload(documentId, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/downloads/{userId}")
    public ResponseEntity<List<Descarga>> getUserDownloadHistory(@PathVariable Integer userId) {
        List<Descarga> downloadHistory = downloadService.getUserDownloadHistory(userId);
        return ResponseEntity.ok(downloadHistory);
    }

    @PostMapping("/view")
    public ResponseEntity<Void> recordDocumentView(@RequestParam Integer documentId, @RequestParam Integer userId) {
        viewService.recordDocumentView(documentId, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/views/{userId}")
    public ResponseEntity<List<VistoPor>> getUserViewHistory(@PathVariable Integer userId) {
        List<VistoPor> viewHistory = viewService.getUserViewHistory(userId);
        return ResponseEntity.ok(viewHistory);
    }

    @PostMapping("/comment")
    public ResponseEntity<Comentario> addComment(@RequestBody Comentario comentario) {
        Comentario newComment = commentService.addComment(comentario);
        return ResponseEntity.ok(newComment);
    }

    @PostMapping("/reply")
    public ResponseEntity<Comentario> replyToComment(@RequestParam Integer parentCommentId, @RequestBody Comentario comentario) {
        Comentario replyComment = commentService.replyToComment(parentCommentId, comentario);
        return ResponseEntity.ok(replyComment);
    }

    @GetMapping("/comments/{documentId}")
    public ResponseEntity<List<Comentario>> listDocumentComments(@PathVariable Integer documentId) {
        List<Comentario> comments = commentService.listDocumentComments(documentId);
        return ResponseEntity.ok(comments);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }
}
