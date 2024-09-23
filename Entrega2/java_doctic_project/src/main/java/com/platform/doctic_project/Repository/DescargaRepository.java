package com.platform.doctic_project.Repository;

import com.platform.doctic_project.Model.Descarga;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface DescargaRepository extends JpaRepository<Descarga, Integer> {

    List<Descarga> findByUsuarioId(Integer userId);

    boolean existsByUsuarioIdAndDocumentoId(Integer userId, Object id);

}


