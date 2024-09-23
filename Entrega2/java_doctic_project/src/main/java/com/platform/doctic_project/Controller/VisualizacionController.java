package com.platform.doctic_project.Controller;

import com.platform.doctic_project.Service.IVisualizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/visualizaciones") // Endpoint base para visualizaciones
public class VisualizacionController {

    @Autowired
    private IVisualizacionService visualizacionService; // Usamos la interfaz

    // Registrar la visualización de un documento
    @PostMapping("/registrar")
    public ResponseEntity<String> recordDocumentView(@RequestParam Long userId, @RequestParam Long documentId) {
        try {
            visualizacionService.recordDocumentView(userId, documentId);
            return new ResponseEntity<>("Visualización del documento registrada con éxito.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtener el historial de visualizaciones de un usuario
    @GetMapping("/usuario/{userId}")
    public ResponseEntity<?> getUserViewHistory(@PathVariable Long userId) {
        try {
            List<Long> viewHistory = visualizacionService.getUserViewHistory(userId);
            return ResponseEntity.ok(viewHistory);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
