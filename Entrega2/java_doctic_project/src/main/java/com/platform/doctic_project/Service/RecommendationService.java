package com.platform.doctic_project.Service;

import java.util.List;

import com.platform.doctic_project.Model.Documento;

public interface RecommendationService {
    List<Documento> generateDocumentRecommendations(Integer userId);
}
