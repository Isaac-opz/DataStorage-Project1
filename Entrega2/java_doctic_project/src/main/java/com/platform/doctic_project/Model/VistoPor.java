package com.platform.doctic_project.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vistopor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VistoPor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vistopor")
    private Integer idVistoPor;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_documento", nullable = false)
    private Documento documento;
}
