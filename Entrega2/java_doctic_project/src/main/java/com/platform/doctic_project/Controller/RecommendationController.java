package com.platform.doctic_project.Controller;

// ESTE ES DE RECOMENDACIONES Y VALORACIONES

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.doctic_project.Model.Documento;
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
    public ResponseEntity<Void> rateDocument(@RequestParam Integer userId, @RequestParam Integer documentId, @RequestParam Integer stars) {
        ratingService.rateDocument(userId, documentId, stars);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/average/{documentId}")
    public ResponseEntity<Double> getAverageRating(@PathVariable Integer documentId) {
        Double averageRating = ratingService.calculateAverageRating(documentId);
        return ResponseEntity.ok(averageRating);
    }
}
