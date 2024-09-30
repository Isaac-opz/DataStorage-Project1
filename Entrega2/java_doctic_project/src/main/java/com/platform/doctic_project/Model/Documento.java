package com.platform.doctic_project.Model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "documento")
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento")
    private Integer idDocumento;

    @Column(name = "nombre_documento", nullable = false)
    private String nombreDocumento;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_publicacion", nullable = false)
    private LocalDateTime fechaPublicacion;

    @Column(name = "url", nullable = false)
    private String url;

    @Enumerated(EnumType.STRING)
    @Column(name = "visibilidad", nullable = false, columnDefinition = "ENUM('publico', 'privado')")
    private Visibilidad visibilidad;

    @Column(name = "valoracion", nullable = false)
    private Double valoracion = 0.0;

    @Column(name = "num_descargas", nullable = false)
    private Integer numDescargas = 0;

    @Column(name = "num_vistas", nullable = false)
    private Integer numVistas = 0;

    @Column(name = "num_comentarios", nullable = false)
    private Integer numComentarios = 0;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "documento", cascade = CascadeType.ALL)
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "documento", cascade = CascadeType.ALL)
    private List<AutorDocumento> autores;

    @OneToMany(mappedBy = "documento", cascade = CascadeType.ALL)
    private List<Descarga> descargas;

    @OneToMany(mappedBy = "documento", cascade = CascadeType.ALL)
    private List<VistoPor> vistos;

    @OneToMany(mappedBy = "documento", cascade = CascadeType.ALL)
    private List<Valoracion> valoraciones;

    // Getters y Setters

    // Enum para 'visibilidad'
    public enum Visibilidad {
        publico,
        privado
    }

    // Constructor
    public Documento() {
        this.fechaPublicacion = LocalDateTime.now();
    }

    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Visibilidad getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(Visibilidad visibilidad) {
    this.visibilidad = visibilidad;
    }

    public Double getValoracion() {
        return valoracion;
    }

    public void setValoracion(Double valoracion) {
        this.valoracion = valoracion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<AutorDocumento> getAutores() {
        return autores;
    }

    public void setAutores(List<AutorDocumento> autores) {
        this.autores = autores;
    }

    public List<Descarga> getDescargas() {
        return descargas;
    }

    public void setDescargas(List<Descarga> descargas) {
        this.descargas = descargas;
    }

    public List<VistoPor> getVistos() {
        return vistos;
    }

    public void setVistos(List<VistoPor> vistos) {
        this.vistos = vistos;
    }

    public List<Valoracion> getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(List<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }
    public Integer getNumComentarios() {
        return numComentarios;
    }
    
    public void setNumComentarios(Integer numComentarios) {
        this.numComentarios = numComentarios;
    }

    public Integer getNumDescargas() {
        return numDescargas;
    }
    
    public void setNumDescargas(Integer numDescargas) {
        this.numDescargas = numDescargas;
    }

    public Integer getNumVistas() {
        return numVistas;
    }
    
    public void setNumVistas(Integer numVistas) {
        this.numVistas = numVistas;
    }
}
