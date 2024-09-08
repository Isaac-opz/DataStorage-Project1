package com.platform.doctic_project.Controller;

import com.platform.doctic_project.Model.DocumentoModel;
import com.platform.doctic_project.Service.DocumentoService;
import com.platform.doctic_project.Service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("/create")
    public ResponseEntity<String> createDocument(@RequestBody DocumentoModel documento) {
        documentoService.createDocument(documento);
        return ResponseEntity.ok("Documento creado con éxito.");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DocumentoModel>> listUserDocuments(@PathVariable Long userId) {
        List<DocumentoModel> documentos = documentoService.listUserDocuments(userId);
        return ResponseEntity.ok(documentos);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories() {
        List<String> categories = categoriaService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/assign-category")
    public ResponseEntity<String> assignCategoryToDocument(@RequestParam Long documentId, @RequestParam Long categoryId) {
        documentoService.assignCategoryToDocument(documentId, categoryId);
        return ResponseEntity.ok("Categoría asignada al documento con éxito.");
    }
}
