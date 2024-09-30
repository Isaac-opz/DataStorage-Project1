package com.platform.doctic_project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.platform.doctic_project.Model.Documento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Integer> {
    List<Documento> findByVisibilidad(String visibilidad);

    List<Documento> findByNombreDocumentoContainingOrUsuarioNombreUsuarioContaining(String searchQuery, String searchQuery2);
}
