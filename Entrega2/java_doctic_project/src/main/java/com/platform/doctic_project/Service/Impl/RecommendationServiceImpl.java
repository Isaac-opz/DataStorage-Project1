package com.platform.doctic_project.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Model.VistoPor;
import com.platform.doctic_project.Repository.DocumentoRepository;
import com.platform.doctic_project.Repository.VistoPorRepository;
import com.platform.doctic_project.Service.RecommendationService;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private VistoPorRepository vistoPorRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Override
    public List<Documento> generateDocumentRecommendations(Integer userId) {
        // Lógica: Obtener el historial de visualización del usuario y recomendar documentos similares
        List<VistoPor> historialVistas = vistoPorRepository.findByUsuarioId(userId);
        // Aquí puedes aplicar algún criterio de recomendación basado en categorías, autores, etc.
        // Retorna la lista de documentos recomendados (por ejemplo, basada en categorías más vistas)
        return documentoRepository.findByVisibilidad("publico"); // Lógica básica, puedes mejorarla
    }
}
