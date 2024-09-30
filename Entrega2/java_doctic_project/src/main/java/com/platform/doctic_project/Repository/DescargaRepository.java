package com.platform.doctic_project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.platform.doctic_project.Model.Descarga;

@Repository
public interface DescargaRepository extends JpaRepository<Descarga, Integer> {
    List<Descarga> findByUsuarioId(Integer usuarioId);
}
