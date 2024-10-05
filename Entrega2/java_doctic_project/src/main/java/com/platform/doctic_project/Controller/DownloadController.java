package com.platform.doctic_project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.platform.doctic_project.Exception.RecursoNoEncontradoException;
import com.platform.doctic_project.Model.Descarga;
import com.platform.doctic_project.Service.DownloadService;


@RestController
@RequestMapping("/api/downloads")
public class DownloadController {

    @Autowired
    private DownloadService downloadService;

    @PostMapping("/{documentId}/{userId}")
    public ResponseEntity<String> recordDocumentDownload(@PathVariable Integer documentId, @PathVariable Integer userId) {
        try {
            downloadService.recordDocumentDownload(documentId, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Descarga registrada con éxito.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (RecursoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("/history/{userId}")
    public ResponseEntity<List<Descarga>> getUserDownloadHistory(@PathVariable Integer userId) {
        List<Descarga> downloadHistory = downloadService.getUserDownloadHistory(userId);
        return ResponseEntity.ok(downloadHistory);
    }
}


