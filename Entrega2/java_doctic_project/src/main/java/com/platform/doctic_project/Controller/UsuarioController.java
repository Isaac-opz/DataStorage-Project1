package com.platform.doctic_project.Controller;

import com.platform.doctic_project.Model.Usuario;
import com.platform.doctic_project.Service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/usuarios") // Endpoint base para usuarios
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService; // Usamos la interfaz


    // Registrar un nuevo usuario
    @PostMapping("/registrar")
    public ResponseEntity<String> createUser(@RequestBody Usuario usuario) {
        try {
            usuarioService.createUser(usuario);
            return new ResponseEntity<>("Usuario registrado con éxito.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Autenticar un usuario
    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody Usuario usuario) {
        try {
            Optional<Usuario> authenticatedUser = usuarioService.authenticateUser(usuario.getUsername(), usuario.getPassword());
            if (authenticatedUser.isPresent()) {
                return ResponseEntity.ok("Autenticación exitosa.");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas.");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Eliminar el método de recuperar contraseña, ya que no es necesario

    // Agregar un nuevo método para guardar contraseña en el historial
    @PostMapping("/save-password-to-history")
    public ResponseEntity<String> savePasswordToHistory(@RequestParam int id_usuario, @RequestParam String contrasena) {
        try {
            usuarioService.savePasswordToHistory(id_usuario, contrasena);
            return ResponseEntity.ok("Contraseña guardada en el historial con éxito.");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Agregar un nuevo método para verificar si una contraseña ya fue utilizada
    @PostMapping("/check-password-history")
    public ResponseEntity<String> checkPasswordHistory(@RequestParam int id_usuario, @RequestParam String contrasena) {
        try {
            boolean isPasswordUsed = usuarioService.checkPasswordHistory(id_usuario, contrasena);
            if (isPasswordUsed) {
                return ResponseEntity.ok("La contraseña ya fue utilizada.");
            } else {
                return ResponseEntity.ok("La contraseña no ha sido utilizada.");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

