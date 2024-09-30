package com.platform.doctic_project.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "nombre_usuario", nullable = false, unique = true)
    private String nombreUsuario;

    @Column(name = "correo_electronico", nullable = false, unique = true)
    private String correoElectronico;

    @Column(name = "ciudad")
    private String ciudad;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "pregunta_secreta", nullable = false)
    private String preguntaSecreta;

    @Column(name = "respuesta_secreta", nullable = false)
    private String respuestaSecreta;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<HistorialContrasena> historialesContrasena;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<AutorDocumento> documentosAutor;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Descarga> descargas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<VistoPor> vistos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Valoracion> valoraciones;

    // Getters y Setters
}
