package com.platform.doctic_project.Model;

import java.util.List;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    private LocalDateConverter fechaPublicacion;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "visibilidad", nullable = false)
    private String visibilidad;

    @Column(name = "valoracion")
    private Double valoracion;

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

    public LocalDateConverter getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDateConverter fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(String visibilidad) {
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
}
