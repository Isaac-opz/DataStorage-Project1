package com.backend.doctic_mongo.Controller;

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

import com.backend.doctic_mongo.DTO.DocumentoDTO;
import com.backend.doctic_mongo.Exceptions.CustomException;
import com.backend.doctic_mongo.Model.DocumentoModel;
import com.backend.doctic_mongo.Service.IDocumentoService;

@RestController
@RequestMapping("/api/documentos")
public class DocumentoController {

    @Autowired
    private IDocumentoService documentoService;

    // Endpoint para publicar un documento
    @PostMapping("/publicar")
    public ResponseEntity<String> publicarDocumento(@RequestBody DocumentoDTO documentoDTO) {
        try {
            documentoService.publicarDocumento(documentoDTO);
            return ResponseEntity.ok("Documento publicado con éxito.");
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al publicar el documento.");
        }
    }

    // Endpoint para descargar un documento
    @GetMapping("/descargar/{documentoId}") 
    public ResponseEntity<?> descargarDocumento(@PathVariable String documentoId, @RequestParam String usuarioId) {
        try {
            DocumentoModel documento = documentoService.descargarDocumento(documentoId, usuarioId);
            return ResponseEntity.ok(documento);
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al descargar el documento.");
        }
    }

    @PutMapping("/actualizarVisibilidad/{documentoId}")
    public ResponseEntity<String> actualizarVisibilidad(@PathVariable String documentoId, @RequestParam String nuevaVisibilidad) {
        try {
            documentoService.actualizarVisibilidad(documentoId, nuevaVisibilidad);
            return ResponseEntity.ok("Visibilidad del documento actualizada con éxito.");
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar la visibilidad del documento.");
        }
    }

    @PutMapping("/actualizar/{documentoId}")
    public ResponseEntity<String> actualizarDocumento(@PathVariable String documentoId, @RequestBody DocumentoDTO documentoDTO) {
        try {
            documentoService.actualizarDocumento(documentoId, documentoDTO);
            return ResponseEntity.ok("Documento actualizado con éxito.");
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el documento.");
        }
    }


}
