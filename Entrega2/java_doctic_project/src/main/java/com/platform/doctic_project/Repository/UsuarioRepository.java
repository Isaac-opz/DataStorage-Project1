package com.platform.doctic_project.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.platform.doctic_project.Model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    Optional<Usuario> findByCorreoElectronico(String correoElectronico);

    boolean existsByNombreUsuario(String nombreUsuario);  // Método para verificar si existe el nombre de usuario

    boolean existsByNombreUsuarioOrCorreoElectronico(String nombreUsuario, String correoElectronico);
}
