package com.platform.doctic_project.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.doctic_project.Model.Usuario;
import com.platform.doctic_project.Repository.UsuarioRepository;
import com.platform.doctic_project.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario createUser(Usuario usuario) {
        if (usuarioRepository.existsByNombreUsuarioOrCorreoElectronico(usuario.getNombreUsuario(), usuario.getCorreoElectronico())) {
            throw new IllegalArgumentException("El nombre de usuario o correo electrónico ya están en uso.");
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario authenticateUser(String nombreUsuario, String contrasena) {
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado."));
        String contrasenaActiva = usuario.getContrasenaActiva();
        if (contrasenaActiva != null && contrasenaActiva.equals(contrasena)) {
            return usuario;
        } else {
            throw new IllegalArgumentException("Contraseña incorrecta.");
        }
    }
}
