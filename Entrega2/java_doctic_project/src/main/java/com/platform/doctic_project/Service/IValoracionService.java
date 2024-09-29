package com.platform.doctic_project.Service;

public interface IValoracionService {

    // Método para calcular el promedio de las valoraciones de un documento
    double calculateAverageRating(Integer documentId);

    // Método para calificar un documento
    void rateDocument(Integer documentId, Integer userId, int estrellas);
}
