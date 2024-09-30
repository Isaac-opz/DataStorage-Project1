package com.platform.doctic_project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Model.Usuario;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Integer> {
    List<Documento> findByVisibilidad(Documento.Visibilidad visibilidad);
    List<Documento> findByAutores_Usuario(Usuario usuario);
}