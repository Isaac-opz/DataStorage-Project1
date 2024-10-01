package com.platform.doctic_project.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "autor_documento")
public class AutorDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autor_documento")
    private Integer idAutorDocumento;

    @ManyToOne
    @JoinColumn(name = "id_documento", nullable = false)
    @JsonIgnore
    private Documento documento;


    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    @JsonIgnore
    private Usuario usuario;


    @Enumerated(EnumType.STRING)
    @Column(name = "publico", nullable = false, columnDefinition = "ENUM('Si', 'No') DEFAULT 'Si'")
    private Publico publico = Publico.Si;

    // Getters y Setters

    // Enum para 'publico'
    public enum Publico {
        Si,
        No
    }

    // Getters y Setters

    public Integer getIdAutorDocumento() {
        return idAutorDocumento;
    }

    public void setIdAutorDocumento(Integer idAutorDocumento) {
        this.idAutorDocumento = idAutorDocumento;
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

    public Publico getPublico() {
        return publico;
    }

    @SuppressWarnings("rawtypes")
    public void setPublico(Enum publico) {
        this.publico = (Publico) publico;
    }
}
