package com.platform.doctic_project.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Repository.VistoPorRepository;
import com.platform.doctic_project.Service.RecommendationService;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private VistoPorRepository vistoPorRepository;

    @Override
    public List<Documento> generateDocumentRecommendations(Integer userId) {
        // Lógica para generar recomendaciones basadas en el historial de visualizaciones del usuario
        List<Documento> viewedDocuments = vistoPorRepository.findById(userId).stream()
                .map(vistoPor -> vistoPor.getDocumento())
                .toList();

        // Aquí podrías implementar una lógica más avanzada que incluya sugerencias basadas en otros usuarios
        // o categorías similares.
        return viewedDocuments; // Implementa la lógica avanzada de recomendaciones
    }
}
