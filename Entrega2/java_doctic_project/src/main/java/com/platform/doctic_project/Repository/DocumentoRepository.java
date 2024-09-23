package com.platform.doctic_project.Repository;

import com.platform.doctic_project.Model.Documento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface DocumentoRepository extends JpaRepository<Documento, Integer> {

    List<Documento> findByUsuarioNombreUsuario(String autorNombre);

    List<Documento> findByNombreDocumentoContainingOrUsuarioNombreUsuarioContaining(String searchQuery,
            String searchQuery2);

}


