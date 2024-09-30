package com.platform.doctic_project.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.doctic_project.Model.HistorialContrasena;
import com.platform.doctic_project.Model.HistorialContrasena.Estado;
import com.platform.doctic_project.Model.Usuario;
import com.platform.doctic_project.Repository.HistorialContrasenaRepository;
import com.platform.doctic_project.Repository.UsuarioRepository;
import com.platform.doctic_project.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private HistorialContrasenaRepository historialContrasenaRepository;

    @Override
    public Usuario createUser(Usuario usuario) {
        if (usuarioRepository.existsByNombreUsuarioOrCorreoElectronico(usuario.getNombreUsuario(),
                usuario.getCorreoElectronico())) {
            throw new IllegalArgumentException("El nombre de usuario o correo electrónico ya están en uso.");
        }

        // Guardar el usuario sin contraseñas
        Usuario nuevoUsuario = usuarioRepository.save(usuario);

        // Guardar la contraseña en el historial
        HistorialContrasena historial = new HistorialContrasena();
        historial.setUsuario(nuevoUsuario);
        historial.setContrasena(usuario.getContrasenaActiva());
        historial.setEstado(Estado.activa);

        historialContrasenaRepository.save(historial);

        return nuevoUsuario;
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
