package com.platform.doctic_project.Controller;

// ESTE ES DE USUARIOS Y CONTRASEÑAS

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.doctic_project.Model.Usuario;
import com.platform.doctic_project.Service.PasswordService;
import com.platform.doctic_project.Service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordService passwordService;

    @PostMapping("/register")
    public ResponseEntity<Usuario> registerUser(@RequestBody Usuario usuario) {
        Usuario newUser = userService.createUser(usuario);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestParam String username, @RequestParam String password) {
        Usuario user = userService.authenticateUser(username, password);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/recover-password")
    public ResponseEntity<Void> recoverPassword(@RequestParam String username, @RequestParam String secretAnswer) {
        boolean success = passwordService.recoverPassword(username, secretAnswer);
        return success ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}
