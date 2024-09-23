package com.platform.doctic_project.Controller;

import com.platform.doctic_project.Exception.RecursoNoEncontradoException;
import com.platform.doctic_project.Service.IDescargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/descargas") // Endpoint base para descargas
public class DescargaController {

    @Autowired
    private IDescargaService descargaService;

    // Endpoint para registrar la descarga de un documento
    @PostMapping("/registrar")
    public ResponseEntity<String> recordDocumentDownload(@RequestParam Long userId, @RequestParam Long documentId) {
        try {
            descargaService.recordDocumentDownload(userId, documentId);
            return new ResponseEntity<>("Descarga del documento registrada con éxito.", HttpStatus.OK);
        } catch (RecursoNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para obtener el historial de descargas de un usuario
    @GetMapping("/usuario/{userId}")
    public ResponseEntity<?> getUserDownloadHistory(@PathVariable Long userId) {
        try {
            List<Long> downloadHistory = descargaService.getUserDownloadHistory(userId);
            return ResponseEntity.ok(downloadHistory);
        } catch (RecursoNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
