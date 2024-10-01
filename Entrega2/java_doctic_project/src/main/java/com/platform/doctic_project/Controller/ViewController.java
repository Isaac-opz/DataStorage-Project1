package com.platform.doctic_project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.platform.doctic_project.Service.ViewService;
import com.platform.doctic_project.Model.VistoPor;

import java.util.List;

@RestController
@RequestMapping("/api/views")
public class ViewController {

    @Autowired
    private ViewService viewService;

    @PostMapping("/record")
    public ResponseEntity<String> recordDocumentView(@RequestParam Integer documentId, @RequestParam Integer userId) {
        viewService.recordDocumentView(documentId, userId);
        return ResponseEntity.ok("Visualización registrada con éxito.");
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<VistoPor>> getUserViewHistory(@PathVariable Integer userId) {
        List<VistoPor> history = viewService.getUserViewHistory(userId);
        return ResponseEntity.ok(history);
    }
}
