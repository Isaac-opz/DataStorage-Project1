package com.platform.doctic_project.Controller;

import java.util.HashMap;

// ESTE ES DE RECOMENDACIONES Y VALORACIONES

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platform.doctic_project.Exception.RecursoNoEncontradoException;
import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Model.Valoracion;
import com.platform.doctic_project.Service.RatingService;
import com.platform.doctic_project.Service.RecommendationService;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private RatingService ratingService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Documento>> getRecommendations(@PathVariable Integer userId) {
        List<Documento> recommendations = recommendationService.generateDocumentRecommendations(userId);
        return ResponseEntity.ok(recommendations);
    }

    @PostMapping("/rate")
    public ResponseEntity<Map<String, String>> rateDocument(@RequestBody Map<String, Object> payload) {
        Integer userId = (Integer) payload.get("userId");
        Integer documentId = (Integer) payload.get("documentId");
        Integer stars = (Integer) payload.get("stars");

        ratingService.rateDocument(userId, documentId, stars);

        // Crear un mapa con el mensaje de éxito
        Map<String, String> response = new HashMap<>();
        response.put("message", "Documento reseñado correctamente");

        // Devolver el mensaje en la respuesta
        return ResponseEntity.ok(response);
    }
    @GetMapping("/ratings/{documentId}")
    public ResponseEntity<?> listDocumentRatings(@PathVariable Integer documentId) {
        try {
            List<Valoracion> valoraciones = ratingService.listDocumentRatings(documentId);
            return ResponseEntity.ok(valoraciones);
        } catch (RecursoNoEncontradoException ex) {
            // Si el documento no tiene valoraciones, devolver el mensaje personalizado
            Map<String, String> response = new HashMap<>();
            response.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}
