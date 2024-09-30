package com.platform.doctic_project.Service;

import com.platform.doctic_project.Model.Usuario;

public interface UserService {
    Usuario createUser(Usuario usuario);
    Usuario authenticateUser(String username, String password);
}
