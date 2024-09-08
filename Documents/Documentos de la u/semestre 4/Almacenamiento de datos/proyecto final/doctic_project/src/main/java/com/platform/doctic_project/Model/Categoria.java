package com.platform.doctic_project.Model;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "categoria")
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

    // Getters y Setters
    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Categoria getMetacategoria() {
        return metacategoria;
    }

    public void setMetacategoria(Categoria metacategoria) {
        this.metacategoria = metacategoria;
    }

    public List<Categoria> getSubcategorias() {
        return subcategorias;
    }

    public void setSubcategorias(List<Categoria> subcategorias) {
        this.subcategorias = subcategorias;
    }
}
