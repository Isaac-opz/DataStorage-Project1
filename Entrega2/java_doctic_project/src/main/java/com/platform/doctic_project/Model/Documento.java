package com.platform.doctic_project.Model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    // Relación con el usuario (quien sube el documento)
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    // Campo que indica si el documento es público o privado
    @Column(name = "visibilidad", nullable = false, length = 50)
    private String visibilidad;

    // Relación con descarga, si es que tiene
    @OneToMany(mappedBy = "documento")
    private List<Descarga> descargas;

    // Relación con comentarios, si es que tiene
    @OneToMany(mappedBy = "documento")
    private List<Comentario> comentarios;

    // Campo adicional para manejar posibles versiones de un documento
    @Column(name = "version", nullable = false)
    private int version;

    // Campo de auditoría para mantener un registro de cuándo fue actualizado por última vez
    @Column(name = "fecha_ultima_actualizacion", nullable = false)
    private LocalDateTime fechaUltimaActualizacion;

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
