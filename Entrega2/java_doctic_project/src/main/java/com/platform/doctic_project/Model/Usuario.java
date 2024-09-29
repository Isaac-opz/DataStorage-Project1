package com.platform.doctic_project.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "nombre_usuario", nullable = false, unique = true, length = 50)
    private String nombreUsuario;

    @Column(name = "correo_electronico", nullable = false, unique = true, length = 150)
    private String correoElectronico;

    @Column(name = "ciudad", length = 100)
    private String ciudad;

    @Column(name = "departamento", length = 100)
    private String departamento;

    @Column(name = "pregunta_secreta", nullable = false, length = 255)
    private String preguntaSecreta;

    @Column(name = "respuesta_secreta", nullable = false, length = 45)
    private String respuestaSecreta;

    // Este campo es la contraseña del usuario
    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    // Métodos para obtener y establecer la contraseña

    public String getContrasena() {
        return this.contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    // Método para obtener el ID del usuario
    public Integer getId() {
        return this.idUsuario;
    }
}
