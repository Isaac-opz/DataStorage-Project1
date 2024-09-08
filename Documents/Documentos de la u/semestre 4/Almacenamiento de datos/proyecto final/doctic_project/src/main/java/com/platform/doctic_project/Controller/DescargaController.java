package com.platform.doctic_project.Controller;

import com.platform.doctic_project.Service.DescargaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/downloads")
public class DescargaController {

    @Autowired
    private DescargaService descargaService;

    @PostMapping("/record")
    public ResponseEntity<String> recordDocumentDownload(@RequestParam Long userId, @RequestParam Long documentId) {
        descargaService.recordDocumentDownload(userId, documentId);
        return ResponseEntity.ok("Descarga del documento registrada con éxito.");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Long>> getUserDownloadHistory(@PathVariable Long userId) {
        List<Long> downloadHistory = descargaService.getUserDownloadHistory(userId);
        return ResponseEntity.ok(downloadHistory);
    }
}
