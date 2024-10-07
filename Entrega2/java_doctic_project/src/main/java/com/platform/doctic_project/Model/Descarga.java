package com.platform.doctic_project.Model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "descarga")
public class Descarga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_descarga")
    private Integer idDescarga;

    @ManyToOne
    @JoinColumn(name = "id_documento", nullable = false)
    @JsonIgnore
    private Documento documento;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnore
    private Usuario usuario;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    // Constructor por defecto
    public Descarga() {
        this.fechaHora = LocalDateTime.now();
    }

    // Constructor con parámetros (opcional)
    public Descarga(Documento documento, Usuario usuario) {
        this.documento = documento;
        this.usuario = usuario;
        this.fechaHora = LocalDateTime.now(); // Inicializa con la fecha/hora actual
    }

    // Getters y Setters
    public Integer getIdDescarga() {
        return idDescarga;
    }

    public void setIdDescarga(Integer idDescarga) {
        this.idDescarga = idDescarga;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public String toString() {
        return "Descarga{" +
                "idDescarga=" + idDescarga +
                ", documento=" + documento +
                ", usuario=" + usuario +
                ", fechaHora=" + fechaHora +
                '}';
    }
}
