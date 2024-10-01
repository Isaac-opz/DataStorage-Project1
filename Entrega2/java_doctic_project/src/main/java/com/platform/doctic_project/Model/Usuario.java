package com.platform.doctic_project.Model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

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
    @JsonManagedReference
    private List<HistorialContrasena> historialesContrasena = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<AutorDocumento> documentosAutor;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Descarga> descargas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<VistoPor> vistos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Valoracion> valoraciones;

    @Transient
    private String contrasenaInicial;  // Campo temporal para manejar la contraseña inicial

    // Constructor que inicializa la lista de historiales de contraseñas
    public Usuario() {
        this.historialesContrasena = new ArrayList<>();
    }

    // Método para obtener la contraseña activa
    public String getContrasenaActiva() {
        for (HistorialContrasena historial : historialesContrasena) {
            if (historial.getEstado() == HistorialContrasena.Estado.activa) {
                return historial.getContrasena();
            }
        }
        return null;
    }

    // Getters y Setters

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getPreguntaSecreta() {
        return preguntaSecreta;
    }

    public void setPreguntaSecreta(String preguntaSecreta) {
        this.preguntaSecreta = preguntaSecreta;
    }

    public String getRespuestaSecreta() {
        return respuestaSecreta;
    }

    public void setRespuestaSecreta(String respuestaSecreta) {
        this.respuestaSecreta = respuestaSecreta;
    }

    public List<HistorialContrasena> getHistorialesContrasena() {
        return historialesContrasena;
    }

    public void setHistorialesContrasena(List<HistorialContrasena> historialesContrasena) {
        this.historialesContrasena = historialesContrasena;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public List<AutorDocumento> getDocumentosAutor() {
        return documentosAutor;
    }

    public void setDocumentosAutor(List<AutorDocumento> documentosAutor) {
        this.documentosAutor = documentosAutor;
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

    public String getContrasenaInicial() {
        return contrasenaInicial;
    }

    public void setContrasenaInicial(String contrasenaInicial) {
        this.contrasenaInicial = contrasenaInicial;
    }
}