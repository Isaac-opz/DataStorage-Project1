package com.platform.doctic_project.Controller;

import com.platform.doctic_project.Service.VisualizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/views")
public class VisualizacionController {

    @Autowired
    private VisualizacionService visualizacionService;

    @PostMapping("/record")
    public ResponseEntity<String> recordDocumentView(@RequestParam Long userId, @RequestParam Long documentId) {
        visualizacionService.recordDocumentView(userId, documentId);
        return ResponseEntity.ok("Visualización del documento registrada con éxito.");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Long>> getUserViewHistory(@PathVariable Long userId) {
        List<Long> viewHistory = visualizacionService.getUserViewHistory(userId);
        return ResponseEntity.ok(viewHistory);
    }
}
