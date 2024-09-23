package com.platform.doctic_project.Model;


import com.platform.doctic_project.Model.ENUM.Publico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "autor_documento")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutorDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autor_documento")
    private Integer idAutorDocumento;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_documento", nullable = false)
    private Documento documento;

    @Column(name = "publico")
    @Enumerated(EnumType.STRING)
    private Publico publico;
}
