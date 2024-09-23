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

    @Column(name = "valoracion")
    private Double valoracion;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    public void setUsuario(Usuario usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setUsuario'");
    }

    public Object getId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }

    
}
