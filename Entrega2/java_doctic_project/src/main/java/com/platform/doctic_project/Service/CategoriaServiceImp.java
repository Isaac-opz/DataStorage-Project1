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
public class CategoriaServiceImp implements ICategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    // Get all categories
    @Override
    public List<Categoria> getAllCategories() {
        return categoriaRepository.findAll();
    }

    // Assign a category to a document
    @Override
    public Documento assignCategoryToDocument(Integer documentId, Integer categoryId) {
        // Find document by ID
        Optional<Documento> documentoOpt = documentoRepository.findById(documentId);
        if (documentoOpt.isEmpty()) {
            throw new IllegalArgumentException("Documento no encontrado.");
        }

        // Find category by ID
        Optional<Categoria> categoriaOpt = categoriaRepository.findById(categoryId);
        if (categoriaOpt.isEmpty()) {
            throw new IllegalArgumentException("Categoría no encontrada.");
        }

        // Assign category to document and save changes
        Documento documento = documentoOpt.get();
        documento.setCategoria(categoriaOpt.get());
        return documentoRepository.save(documento);
    }
}
