package com.platform.doctic_project.Controller;

import com.platform.doctic_project.Service.RecomendacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecomendacionController {

    @Autowired
    private RecomendacionService recomendacionService;

    @GetMapping("/generate/{userId}")
    public ResponseEntity<List<Long>> generateDocumentRecommendations(@PathVariable Long userId) {
        List<Long> recommendations = recomendacionService.generateDocumentRecommendations(userId);
        return ResponseEntity.ok(recommendations);
    }
}
