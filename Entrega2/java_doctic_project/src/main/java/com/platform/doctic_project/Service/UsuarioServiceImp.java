package com.platform.doctic_project.Service;

import com.platform.doctic_project.Model.Usuario;
import com.platform.doctic_project.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImp implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private PasswordHistoryServiceImp passwordHistoryService;

    @Override
    public Usuario createUser(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public boolean validateUserExistence(String username, String correoElectronico) {
        return usuarioRepository.existsByUsernameOrCorreoElectronico(username, correoElectronico);
    }

    @Override
    public Optional<Usuario> authenticateUser(String username, String password) {
        Optional<Usuario> usuario = usuarioRepository.findByUsername(username);
        if (usuario.isPresent() && passwordEncoder.matches(password, usuario.get().getPassword())) {
            return usuario;
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void savePasswordToHistory(int id_usuario, String contrasena) {
        passwordHistoryService.savePasswordToHistory(id_usuario, contrasena);
    }

    @Override
    public boolean checkPasswordHistory(int id_usuario, String contrasena) {
        return passwordHistoryService.checkPasswordHistory(id_usuario, contrasena);
    }

    @Override
    public Usuario updateUser(Usuario usuario) {
        if (!usuarioRepository.existsById(usuario.getIdUsuario())) {
            throw new IllegalArgumentException("El usuario no existe.");
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public boolean recoverPassword(String username, String answerToSecurityQuestion, String newPassword) {
        Optional<Usuario> usuario = usuarioRepository.findByUsername(username);
        if (usuario.isPresent()) {
            Usuario foundUser = usuario.get();
            if (foundUser.getRespuestaSecreta().equals(answerToSecurityQuestion)) {
                foundUser.setPassword(passwordEncoder.encode(newPassword));
                usuarioRepository.save(foundUser);
                return true;
            }
        }
        return false;
    }
}
