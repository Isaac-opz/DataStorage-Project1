package com.platform.doctic_project.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.platform.doctic_project.Model.HistorialContrasena;
import com.platform.doctic_project.Model.Usuario;
import com.platform.doctic_project.Model.HistorialContrasena.Estado;

@Repository
public interface HistorialContrasenaRepository extends JpaRepository<HistorialContrasena, Integer> {
    List<HistorialContrasena> findByUsuario(Usuario usuario);
    Optional<HistorialContrasena> findByUsuarioAndEstado(Usuario usuario, Estado estado);
    boolean existsByUsuarioAndContrasena(Usuario usuario, String contrasena);
}