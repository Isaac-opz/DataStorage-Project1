package com.platform.doctic_project.Controller;

import java.util.HashMap;
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
import java.util.Map;  // IMPORTAR ESTO

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
    public ResponseEntity<Map<String, Object>> getUserDownloadHistory(@PathVariable Integer userId) {
        List<Descarga> downloadHistory = downloadService.getUserDownloadHistory(userId);

        // Crear el mapa de respuesta
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Historial de descargas obtenido exitosamente");
        response.put("historialDescargas", downloadHistory);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/view")
    public ResponseEntity<Void> recordDocumentView(@RequestParam Integer documentId, @RequestParam Integer userId) {
        viewService.recordDocumentView(documentId, userId);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/views/{userId}")
    public ResponseEntity<Map<String, Object>> getUserViewHistory(@PathVariable Integer userId) {
        List<VistoPor> viewHistory = viewService.getUserViewHistory(userId);

        // Crear el mapa de respuesta
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Historial de vistas obtenido exitosamente");
        response.put("historialVistas", viewHistory);

        return ResponseEntity.ok(response);
    }


    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addComment(@RequestBody Comentario comentario) {
        Comentario newComment = commentService.addComment(comentario);

        // Crear un mapa de respuesta con el mensaje de éxito y el comentario
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Comentario guardado exitosamente");
        response.put("comentario", newComment);

        return ResponseEntity.ok(response);
    }



    @PostMapping("/reply/{parentCommentId}")
    public ResponseEntity<Map<String, Object>> replyToComment(@PathVariable Integer parentCommentId, @RequestBody Comentario comentario) {
        Comentario newComment = commentService.replyToComment(parentCommentId, comentario);

        // Crear un mapa de respuesta con el mensaje de éxito y el comentario
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Respuesta guardada exitosamente");
        response.put("comentario", newComment);

        return ResponseEntity.ok(response);
    }

    
    @GetMapping("/comments/{documentId}")
    public ResponseEntity<Map<String, Object>> listDocumentComments(@PathVariable Integer documentId) {
        // Llamar al servicio para obtener los comentarios del documento
        List<Comentario> comentarios = commentService.listDocumentComments(documentId);

        // Crear un mapa de respuesta con el mensaje de éxito y la lista de comentarios
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Comentarios del documento obtenidos exitosamente");
        response.put("comentarios", comentarios);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<Map<String, Object>> deleteComment(@PathVariable Integer commentId) {
        // Llamar al servicio para eliminar el comentario
        commentService.deleteComment(commentId);

        // Crear un mapa de respuesta con el mensaje de éxito
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Comentario eliminado exitosamente");

        return ResponseEntity.ok(response);
    }

}
