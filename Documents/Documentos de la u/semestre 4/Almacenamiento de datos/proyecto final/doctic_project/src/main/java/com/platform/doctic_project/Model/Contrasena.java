package com.platform.doctic_project.Model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_contrasena")
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

    @Column(name = "fecha_cambio", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fechaCambio;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", columnDefinition = "ENUM('activa', 'inactiva')")
    private EstadoContrasena estado;

    public enum EstadoContrasena {
        activa, inactiva
    }

    // Getters y Setters
    // ...
}
