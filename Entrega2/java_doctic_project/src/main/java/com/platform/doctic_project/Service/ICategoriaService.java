package com.platform.doctic_project.Service;

import java.util.List;

import com.platform.doctic_project.Model.Categoria;
import com.platform.doctic_project.Model.Documento;

public interface ICategoriaService {
    List<Categoria> getAllCategories();
    Documento assignCategoryToDocument(Integer documentId, Integer categoryId);
}
