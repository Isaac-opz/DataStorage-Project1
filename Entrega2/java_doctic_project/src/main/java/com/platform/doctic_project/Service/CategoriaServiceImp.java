package com.platform.doctic_project.Service;

import com.platform.doctic_project.Model.Categoria;
import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Repository.CategoriaRepository;
import com.platform.doctic_project.Repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImp {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    // 1. Servicio para obtener todas las categorías disponibles
    public List<Categoria> getAllCategories() {
        return categoriaRepository.findAll();
    }

    // 2. Servicio para asignar una categoría a un documento
    public Documento assignCategoryToDocument(Long documentId, Long categoryId) {
        // Verificar si el documento existe
        Optional<Documento> documento = documentoRepository.findById(documentId);
        if (documento.isEmpty()) {
            throw new IllegalArgumentException("Documento no encontrado.");
        }

        // Verificar si la categoría existe
        Optional<Categoria> categoria = categoriaRepository.findById(categoryId);
        if (categoria.isEmpty()) {
            throw new IllegalArgumentException("Categoría no encontrada.");
        }

        // Asignar la categoría al documento y guardar cambios
        documento.get().setCategoria(categoria.get());
        return documentoRepository.save(documento.get());
    }
}
