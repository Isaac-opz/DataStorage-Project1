package com.platform.doctic_project.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comentario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comentario")
    private Integer idComentario;

    @ManyToOne
    @JoinColumn(name = "id_documento", nullable = false)
    private Documento documento;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "comentario", nullable = false)
    private String comentario;

    @Column(name = "fecha_comentario", nullable = false)
    private LocalDateTime fechaComentario;

    @ManyToOne
    @JoinColumn(name = "id_metacomentario")
    private Comentario metaComentario;

    public void setIdMetacomentario(Comentario comentario2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setIdMetacomentario'");
    }
}
