package com.platform.doctic_project.Service;

import com.platform.doctic_project.Model.Categoria;
import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Model.Descarga;
import com.platform.doctic_project.Model.¿Visualizacion;
import com.platform.doctic_project.Repository.DocumentoRepository;
import com.platform.doctic_project.Repository.DescargaRepository;
import com.platform.doctic_project.Repository.VisualizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecomendacionService {

    @Autowired
    private VisualizacionRepository VisualizacionRepository;

    @Autowired
    private DescargaRepository DescargaRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    // 1. Servicio para generar recomendaciones de documentos
    public List<Documento> generateDocumentRecommendations(Integer userId) {
        // Obtener el historial de visualizaciones y descargas del usuario
        List<HistorialVisualizacion> visualizaciones = VisualizacionRepository.findByUsuarioId(userId);
        List<HistorialDescarga> descargas = DescargaRepository.findByUsuarioId(userId);

        // Combinar los documentos vistos y descargados para identificar categorías de interés
        Set<Categoria> categoriasDeInteres = new HashSet<>();

        for (HistorialVisualizacion visualizacion : visualizaciones) {
            categoriasDeInteres.add(visualizacion.getDocumento().getCategoria());
        }

        for (HistorialDescarga descarga : descargas) {
            categoriasDeInteres.add(descarga.getDocumento().getCategoria());
        }

        // Filtrar documentos por categorías de interés que el usuario aún no ha visto ni descargado
        List<Documento> documentosRecomendados = documentoRepository.findAll().stream()
            .filter(documento -> categoriasDeInteres.contains(documento.getCategoria()))
            .filter(documento -> !haVistoODescargado(userId, documento))
            .collect(Collectors.toList());

        return documentosRecomendados;
    }

    // Método auxiliar para verificar si el usuario ha visto o descargado un documento
    private boolean haVistoODescargado(Integer userId, Documento documento) {
        boolean visto = VisualizacionRepository.existsByUsuarioIdAndDocumentoId(userId, documento.getId());
        boolean descargado = DescargaRepository.existsByUsuarioIdAndDocumentoId(userId, documento.getId());
        return visto || descargado;
    }
}
