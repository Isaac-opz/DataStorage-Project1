package com.platform.doctic_project.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.doctic_project.Model.Categoria;
import com.platform.doctic_project.Repository.CategoriaRepository;
import com.platform.doctic_project.Service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> getAllCategories() {
        // Lógica para obtener todas las categorías
        return categoriaRepository.findAll();
    }

    @Override
    public void assignCategoryToDocument(Integer documentId, Integer categoryId) {
        // Lógica para asignar categoría a un documento
    }
}
