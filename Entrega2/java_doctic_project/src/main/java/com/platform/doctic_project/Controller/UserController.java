// ESTE ES DE   USUARIOS Y GESTION DE CONTRASENAS

package com.platform.doctic_project.Controller;

import java.util.Map;

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
            if (userService.existsByUsername(usuario.getNombreUsuario())) {
                return ResponseEntity.status(409).body("El nombre de usuario ya está en uso.");
            }
            if (usuario.getContrasena() == null || usuario.getContrasena().isEmpty()) {
                return ResponseEntity.badRequest().body("La contraseña inicial no puede estar vacía.");
            }
            Usuario newUser = userService.createUser(usuario);
            return ResponseEntity.ok(newUser); // Devuelve el nuevo usuario con su información
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al registrar el usuario.");
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
        try {
            String nombreUsuario = loginRequest.get("nombreUsuario");
            String contrasena = loginRequest.get("contrasena");
            
            // Autenticar el usuario
            Usuario usuario = userService.authenticateUser(nombreUsuario, contrasena);
            
            // Retornar mensaje de éxito
            String mensaje = "Hola " + usuario.getNombreUsuario() + ", has accedido a tu cuenta en Doctic.";
            return ResponseEntity.ok(Map.of("message", mensaje));
        } catch (IllegalArgumentException e) {
            // Si hay algún error en la autenticación
            return ResponseEntity.status(401).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            // Para cualquier otro error
            return ResponseEntity.status(500).body(Map.of("error", "Error al intentar iniciar sesión."));
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody Map<String, String> request) {
            String nombreUsuario = request.get("nombreUsuario");
            String contrasenaActual = request.get("contrasenaActual");
            String respuestaSecreta = request.get("respuestaSecreta");
            String nuevaContrasena = request.get("nuevaContrasena");

            boolean success = passwordService.changePassword(nombreUsuario, contrasenaActual, respuestaSecreta, nuevaContrasena);

            if (success) {
                return ResponseEntity.ok("La contraseña ha sido cambiada exitosamente.");
            } else {
                return ResponseEntity.badRequest().body("Error al cambiar la contraseña.");
            }
    }


}
