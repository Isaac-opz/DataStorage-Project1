package com.platform.doctic_project.Service;

import com.platform.doctic_project.Model.Usuario;
import com.platform.doctic_project.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImp implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    

    // 1. Crear un nuevo usuario
    @Override
    public Usuario createUser(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));  // Cifrar contraseña
        return usuarioRepository.save(usuario);
    }

    // 2. Validar la existencia del usuario
    @Override
    public boolean validateUserExistence(String username, String correoElectronico) {
        return usuarioRepository.existsByUsernameOrCorreoElectronico(username, correoElectronico);
    }


    // 5. Actualizar usuario
    @Override
    public Usuario updateUser(Usuario usuario) {
        if (!usuarioRepository.existsById(usuario.getId())) {
            throw new IllegalArgumentException("El usuario no existe.");
        }
        // Lógica adicional para la actualización si es necesario
        return usuarioRepository.save(usuario);
    }
}
