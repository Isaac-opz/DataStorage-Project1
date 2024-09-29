package com.platform.doctic_project.Controller;

import com.platform.doctic_project.Model.Usuario;
import com.platform.doctic_project.Service.IUsuarioService;
import com.platform.doctic_project.Service.IPasswordHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuarios") // Endpoint base para usuarios
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService; // Usamos la interfaz

    @Autowired
    private IPasswordHistoryService passwordHistoryService; // Usamos la interfaz

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
            boolean isAuthenticated = usuarioService.authenticateUser(usuario.getUsername(), usuario.getContrasena());
            if (isAuthenticated) {
                return ResponseEntity.ok("Autenticación exitosa.");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas.");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Recuperar contraseña
    @PostMapping("/recuperar-contrasena")
    public ResponseEntity<String> recoverPassword(@RequestParam String username, @RequestParam String respuestaSecreta) {
        try {
            boolean isRecovered = usuarioService.recoverPassword(username, respuestaSecreta, respuestaSecreta);
            if (isRecovered) {
                passwordHistoryService.savePasswordToHistory(username); // Guardar en el historial de contraseñas
                return ResponseEntity.ok("Contraseña recuperada con éxito.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo recuperar la contraseña.");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
