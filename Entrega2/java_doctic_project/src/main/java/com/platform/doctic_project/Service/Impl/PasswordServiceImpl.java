package com.platform.doctic_project.Service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.doctic_project.Exception.RecursoNoEncontradoException;
import com.platform.doctic_project.Model.HistorialContrasena;
import com.platform.doctic_project.Model.HistorialContrasena.Estado;
import com.platform.doctic_project.Model.Usuario;
import com.platform.doctic_project.Repository.HistorialContrasenaRepository;
import com.platform.doctic_project.Repository.UsuarioRepository;
import com.platform.doctic_project.Service.PasswordService;

@Service
public class PasswordServiceImpl implements PasswordService {

    @Autowired
    private HistorialContrasenaRepository historialContrasenaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public boolean changePassword(String nombreUsuario, String contrasenaActual, String respuestaSecreta, String nuevaContrasena) {
        // Verificar si el usuario existe
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado."));

        // Verificar la contraseña actual desde el historial de contraseñas
        Optional<HistorialContrasena> contrasenaActivaOpt = historialContrasenaRepository.findByUsuarioAndEstado(usuario, Estado.activa);
        if (contrasenaActivaOpt.isPresent()) {
            HistorialContrasena contrasenaActiva = contrasenaActivaOpt.get();
            if (!contrasenaActiva.getContrasena().equals(contrasenaActual)) {
                throw new IllegalArgumentException("La contraseña actual es incorrecta.");
            }
        } else {
            throw new IllegalArgumentException("No se encontró una contraseña activa para el usuario.");
        }

        // Verificar la respuesta secreta
        if (!usuario.getRespuestaSecreta().equals(respuestaSecreta)) {
            throw new IllegalArgumentException("La respuesta secreta es incorrecta.");
        }

        // Desactivar la contraseña actual en el historial de contraseñas
        HistorialContrasena contrasenaActiva = contrasenaActivaOpt.get();
        contrasenaActiva.setEstado(HistorialContrasena.Estado.inactiva);  // Desactivar la contraseña actual
        historialContrasenaRepository.save(contrasenaActiva);  // Guardar el historial actualizado

        // Registrar la nueva contraseña en el historial de contraseñas
        HistorialContrasena nuevoHistorial = new HistorialContrasena(usuario, nuevaContrasena, HistorialContrasena.Estado.activa);
        historialContrasenaRepository.save(nuevoHistorial);  // Guardar la nueva contraseña en el historial

        return true;
    }




    @Override
    public void savePasswordToHistory(Integer userId, String password) {
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con ID: " + userId));

        // Verificar si la contraseña ya ha sido usada
        if (checkPasswordHistory(userId, password)) {
            throw new IllegalArgumentException("La contraseña ya ha sido utilizada anteriormente.");
        }

        // Establecer todas las contraseñas anteriores como inactivas
        historialContrasenaRepository.findByUsuarioAndEstado(usuario, Estado.activa).ifPresent(contrasenaActiva -> {
            contrasenaActiva.setEstado(Estado.inactiva);
            historialContrasenaRepository.save(contrasenaActiva);
        });

        // Guardar la nueva contraseña como activa
        HistorialContrasena nuevaContrasena = new HistorialContrasena();
        nuevaContrasena.setUsuario(usuario);
        nuevaContrasena.setContrasena(password);
        nuevaContrasena.setEstado(Estado.activa);
        historialContrasenaRepository.save(nuevaContrasena);
    }

    @Override
    public boolean checkPasswordHistory(Integer userId, String password) {
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado con ID: " + userId));

        return historialContrasenaRepository.existsByUsuarioAndContrasena(usuario, password);
    }

    // Métodos auxiliares
    private String generarContrasenaTemporal() {
        // Generar una contraseña temporal (implementación simplificada)
        return "nueva_contrasena123";
    }

    private void enviarCorreoRecuperacion(String correoElectronico, String nuevaContrasena) {
        // Implementación simulada de envío de correo electrónico
        System.out.println("Enviando correo a " + correoElectronico + " con la nueva contraseña: " + nuevaContrasena);
    }
}