package com.platform.doctic_project.Service;

import java.util.List;

import com.platform.doctic_project.Model.Categoria;

public interface CategoryService {
    List<Categoria> getAllCategories();
    void assignCategoryToDocument(Integer documentId, Integer categoryId);
}
