package com.platform.doctic_project.Service;

import com.platform.doctic_project.Model.Usuario;
import java.util.Optional;

public interface IUsuarioService {
    Usuario createUser(Usuario usuario);
    boolean validateUserExistence(String username, String correoElectronico);
    Optional<Usuario> authenticateUser(String username, String password);
    boolean recoverPassword(String username, String answerToSecurityQuestion, String newPassword);
    Usuario updateUser(Usuario usuario);
}
