package com.platform.doctic_project.Model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "valoracion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Valoracion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_valoracion")
    private Integer idValoracion;

    @Column(name = "estrellas")
    private Integer estrellas;

    @Column(name = "fecha_valoracion")
    private LocalDate fechaValoracion;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_documento", nullable = false)
    private Documento documento;
}
