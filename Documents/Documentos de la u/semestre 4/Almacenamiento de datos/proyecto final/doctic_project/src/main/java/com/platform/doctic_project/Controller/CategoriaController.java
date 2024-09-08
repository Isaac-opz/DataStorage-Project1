package com.platform.doctic_project.Controller;

import com.platform.doctic_project.Service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllCategories() {
        List<String> categories = categoriaService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/assign")
    public ResponseEntity<String> assignCategoryToDocument(@RequestParam Long documentId, @RequestParam Long categoryId) {
        categoriaService.assignCategoryToDocument(documentId, categoryId);
        return ResponseEntity.ok("Categoría asignada al documento con éxito.");
    }
}
