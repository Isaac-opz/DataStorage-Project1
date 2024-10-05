package com.platform.doctic_project.Controller;

//ESTE ES DE DOCUMENTOS Y CATEGORIAS
import java.util.HashMap;
import java.util.Map; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.doctic_project.Exception.RecursoNoEncontradoException;
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

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Documento> createDocument(@RequestBody Documento documento, @RequestParam Integer userId) {
        Documento newDocument = documentService.createDocument(documento, userId);
        return ResponseEntity.ok(newDocument);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Documento>> getUserDocuments(@PathVariable Integer userId) {
        List<Documento> documents = documentService.listUserDocuments(userId);
        return ResponseEntity.ok(documents);
    }

    @PutMapping("/visibility/{documentId}")
    public ResponseEntity<Map<String, Object>> updateDocumentVisibility(
            @PathVariable Integer documentId, 
            @RequestBody Map<String, String> requestBody) {
        
        // Obtener la visibilidad desde el cuerpo de la solicitud
        String visibility = requestBody.get("visibility");
        
        // Llamar al servicio para actualizar la visibilidad
        documentService.updateDocumentVisibility(documentId, visibility);
        
        // Crear un mapa de respuesta con el mensaje de éxito
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Visibilidad del documento actualizada correctamente");
        
        return ResponseEntity.ok(response);
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
