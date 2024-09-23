package com.platform.doctic_project.Repository;

import com.platform.doctic_project.Model.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    boolean existsByUsernameOrCorreoElectronico(String username, String correoElectronico);

    Optional<Usuario> findByUsername(String username);

}


