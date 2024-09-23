package com.platform.doctic_project.Service;

import com.platform.doctic_project.Model.Documento;

import java.util.List;

public interface IRecomendacionService {
    List<Documento> generateDocumentRecommendations(Integer userId);
}
