package com.platform.doctic_project.Controller;

import com.platform.doctic_project.Service.ValoracionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ratings")
public class ValoracionController {

    @Autowired
    private ValoracionService valoracionService;

    @PostMapping("/rate")
    public ResponseEntity<String> rateDocument(@RequestParam Long documentId, @RequestParam Long userId, @RequestParam int rating) {
        valoracionService.rateDocument(documentId, userId, rating);
        return ResponseEntity.ok("Valoración añadida con éxito.");
    }

    @GetMapping("/average/{documentId}")
    public ResponseEntity<Double> calculateAverageRating(@PathVariable Long documentId) {
        double averageRating = valoracionService.calculateAverageRating(documentId);
        return ResponseEntity.ok(averageRating);
    }
}
