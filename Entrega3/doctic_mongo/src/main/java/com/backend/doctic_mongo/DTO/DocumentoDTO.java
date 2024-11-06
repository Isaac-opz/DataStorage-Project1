package com.backend.doctic_mongo.DTO;

import java.util.Date;
import java.util.List;

public class DocumentoDTO {
    private String nombreDocumento;
    private String descripcion;
    private Date fechaPublicacion;
    private String url;
    private String visibilidad;
    private int valoracion;
    private int numDescargas;
    private int numVistas;
    private int numComentarios;
    private CategoriaDTO idCategoria;
    private List<AutorDTO> autores;
    private List<ValoracionDTO> valoraciones;
    private List<DescargaDTO> descargas;
    private List<VistaDTO> vistas;

    // Getters y Setters para cada campo

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

    public CategoriaDTO getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(CategoriaDTO idCategoria) {
        this.idCategoria = idCategoria;
    }

    public List<AutorDTO> getAutores() {
        return autores;
    }

    public void setAutores(List<AutorDTO> autores) {
        this.autores = autores;
    }

    public List<ValoracionDTO> getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(List<ValoracionDTO> valoraciones) {
        this.valoraciones = valoraciones;
    }

    public List<DescargaDTO> getDescargas() {
        return descargas;
    }

    public void setDescargas(List<DescargaDTO> descargas) {
        this.descargas = descargas;
    }

    public List<VistaDTO> getVistas() {
        return vistas;
    }

    public void setVistas(List<VistaDTO> vistas) {
        this.vistas = vistas;
    }

    // Clase interna para CategoriaDTO
    public static class CategoriaDTO {
        private int idCategoria;
        private String categoria;
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

    // Clase interna para AutorDTO
    public static class AutorDTO {
        private String idUsuario;
        private String publico;

        // Getters y Setters
        public String getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(String idUsuario) {
            this.idUsuario = idUsuario;
        }

        public String getPublico() {
            return publico;
        }

        public void setPublico(String publico) {
            this.publico = publico;
        }
    }

    // Clase interna para ValoracionDTO
    public static class ValoracionDTO {
        private int estrellas;
        private Date fechaValoracion;
        private String idUsuario;

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

        public String getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(String idUsuario) {
            this.idUsuario = idUsuario;
        }
    }

    // Clase interna para DescargaDTO
    public static class DescargaDTO {
        private Date fechaHora;
        private String idUsuario;

        // Getters y Setters
        public Date getFechaHora() {
            return fechaHora;
        }

        public void setFechaHora(Date fechaHora) {
            this.fechaHora = fechaHora;
        }

        public String getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(String idUsuario) {
            this.idUsuario = idUsuario;
        }
    }

    // Clase interna para VistaDTO
    public static class VistaDTO {
        private Date fechaHora;
        private String idUsuario;

        // Getters y Setters
        public Date getFechaHora() {
            return fechaHora;
        }

        public void setFechaHora(Date fechaHora) {
            this.fechaHora = fechaHora;
        }

        public String getIdUsuario() {
            return idUsuario;
        }

        public void setIdUsuario(String idUsuario) {
            this.idUsuario = idUsuario;
        }
    }
}
