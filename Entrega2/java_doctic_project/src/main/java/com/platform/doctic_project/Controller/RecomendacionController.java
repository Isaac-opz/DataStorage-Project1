package com.platform.doctic_project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Service.RecomendacionServiceImp;

@RestController
@RequestMapping("/api/v1/recomendaciones") // Endpoints base para recomendaciones
public class RecomendacionController {

    @Autowired
    private RecomendacionServiceImp recomendacionService; // Usamos la implementación Imp

    // Generar recomendaciones de documentos para un usuario
    @GetMapping("/generar/{userId}")
    public ResponseEntity<?> generateDocumentRecommendations(@PathVariable Integer userId) {
        try {
            List<Documento> recomendaciones = recomendacionService.generateDocumentRecommendations(userId);
            return ResponseEntity.ok(recomendaciones);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
