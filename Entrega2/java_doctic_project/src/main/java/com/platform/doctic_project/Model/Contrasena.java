package com.platform.doctic_project.Model;

import com.platform.doctic_project.Model.ENUM.EstadoContrasena;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "historial_contrasena")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Contrasena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private Integer idHistorial;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "contrasena", nullable = false, length = 255)
    private String contrasena;

    @Column(name = "fecha_cambio", nullable = false)
    private LocalDateTime fechaCambio;

    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private EstadoContrasena estado;

    // Métodos para establecer y obtener los atributos
    public void setUserId(Integer userId) {
        if (this.usuario == null) {
            this.usuario = new Usuario();
        }
        this.usuario.setIdUsuario(userId);
    }

    public String getPassword() {
        return this.contrasena;
    }

    public void setPassword(String encodedPassword) {
        this.contrasena = encodedPassword;
    }
}
