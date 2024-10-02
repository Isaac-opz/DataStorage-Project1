// ESTE ES DE   USUARIOS Y GESTION DE CONTRASENAS

package com.platform.doctic_project.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<?> registerUser(@RequestBody Map<String, Object> userRequest) {
        try {
            // Obtener los datos del usuario y la contraseña del request
            Usuario usuario = new Usuario();
            usuario.setNombreUsuario((String) userRequest.get("nombreUsuario"));
            usuario.setCorreoElectronico((String) userRequest.get("correoElectronico"));
            usuario.setCiudad((String) userRequest.get("ciudad"));
            usuario.setDepartamento((String) userRequest.get("departamento"));
            usuario.setPreguntaSecreta((String) userRequest.get("preguntaSecreta"));
            usuario.setRespuestaSecreta((String) userRequest.get("respuestaSecreta"));

            // Obtener la contraseña
            String contrasena = (String) userRequest.get("contrasena");

            // Registrar al usuario y guardar la contraseña en el historial
            Usuario newUser = userService.createUser(usuario, contrasena);
            return ResponseEntity.ok(newUser);
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
    public ResponseEntity<?> changePassword(@RequestBody Map<String, String> passwordRequest) {
        try {
            String nombreUsuario = passwordRequest.get("nombreUsuario");
            String contrasenaActual = passwordRequest.get("contrasenaActual");
            String respuestaSecreta = passwordRequest.get("respuestaSecreta");
            String nuevaContrasena = passwordRequest.get("nuevaContrasena");

            boolean success = passwordService.changePassword(nombreUsuario, contrasenaActual, respuestaSecreta, nuevaContrasena);
            if (success) {
                return ResponseEntity.ok("Contraseña cambiada exitosamente.");
            } else {
                return ResponseEntity.badRequest().body("No se pudo cambiar la contraseña.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al cambiar la contraseña.");
        }
    }



}
