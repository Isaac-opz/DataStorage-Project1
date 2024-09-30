package com.platform.doctic_project.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.doctic_project.Exception.RecursoNoEncontradoException;
import com.platform.doctic_project.Model.Categoria;
import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Repository.CategoriaRepository;
import com.platform.doctic_project.Repository.DocumentoRepository;
import com.platform.doctic_project.Service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Override
    public List<Categoria> getAllCategories() {
        return categoriaRepository.findAll();
    }

    @Override
    public void assignCategoryToDocument(Integer documentId, Integer categoryId) {
        // Verificar que el documento exista
        Documento documento = documentoRepository.findById(documentId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Documento no encontrado con ID: " + documentId));

        // Verificar que la categoría exista
        Categoria categoria = categoriaRepository.findById(categoryId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Categoría no encontrada con ID: " + categoryId));

        // Asignar la categoría al documento
        documento.setCategoria(categoria);
        documentoRepository.save(documento);
    }
}
