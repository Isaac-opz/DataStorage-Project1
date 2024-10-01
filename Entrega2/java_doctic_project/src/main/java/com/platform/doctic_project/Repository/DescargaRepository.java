package com.platform.doctic_project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.platform.doctic_project.Model.Descarga;
import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Model.Usuario;

@Repository
public interface DescargaRepository extends JpaRepository<Descarga, Integer> {
    // Obtener descargas por usuario
    List<Descarga> findByUsuario(Usuario usuario);

    // Obtener descargas por documento
    List<Descarga> findByDocumento(Documento documento);

    // Obtener todas las descargas (opcional)
    List<Descarga> findAll();
}
