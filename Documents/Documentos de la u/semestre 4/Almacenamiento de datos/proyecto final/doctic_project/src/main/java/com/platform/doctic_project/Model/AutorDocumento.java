package com.platform.doctic_project.Model;
import jakarta.persistence.*;

@Entity
@Table(name = "autor_documento")
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

    @Enumerated(EnumType.STRING)
    @Column(name = "publico", columnDefinition = "ENUM('Si', 'No')")
    private Publico publico;

    public enum Publico {
        Si, No
    }

    // Getters y Setters
    // ...
}
