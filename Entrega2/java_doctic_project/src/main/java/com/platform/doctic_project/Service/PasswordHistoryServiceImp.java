package com.platform.doctic_project.Service;

import com.platform.doctic_project.Model.Contrasena;
import com.platform.doctic_project.Repository.ContrasenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PasswordHistoryServiceImp implements IPasswordHistoryService {

    @Autowired
    private ContrasenaRepository contrasenaRepository;

    @Autowired
    private AuthService authService;

    // 1. Guardar contraseña en el historial
    @Override
    public void savePasswordToHistory(Integer userId, String newPassword) {
        String encodedPassword = authService.encodePassword(newPassword); // Cifrar la contraseña antes de guardarla
        Contrasena contrasena = new Contrasena();
        contrasena.setUserId(userId);
        contrasena.setPassword(encodedPassword);
        contrasena.setFechaCambio(LocalDateTime.now());
        contrasenaRepository.save(contrasena);
    }

    // 2. Verificar si la contraseña ha sido utilizada antes
    @Override
    public boolean checkPasswordHistory(Integer userId, String newPassword) {
        List<Contrasena> passwordHistory = contrasenaRepository.findByUserId(userId);
        for (Contrasena historial : passwordHistory) {
            if (authService.matchesPassword(newPassword, historial.getPassword())) {
                return true; // La contraseña ya ha sido utilizada
            }
        }
        return false; // La contraseña no ha sido utilizada antes
    }
}
