package com.backend.doctic_mongo.Controller;
import com.backend.doctic_mongo.Service.IDocumentoService;
import com.backend.doctic_mongo.Model.DocumentoModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/documentos")
public class DocumentoController {

    @Autowired
    private IDocumentoService documentoService;

    @PostMapping("/publicar")
    public ResponseEntity<DocumentoModel> publicarDocumento(@RequestBody DocumentoModel documento) {
        DocumentoModel nuevoDocumento = documentoService.publicarDocumento(documento);
        return new ResponseEntity<>(nuevoDocumento, HttpStatus.CREATED);
    }
}
