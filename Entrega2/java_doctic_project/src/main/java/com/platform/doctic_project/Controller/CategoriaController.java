package com.platform.doctic_project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.doctic_project.Model.Categoria;
import com.platform.doctic_project.Service.CategoriaServiceImp;

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
    public ResponseEntity<String> assignCategoryToDocument(@RequestParam Integer documentId, @RequestParam Integer categoryId) {
        categoriaServiceImp.assignCategoryToDocument(documentId, categoryId);
        return ResponseEntity.ok("Categoría asignada al documento con éxito.");
    }
}
