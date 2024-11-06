package com.backend.doctic_mongo.Service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.doctic_mongo.DTO.DocumentoDTO;
import com.backend.doctic_mongo.Exceptions.CustomException;
import com.backend.doctic_mongo.Model.DocumentoModel;
import com.backend.doctic_mongo.Repository.IDocumentoRepository;

@Service
public class DocumentoServiceImpl implements IDocumentoService {

    @Autowired
    private IDocumentoRepository documentoRepository;

    @Override
    public void publicarDocumento(DocumentoDTO documentoDTO) {
        try {
            DocumentoModel documento = new DocumentoModel();
            documento.setNombreDocumento(documentoDTO.getNombreDocumento());
            documento.setDescripcion(documentoDTO.getDescripcion());
            documento.setFechaPublicacion(documentoDTO.getFechaPublicacion());
            documento.setUrl(documentoDTO.getUrl());
            documento.setVisibilidad(documentoDTO.getVisibilidad());
            documento.setValoracion(documentoDTO.getValoracion());
            documento.setNumDescargas(documentoDTO.getNumDescargas());
            documento.setNumVistas(documentoDTO.getNumVistas());
            documento.setNumComentarios(documentoDTO.getNumComentarios());

            // Mapeo de Categoria
            DocumentoModel.Categoria categoria = new DocumentoModel.Categoria();
            categoria.setIdCategoria(documentoDTO.getIdCategoria().getIdCategoria());
            categoria.setCategoria(documentoDTO.getIdCategoria().getCategoria());
            categoria.setIdMetacategoria(documentoDTO.getIdCategoria().getIdMetacategoria());
            documento.setIdCategoria(categoria);

            // Mapeo de Autores
            documento.setAutores(documentoDTO.getAutores().stream().map(autorDTO -> {
                DocumentoModel.AutorModel autor = new DocumentoModel.AutorModel();
                autor.setIdUsuario(new ObjectId(autorDTO.getIdUsuario()));
                autor.setPublico(autorDTO.getPublico());
                return autor;
            }).collect(Collectors.toList()));

            // Mapeo de Valoraciones
            documento.setValoraciones(documentoDTO.getValoraciones().stream().map(valoracionDTO -> {
                DocumentoModel.ValoracionModel valoracion = new DocumentoModel.ValoracionModel();
                valoracion.setEstrellas(valoracionDTO.getEstrellas());
                valoracion.setFechaValoracion(valoracionDTO.getFechaValoracion());
                valoracion.setIdUsuario(new ObjectId(valoracionDTO.getIdUsuario()));
                return valoracion;
            }).collect(Collectors.toList()));

            // Mapeo de Descargas
            documento.setDescargas(documentoDTO.getDescargas().stream().map(descargaDTO -> {
                DocumentoModel.DescargaModel descarga = new DocumentoModel.DescargaModel();
                descarga.setFechaHora(descargaDTO.getFechaHora());
                descarga.setIdUsuario(new ObjectId(descargaDTO.getIdUsuario()));
                return descarga;
            }).collect(Collectors.toList()));

            // Mapeo de Vistas
            documento.setVistas(documentoDTO.getVistas().stream().map(vistaDTO -> {
                DocumentoModel.VistaModel vista = new DocumentoModel.VistaModel();
                vista.setFechaHora(vistaDTO.getFechaHora());
                vista.setIdUsuario(new ObjectId(vistaDTO.getIdUsuario()));
                return vista;
            }).collect(Collectors.toList()));

            documentoRepository.save(documento);
        } catch (Exception e) {
            throw new CustomException("Error al publicar el documento: " + e.getMessage());
        }
    }

    @Override
    public DocumentoModel descargarDocumento(String documentoId, String usuarioId) {
        try {
            ObjectId id = new ObjectId(documentoId); // Conversión de String a ObjectId
            Optional<DocumentoModel> documentoOpt = documentoRepository.findById(id);

            if (documentoOpt.isPresent()) {
                return documentoOpt.get();
            } else {
                throw new CustomException("Documento no encontrado con ID: " + documentoId);
            }
        } catch (Exception e) {
            throw new CustomException("Error al descargar el documento: " + e.getMessage());
        }
    }
}
