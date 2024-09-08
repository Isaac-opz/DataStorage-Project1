package com.platform.doctic_project.Service;

import com.platform.doctic_project.Model.Contrasena;
import com.platform.doctic_project.Repository.ContrasenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PasswordHistoryService {

    @Autowired
    private ContrasenaRepository ContrasenaRepository;

    @Autowired
    private AuthService authService;

    // Guardar contraseña en el historial
    public void savePasswordToHistory(Integer userId, String newPassword) {
        String encodedPassword = authService.encodePassword(newPassword); // Cifrar la contraseña antes de guardarla
        Contrasena Contrasena = new Contrasena();
        Contrasena.setUserId(userId);
        Contrasena.setPassword(encodedPassword);
        Contrasena.setFechaCambio(LocalDateTime.now());
        ContrasenaRepository.save(Contrasena);
    }

    // Verificar si la contraseña ha sido utilizada antes
    public boolean checkPasswordHistory(Integer userId, String newPassword) {
        List<Contrasena> passwordHistory = ContrasenaRepository.findByUserId(userId);
        for (Contrasena historial : passwordHistory) {
            if (authService.matchesPassword(newPassword, historial.getPassword())) {
                return true; // La contraseña ya ha sido utilizada
            }
        }
        return false; // La contraseña no ha sido utilizada antes
    }
}
