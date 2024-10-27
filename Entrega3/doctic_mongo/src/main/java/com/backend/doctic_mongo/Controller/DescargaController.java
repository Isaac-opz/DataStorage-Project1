package com.backend.doctic_mongo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.doctic_mongo.Service.IDescargaService;

@RestController
@RequestMapping("/api/descargas")
public class DescargaController {

    @Autowired
    private IDescargaService descargaService;

    @PostMapping("/descargar/{documentoId}")
    public ResponseEntity<String> descargarDocumento(@PathVariable String documentoId, @RequestBody String usuarioId) {
        String url = descargaService.registrarDescarga(documentoId, usuarioId);
        return ResponseEntity.ok(url);
    }
}
