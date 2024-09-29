package com.platform.doctic_project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.doctic_project.Service.IValoracionService;

@RestController
@RequestMapping("/api/v1/valoraciones") // Endpoint base para valoraciones
public class ValoracionController {

    @Autowired
    private IValoracionService valoracionService; // Usamos la interfaz

    // Añadir una valoración a un documento
    @PostMapping("/valorar")
    public ResponseEntity<String> rateDocument(@RequestParam Integer documentId, @RequestParam Integer userId, @RequestParam int rating) {
        try {
            valoracionService.rateDocument(documentId, userId, rating);
            return new ResponseEntity<>("Valoración añadida con éxito.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Calcular la valoración promedio de un documento
    @GetMapping("/promedio/{documentId}")
    public ResponseEntity<?> calculateAverageRating(@PathVariable Integer documentId) {
        try {
            double averageRating = valoracionService.calculateAverageRating(documentId);
            return ResponseEntity.ok(averageRating);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
