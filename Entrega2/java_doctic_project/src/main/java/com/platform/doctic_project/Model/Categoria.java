package com.platform.doctic_project.Model;

import jakarta.persistence.*;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categoria")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(name = "categoria", nullable = false, length = 255)
    private String categoria;

    @ManyToOne
    @JoinColumn(name = "id_metacategoria")
    private Categoria metacategoria;

    @OneToMany(mappedBy = "metacategoria")
    private List<Categoria> subcategorias;
}
