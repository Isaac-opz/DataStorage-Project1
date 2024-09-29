package com.platform.doctic_project.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.doctic_project.Exception.RecursoNoEncontradoException;
import com.platform.doctic_project.Model.Descarga;
import com.platform.doctic_project.Service.IDescargaService;

@RestController
@RequestMapping("/api/v1/descargas") // Endpoint base para descargas
public class DescargaController {

    @Autowired
    private IDescargaService descargaService;

    // Endpoint para registrar la descarga de un documento
    @PostMapping("/registrar")
    public ResponseEntity<String> recordDocumentDownload(@RequestParam Integer userId, @RequestParam Integer documentId) {
        try {
            descargaService.recordDocumentDownload(userId, documentId);
            return new ResponseEntity<>("Descarga del documento registrada con éxito.", HttpStatus.OK);
        } catch (RecursoNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

        @GetMapping("/usuario/{userId}")
    public ResponseEntity<?> getUserDownloadHistory(@PathVariable Integer userId) {
        try {
            // Obtén la lista de objetos Descarga
            List<Descarga> descargaList = descargaService.getUserDownloadHistory(userId);
            
            // Mapea la lista de Descarga a una lista de Integer (usando el campo idDescarga)
            List<Integer> downloadHistory = descargaList.stream()
                                                        .map(Descarga::getIdDescarga) // Mapea el ID de la descarga
                                                        .collect(Collectors.toList());

            return ResponseEntity.ok(downloadHistory); // Devuelve la lista de IDs de descarga
        } catch (RecursoNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
