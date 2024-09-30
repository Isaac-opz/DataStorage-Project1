package com.platform.doctic_project.Service;

public interface PasswordService {
    boolean recoverPassword(String username, String respuestaSecreta);
    void savePasswordToHistory(Integer userId, String password);
    boolean checkPasswordHistory(Integer userId, String password);
}
