package com.platform.doctic_project.Repository;

import com.platform.doctic_project.Model.Descarga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DescargaRepository extends JpaRepository<Descarga, Integer> {
    List<Descarga> findByUsuarioId(Integer userId);
    boolean existsByUsuarioIdAndDocumentoId(Integer userId, Integer documentoId);  // Método adicional
}
