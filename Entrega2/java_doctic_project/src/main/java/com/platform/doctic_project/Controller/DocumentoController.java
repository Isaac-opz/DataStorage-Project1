package com.platform.doctic_project.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.doctic_project.Exception.RecursoNoEncontradoException;
import com.platform.doctic_project.Model.Categoria;
import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Service.ICategoriaService;
import com.platform.doctic_project.Service.IDocumentoService;

@RestController
@RequestMapping("/api/v1/documentos") // Endpoint base para documentos
public class DocumentoController {

    @Autowired
    private IDocumentoService documentoService;

    @Autowired
    private ICategoriaService categoriaService;

    // Crear un nuevo documento
    @PostMapping("/crear/{userId}")
    public ResponseEntity<String> createDocument(@RequestBody Documento documento, @PathVariable Integer userId) {
        try {
            // Crear el documento pasando el ID del usuario
            documentoService.createDocument(documento, userId);
            return new ResponseEntity<>("Documento creado con éxito.", HttpStatus.OK);
        } catch (RecursoNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    // Listar documentos de un usuario
    @GetMapping("/usuario/{userId}")
    public ResponseEntity<?> listUserDocuments(@PathVariable Integer userId) {
        try {
            List<Documento> documentos = documentoService.listUserDocuments(userId);
            return ResponseEntity.ok(documentos);
        } catch (RecursoNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/categorias")
    public ResponseEntity<?> getAllCategories() {
        // Obtiene la lista de objetos Categoria
        List<Categoria> categoriaList = categoriaService.getAllCategories();
        
        // Mapea la lista de Categoria a una lista de String usando el campo 'categoria'
        List<String> categories = categoriaList.stream()
                                            .map(Categoria::getCategoria) // Usa el campo 'categoria' de la clase Categoria
                                            .collect(Collectors.toList());

        // Retorna la lista de nombres de categorías
        return ResponseEntity.ok(categories);
    }


    // Asignar una categoría a un documento
    @PostMapping("/asignar-categoria")
    public ResponseEntity<String> assignCategoryToDocument(@RequestParam Integer documentId, @RequestParam Integer categoryId) {
        try {
            documentoService.assignCategoryToDocument(documentId, categoryId);
            return new ResponseEntity<>("Categoría asignada al documento con éxito.", HttpStatus.OK);
        } catch (RecursoNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
