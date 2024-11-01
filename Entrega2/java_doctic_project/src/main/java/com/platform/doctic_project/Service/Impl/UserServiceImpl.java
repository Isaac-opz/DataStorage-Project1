package com.platform.doctic_project.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List; // Importa la lista correctamente desde java.util
import java.util.Optional;

import com.platform.doctic_project.Exception.RecursoNoEncontradoException;
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
    public Usuario createUser(Usuario usuario, String contrasena) {
        // Verificar si el nombre de usuario o el correo electrónico ya están en uso
        if (usuarioRepository.existsByNombreUsuarioOrCorreoElectronico(usuario.getNombreUsuario(), usuario.getCorreoElectronico())) {
            throw new IllegalArgumentException("El nombre de usuario o correo electrónico ya están en uso.");
        }

        // Verificar si la contraseña proporcionada es válida
        if (contrasena == null || contrasena.isEmpty()) {
            throw new IllegalArgumentException("La contraseña inicial no puede estar vacía.");
        }

        // Guardar el nuevo usuario sin la contraseña (porque la contraseña se gestiona en el historial)
        Usuario nuevoUsuario = usuarioRepository.save(usuario);

        // Crear y guardar el historial de contraseñas con la contraseña proporcionada
        HistorialContrasena historial = new HistorialContrasena();
        historial.setUsuario(nuevoUsuario);
        historial.setContrasena(contrasena); // Asignar la contraseña desde el argumento proporcionado
        historial.setEstado(HistorialContrasena.Estado.activa); // Establecer como contraseña activa
        historial.setFechaCambio(LocalDateTime.now()); // Establecer la fecha de creación

        // Guardar el historial de contraseñas
        historialContrasenaRepository.save(historial);

        return nuevoUsuario;
    }



    @Override
    public Usuario authenticateUser(String nombreUsuario, String contrasena) {
        // Verificar si el usuario existe
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado."));

        // Verificar si la contraseña ingresada es la activa
        Optional<HistorialContrasena> contrasenaActivaOpt = historialContrasenaRepository.findByUsuarioAndEstado(usuario, HistorialContrasena.Estado.activa);
        if (contrasenaActivaOpt.isPresent()) {
            HistorialContrasena contrasenaActiva = contrasenaActivaOpt.get();
            
            // Comparar la contraseña ingresada con la contraseña activa
            if (contrasenaActiva.getContrasena().equals(contrasena)) {
                return usuario;
            } else {
                throw new IllegalArgumentException("La contraseña ingresada es incorrecta.");
            }
        } else {
            throw new IllegalArgumentException("No se encontró una contraseña activa para este usuario.");
        }
    }

    @Override
    public boolean existsByUsername(String username) {
        return usuarioRepository.existsByNombreUsuario(username); // Utiliza el repositorio para verificar
    }

    @Override
    public List<Usuario> getAllUsers() {
        return usuarioRepository.findAll(); // Devuelve todos los usuarios
    }
}
