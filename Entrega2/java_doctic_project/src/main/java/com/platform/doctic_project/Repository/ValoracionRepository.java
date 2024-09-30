package com.platform.doctic_project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Model.Usuario;
import com.platform.doctic_project.Model.Valoracion;

@Repository
public interface ValoracionRepository extends JpaRepository<Valoracion, Integer> {
    Optional<Valoracion> findByUsuarioAndDocumento(Usuario usuario, Documento documento);
    List<Valoracion> findByDocumento(Documento documento);
}