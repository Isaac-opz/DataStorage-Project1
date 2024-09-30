package com.platform.doctic_project.Controller;

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
    public ResponseEntity<?> registerUser(@RequestBody Usuario usuario) {
        try {
            Usuario newUser = userService.createUser(usuario);
            return ResponseEntity.ok(newUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String nombreUsuario, @RequestParam String contrasena) {
        try {
            Usuario user = userService.authenticateUser(nombreUsuario, contrasena);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PostMapping("/recover-password")
    public ResponseEntity<?> recoverPassword(@RequestParam String nombreUsuario, @RequestParam String respuestaSecreta) {
        try {
            boolean success = passwordService.recoverPassword(nombreUsuario, respuestaSecreta);
            if (success) {
                return ResponseEntity.ok("Contraseña recuperada con éxito. Revisa tu correo electrónico.");
            } else {
                return ResponseEntity.badRequest().body("Respuesta secreta incorrecta.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al recuperar la contraseña.");
        }
    }
}
