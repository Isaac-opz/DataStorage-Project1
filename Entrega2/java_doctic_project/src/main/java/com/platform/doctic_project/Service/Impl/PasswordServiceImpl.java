package com.platform.doctic_project.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.doctic_project.Repository.HistorialContrasenaRepository;
import com.platform.doctic_project.Service.PasswordService;

@Service
public class PasswordServiceImpl implements PasswordService {

    @Autowired
    private HistorialContrasenaRepository historialContrasenaRepository;

    @Override
    public boolean recoverPassword(String username, String respuestaSecreta) {
        // Lógica para recuperar contraseña
        return false; // Implementa la lógica aquí
    }

    @Override
    public void savePasswordToHistory(Integer userId, String password) {
        // Lógica para guardar una contraseña en el historial
    }

    @Override
    public boolean checkPasswordHistory(Integer userId, String password) {
        // Lógica para verificar si una contraseña ya ha sido usada
        return false;
    }
}
