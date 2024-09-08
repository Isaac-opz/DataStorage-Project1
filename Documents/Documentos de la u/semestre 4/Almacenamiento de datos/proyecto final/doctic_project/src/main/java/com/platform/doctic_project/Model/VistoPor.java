package com.platform.doctic_project.Model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vistopor")
public class VistoPor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vistopor")
    private Integer idVistoPor;

    @Column(name = "fecha_hora", nullable = true, columnDefinition = "DATETIME(3)")
    private LocalDateTime fechaHora;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_documento", nullable = false)
    private Documento documento;

    // Getters y Setters
    // ...
}
