package com.platform.doctic_project.Service;

import java.util.List; // Asegúrate de que esta importación sea correcta
import com.platform.doctic_project.Model.Usuario;

public interface UserService {
    Usuario createUser(Usuario usuario, String contrasena);
    Usuario authenticateUser(String username, String password);
    boolean existsByUsername(String username);
    List<Usuario> getAllUsers(); // Método para obtener todos los usuarios
}
