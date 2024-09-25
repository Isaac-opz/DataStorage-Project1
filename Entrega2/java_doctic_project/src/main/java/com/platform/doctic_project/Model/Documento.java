package com.platform.doctic_project.Model;

import com.platform.doctic_project.Model.ENUM.Visibilidad;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "documento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento")
    private Integer idDocumento;

    @Column(name = "nombre_documento", nullable = false, length = 255)
    private String nombreDocumento;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_publicacion", nullable = false)
    private LocalDateTime fechaPublicacion;

    @Column(name = "url", nullable = false, length = 255)
    private String url;

    @Column(name = "visibilidad", nullable = false)
    @Enumerated(EnumType.STRING)
    private Visibilidad visibilidad;

    @Column(name = "valoracion", columnDefinition = "DECIMAL(2,1) DEFAULT 0")
    private Double valoracion;

    @ManyToOne
    @JoinColumn(name = "Categoria_idCategoria", nullable = false)
    private Categoria categoria;

    // Nuevos campos para número de descargas, vistas y comentarios
    @Column(name = "num_descargas", nullable = true, columnDefinition = "INT DEFAULT 0")
    private Integer numDescargas;

    @Column(name = "num_vistas", nullable = true, columnDefinition = "INT DEFAULT 0")
    private Integer numVistas;

    @Column(name = "num_comentarios", nullable = true, columnDefinition = "INT DEFAULT 0")
    private Integer numComentarios;
}