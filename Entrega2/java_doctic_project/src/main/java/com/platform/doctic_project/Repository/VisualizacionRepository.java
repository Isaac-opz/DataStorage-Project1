package com.platform.doctic_project.Repository;

import com.platform.doctic_project.Model.VistoPor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VisualizacionRepository extends JpaRepository<VistoPor, Integer> {
    List<VistoPor> findByUsuarioId(Integer userId);  // Obtener el historial de visualizaciones de un usuario
    boolean existsByUsuarioIdAndDocumentoId(Integer userId, Integer documentoId);  // Verificar si un documento ha sido visto
}
