package com.platform.doctic_project.Controller;

import com.platform.doctic_project.Model.Usuario;
import com.platform.doctic_project.Service.UsuarioService;
import com.platform.doctic_project.Service.ContrasenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ContrasenaService contrasenaService;

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody UsuarioModel usuario) {
        usuarioService.createUser(usuario);
        return ResponseEntity.ok("Usuario registrado con éxito.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody UsuarioModel usuario) {
        boolean isAuthenticated = usuarioService.authenticateUser(usuario.getUsername(), usuario.getContrasena());
        if (isAuthenticated) {
            return ResponseEntity.ok("Autenticación exitosa.");
        } else {
            return ResponseEntity.status(401).body("Credenciales inválidas.");
        }
    }

    @PostMapping("/recover-password")
    public ResponseEntity<String> recoverPassword(@RequestParam String username, @RequestParam String respuestaSecreta) {
        boolean isRecovered = usuarioService.recoverPassword(username, respuestaSecreta);
        if (isRecovered) {
            contrasenaService.savePasswordToHistory(username);
            return ResponseEntity.ok("Contraseña recuperada con éxito.");
        } else {
            return ResponseEntity.status(400).body("No se pudo recuperar la contraseña.");
        }
    }
}
