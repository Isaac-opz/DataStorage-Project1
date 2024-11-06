package com.backend.doctic_mongo.Model;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Documentos")
public class DocumentoModel {
    private ObjectId id;

    @Field("nombre_documento")
    private String nombreDocumento;

    private String descripcion;

    @Field("fecha_publicacion")
    private Date fechaPublicacion;

    private String url;
    private String visibilidad;
    private int valoracion;

    @Field("num_descargas")
    private int numDescargas;

    @Field("num_vistas")
    private int numVistas;

    @Field("num_comentarios")
    private int numComentarios;

    @Field("id_categoria")
    private Categoria idCategoria;

    private List<AutorModel> autores;
    private List<ValoracionModel> valoraciones;
    private List<DescargaModel> descargas;
    private List<VistaModel> vistas;

    // Getters y Setters para todos los campos

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
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

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
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

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public int getNumDescargas() {
        return numDescargas;
    }

    public void setNumDescargas(int numDescargas) {
        this.numDescargas = numDescargas;
    }

    public int getNumVistas() {
        return numVistas;
    }

    public void setNumVistas(int numVistas) {
        this.numVistas = numVistas;
    }

    public int getNumComentarios() {
        return numComentarios;
    }

    public void setNumComentarios(int numComentarios) {
        this.numComentarios = numComentarios;
    }

    public Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    public List<AutorModel> getAutores() {
        return autores;
    }

    public void setAutores(List<AutorModel> autores) {
        this.autores = autores;
    }

    public List<ValoracionModel> getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(List<ValoracionModel> valoraciones) {
        this.valoraciones = valoraciones;
    }

    public List<DescargaModel> getDescargas() {
        return descargas;
    }

    public void setDescargas(List<DescargaModel> descargas) {
        this.descargas = descargas;
    }

    public List<VistaModel> getVistas() {
        return vistas;
    }

    public void setVistas(List<VistaModel> vistas) {
        this.vistas = vistas;
    }

    // Clase interna para Categoria
    public static class Categoria {
        @Field("id_categoria")
        private int idCategoria;
        private String categoria;
        @Field("id_metacategoria")
        private int idMetacategoria;

        // Getters y Setters
        public int getIdCategoria() {
            return idCategoria;
        }

        public void setIdCategoria(int idCategoria) {
            this.idCategoria = idCategoria;
        }

        public String getCategoria() {
            return categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

        public int getIdMetacategoria() {
            return idMetacategoria;
        }

        public void setIdMetacategoria(int idMetacategoria) {
            this.idMetacategoria = idMetacategoria;
        }
    }

    // Clase interna para AutorModel
    public static class AutorModel {
        @Field("id_usuario")
        private ObjectId idUsuario;
        private String publico;

        // Getters y Setters
        public ObjectId getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(ObjectId idUsuario) {
            this.idUsuario = idUsuario;
        }

        public String getPublico() {
            return publico;
        }

        public void setPublico(String publico) {
            this.publico = publico;
        }
    }

    // Clase interna para ValoracionModel
    public static class ValoracionModel {
        private int estrellas;
        @Field("fecha_valoracion")
        private Date fechaValoracion;
        @Field("id_usuario")
        private ObjectId idUsuario;

        // Getters y Setters
        public int getEstrellas() {
            return estrellas;
        }

        public void setEstrellas(int estrellas) {
            this.estrellas = estrellas;
        }

        public Date getFechaValoracion() {
            return fechaValoracion;
        }

        public void setFechaValoracion(Date fechaValoracion) {
            this.fechaValoracion = fechaValoracion;
        }

        public ObjectId getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(ObjectId idUsuario) {
            this.idUsuario = idUsuario;
        }
    }

    // Clase interna para DescargaModel
    public static class DescargaModel {
        @Field("fecha_hora")
        private Date fechaHora;
        @Field("id_usuario")
        private ObjectId idUsuario;

        // Getters y Setters
        public Date getFechaHora() {
            return fechaHora;
        }

        public void setFechaHora(Date fechaHora) {
            this.fechaHora = fechaHora;
        }

        public ObjectId getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(ObjectId idUsuario) {
            this.idUsuario = idUsuario;
        }
    }

    // Clase interna para VistaModel
    public static class VistaModel {
        @Field("fecha_hora")
        private Date fechaHora;
        @Field("id_usuario")
        private ObjectId idUsuario;

        // Getters y Setters
        public Date getFechaHora() {
            return fechaHora;
        }

        public void setFechaHora(Date fechaHora) {
            this.fechaHora = fechaHora;
        }

        public ObjectId getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(ObjectId idUsuario) {
            this.idUsuario = idUsuario;
        }
    }
}
