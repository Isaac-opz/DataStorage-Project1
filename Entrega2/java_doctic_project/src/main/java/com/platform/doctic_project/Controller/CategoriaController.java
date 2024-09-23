package com.platform.doctic_project.Controller;

import com.platform.doctic_project.Model.Categoria;
import com.platform.doctic_project.Service.CategoriaServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriaController {

    @Autowired
    private CategoriaServiceImp categoriaServiceImp;  // Se utiliza la implementación con "Imp"

    @GetMapping("/all")
    public ResponseEntity<List<Categoria>> getAllCategories() {
        List<Categoria> categories = categoriaServiceImp.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/assign")
    public ResponseEntity<String> assignCategoryToDocument(@RequestParam Long documentId, @RequestParam Long categoryId) {
        categoriaServiceImp.assignCategoryToDocument(documentId, categoryId);
        return ResponseEntity.ok("Categoría asignada al documento con éxito.");
    }
}
