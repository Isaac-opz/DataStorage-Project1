package com.platform.doctic_project.Controller;

import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Exception.RecursoNoEncontradoException;
import com.platform.doctic_project.Service.IDocumentoService;
import com.platform.doctic_project.Service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/documentos") // Endpoint base para documentos
public class DocumentoController {

    @Autowired
    private IDocumentoService documentoService;

    @Autowired
    private ICategoriaService categoriaService;

    // Crear un nuevo documento
    @PostMapping("/crear")
    public ResponseEntity<String> createDocument(@RequestBody DocumentoModel documento) {
        try {
            documentoService.createDocument(documento);
            return new ResponseEntity<>("Documento creado con éxito.", HttpStatus.OK);
        } catch (RecursoNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Listar documentos de un usuario
    @GetMapping("/usuario/{userId}")
    public ResponseEntity<?> listUserDocuments(@PathVariable Long userId) {
        try {
            List<DocumentoModel> documentos = documentoService.listUserDocuments(userId);
            return ResponseEntity.ok(documentos);
        } catch (RecursoNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Obtener todas las categorías
    @GetMapping("/categorias")
    public ResponseEntity<?> getAllCategories() {
        List<String> categories = categoriaService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    // Asignar una categoría a un documento
    @PostMapping("/asignar-categoria")
    public ResponseEntity<String> assignCategoryToDocument(@RequestParam Long documentId, @RequestParam Long categoryId) {
        try {
            documentoService.assignCategoryToDocument(documentId, categoryId);
            return new ResponseEntity<>("Categoría asignada al documento con éxito.", HttpStatus.OK);
        } catch (RecursoNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
