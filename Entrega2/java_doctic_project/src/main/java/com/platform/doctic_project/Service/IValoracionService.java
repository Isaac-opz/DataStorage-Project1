package com.platform.doctic_project.Service;

import java.util.List;

import com.platform.doctic_project.Model.Valoracion;

public interface IValoracionService {
    Valoracion rateDocument(Integer documentId, Valoracion valoracion);
    double calculateAverageRating(Long documentId);
     List<Valoracion> findByDocumentoId(Integer documentId);
    void rateDocument(Long documentId, Long userId, int rating);
    double calculateAverageRating(Long documentId);
}
