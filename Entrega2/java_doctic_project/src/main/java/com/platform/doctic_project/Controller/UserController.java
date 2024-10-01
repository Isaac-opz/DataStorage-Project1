package com.platform.doctic_project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.platform.doctic_project.Model.Usuario;
import com.platform.doctic_project.Service.PasswordService;
import com.platform.doctic_project.Service.UserService;

import java.util.List;

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
            if (userService.existsByUsername(usuario.getNombreUsuario())) {
                return ResponseEntity.status(409).body("El nombre de usuario ya está en uso.");
            }
            if (usuario.getContrasena() == null || usuario.getContrasena().isEmpty()) {
                return ResponseEntity.badRequest().body("La contraseña inicial no puede estar vacía.");
            }
            Usuario newUser = userService.createUser(usuario);
            return ResponseEntity.ok(newUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al registrar el usuario.");
        }
    }

    @GetMapping("/all") // Endpoint para obtener todos los usuarios
    public ResponseEntity<List<Usuario>> getAllUsers() {
        List<Usuario> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
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
