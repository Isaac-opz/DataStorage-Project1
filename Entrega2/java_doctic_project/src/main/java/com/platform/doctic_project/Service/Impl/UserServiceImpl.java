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
        // Lógica para registrar un usuario
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario authenticateUser(String username, String password) {
        // Lógica para autenticar un usuario
        return null; // Implementa la lógica aquí
    }
}
