package com.platform.doctic_project.Service;

import com.platform.doctic_project.Model.Categoria;
import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Repository.DocumentoRepository;
import com.platform.doctic_project.Repository.DescargaRepository;
import com.platform.doctic_project.Repository.VisualizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecomendacionServiceImp implements IRecomendacionService {

    @Autowired
    private VisualizacionRepository visualizacionRepository;

    @Autowired
    private DescargaRepository descargaRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    // 1. Servicio para generar recomendaciones de documentos
    @Override
    public List<Documento> generateDocumentRecommendations(Long userId) {
        // Obtener el historial de visualizaciones y descargas del usuario
        List<VistoPor> visualizaciones = visualizacionRepository.findByUsuarioId(userId);
        List<Descarga> descargas = descargaRepository.findByUsuarioId(userId);

        // Combinar los documentos vistos y descargados para identificar categorías de interés
        Set<Categoria> categoriasDeInteres = new HashSet<>();

        for (VistoPor visualizacion : visualizaciones) {
            categoriasDeInteres.add(visualizacion.getDocumento().getCategoria());
        }

        for (Descarga descarga : descargas) {
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
        boolean visto = visualizacionRepository.existsByUsuarioIdAndDocumentoId(userId, documento.getId());
        boolean descargado = descargaRepository.existsByUsuarioIdAndDocumentoId(userId, documento.getId());
        return visto || descargado;
    }
}
