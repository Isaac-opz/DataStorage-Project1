package com.platform.doctic_project.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;  // IMPORTAR ESTO

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
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
    public ResponseEntity<MappingJacksonValue> addComment(@RequestBody Comentario comentario) {
        Comentario newComment = commentService.addComment(comentario);
    
        // Crear el filtro para Comentario y Documento
        SimpleFilterProvider filters = new SimpleFilterProvider()
                .addFilter("comentarioFilter", SimpleBeanPropertyFilter.serializeAllExcept("metacomentario", "respuestas"))
                .addFilter("documentoFilter", SimpleBeanPropertyFilter.serializeAllExcept("usuario", "comentarios", "categoria"));
    
        // Crear un mapa de respuesta con el mensaje de éxito y el comentario
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Comentario guardado exitosamente");
        response.put("comentario", newComment);
    
        // Aplicar el filtro al mapa completo
        MappingJacksonValue mapping = new MappingJacksonValue(response);
        mapping.setFilters(filters);
    
        return ResponseEntity.ok(mapping);
    }



    @PostMapping("/reply/{parentCommentId}")
    public ResponseEntity<MappingJacksonValue> replyToComment(@PathVariable Integer parentCommentId, @RequestBody Comentario comentario) {
        Comentario newComment = commentService.replyToComment(parentCommentId, comentario);
    
        // Crear el filtro para Comentario y Documento
        SimpleFilterProvider filters = new SimpleFilterProvider()
                .addFilter("comentarioFilter", SimpleBeanPropertyFilter.serializeAllExcept("metacomentario", "respuestas"))
                .addFilter("documentoFilter", SimpleBeanPropertyFilter.serializeAllExcept("usuario", "comentarios", "categoria"));
    
        // Crear un mapa de respuesta con el mensaje de éxito y el comentario
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Respuesta guardada exitosamente");
        response.put("comentario", newComment);
    
        // Aplicar el filtro al mapa completo
        MappingJacksonValue mapping = new MappingJacksonValue(response);
        mapping.setFilters(filters);
    
        return ResponseEntity.ok(mapping);
    }
    

 
    @GetMapping("/comments/{documentId}")
    public ResponseEntity<MappingJacksonValue> listDocumentComments(@PathVariable Integer documentId) {
        // Llamar al servicio para obtener los comentarios del documento
        List<Comentario> comentarios = commentService.listDocumentComments(documentId);

        // Crear el filtro para Comentario y Documento
        SimpleFilterProvider filters = new SimpleFilterProvider()
                .addFilter("comentarioFilter", SimpleBeanPropertyFilter.serializeAllExcept("metacomentario", "respuestas"))
                .addFilter("documentoFilter", SimpleBeanPropertyFilter.serializeAllExcept("usuario", "comentarios", "categoria"));

        // Crear un mapa de respuesta con los comentarios
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Comentarios del documento obtenidos exitosamente");
        response.put("comentarios", comentarios);

        // Aplicar el filtro al mapa completo
        MappingJacksonValue mapping = new MappingJacksonValue(response);
        mapping.setFilters(filters);

        // Devolver la respuesta con el mapa filtrado
        return ResponseEntity.ok(mapping);
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
