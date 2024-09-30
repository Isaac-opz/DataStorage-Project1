package com.platform.doctic_project.Service;

public interface IPasswordHistoryService {
    void savePasswordToHistory(Integer userId, String newPassword);
    boolean checkPasswordHistory(Integer userId, String newPassword);
}