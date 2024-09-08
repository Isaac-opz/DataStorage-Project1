package com.platform.doctic_project.Model;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "valoracion")
public class Valoracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_valoracion")
    private Integer idValoracion;

    @Column(name = "estrellas", nullable = true)
    private Integer estrellas;

    @Column(name = "fechavaloracion", nullable = true)
    private LocalDate fechaValoracion;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_documento", nullable = false)
    private Documento documento;

    // Getters y Setters
    // ...
}
