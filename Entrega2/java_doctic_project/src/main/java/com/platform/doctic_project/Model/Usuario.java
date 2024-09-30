package com.platform.doctic_project.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "nombre_usuario", nullable = false, length = 50)
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

    @Column(name = "password", nullable = false)  // Asegúrate de incluir este campo
    private String password;  // Campo para la contraseña

    // No necesitas los métodos getPassword y setPassword porque Lombok generará getters y setters automáticamente
}
