package com.platform.doctic_project.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Model.Usuario;
import com.platform.doctic_project.Model.VistoPor;

@Repository
public interface VistoPorRepository extends JpaRepository<VistoPor, Integer> {
    boolean existsByUsuarioAndDocumento(Usuario usuario, Documento documento);
    List<VistoPor> findByUsuario(Usuario usuario);
}