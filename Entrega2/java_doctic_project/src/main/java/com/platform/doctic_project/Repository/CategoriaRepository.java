package com.platform.doctic_project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.platform.doctic_project.Model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
