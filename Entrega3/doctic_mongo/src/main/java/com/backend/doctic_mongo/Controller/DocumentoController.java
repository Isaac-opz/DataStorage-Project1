package com.backend.doctic_mongo.Controller;

import com.backend.doctic_mongo.Model.DocumentoModel;
import com.backend.doctic_mongo.Service.IDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/documentos")
public class DocumentoController {

    @Autowired
    private IDocumentoService documentoService;

    @PostMapping("/publicar")
    public ResponseEntity<String> publicarDocumento(@RequestBody DocumentoDTO documentoDTO) {
        documentoService.publicarDocumento(documentoDTO);
        return ResponseEntity.ok("Documento publicado con éxito.");
    }

    @GetMapping("/descargar/{documentoId}")
    public ResponseEntity<DocumentoModel> descargarDocumento(@PathVariable String documentoId, @RequestParam String usuarioId) {
        DocumentoModel documento = documentoService.descargarDocumento(documentoId, usuarioId);
        return ResponseEntity.ok(documento);
    }
}
