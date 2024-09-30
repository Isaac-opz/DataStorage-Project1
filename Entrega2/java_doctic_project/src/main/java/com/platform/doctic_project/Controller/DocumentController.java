package com.platform.doctic_project.Controller;

//ESTE ES DE DOCUMENTOS Y CATEGORIAS

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.doctic_project.Model.Categoria;
import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Service.CategoryService;
import com.platform.doctic_project.Service.DocumentService;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<Documento> createDocument(@RequestBody Documento documento) {
        Documento newDocument = documentService.createDocument(documento);
        return ResponseEntity.ok(newDocument);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Documento>> getUserDocuments(@PathVariable Integer userId) {
        List<Documento> documents = documentService.listUserDocuments(userId);
        return ResponseEntity.ok(documents);
    }

    @PutMapping("/visibility/{documentId}")
    public ResponseEntity<Void> updateDocumentVisibility(@PathVariable Integer documentId, @RequestParam String visibility) {
        documentService.updateDocumentVisibility(documentId, visibility);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Categoria>> getAllCategories() {
        List<Categoria> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/assign-category")
    public ResponseEntity<Void> assignCategoryToDocument(@RequestParam Integer documentId, @RequestParam Integer categoryId) {
        categoryService.assignCategoryToDocument(documentId, categoryId);
        return ResponseEntity.ok().build();
    }
}
