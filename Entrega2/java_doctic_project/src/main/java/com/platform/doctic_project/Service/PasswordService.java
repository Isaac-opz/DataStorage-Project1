package com.platform.doctic_project.Service;

public interface PasswordService {
    boolean changePassword(String nombreUsuario, String contrasenaActual, String respuestaSecreta, String nuevaContrasena);
    void savePasswordToHistory(Integer userId, String password);
    boolean checkPasswordHistory(Integer userId, String password);
}
