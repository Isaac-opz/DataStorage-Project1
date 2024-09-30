package com.platform.doctic_project.Service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.doctic_project.Model.Valoracion;
import com.platform.doctic_project.Repository.ValoracionRepository;
import com.platform.doctic_project.Service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private ValoracionRepository valoracionRepository;

    @Override
    public Valoracion rateDocument(Integer userId, Integer documentId, Integer estrellas) {
        // Lógica para añadir una valoración
        return null; // Implementa la lógica aquí
    }

    @Override
    public Double calculateAverageRating(Integer documentId) {
        // Lógica para calcular la valoración promedio
        return null; // Implementa la lógica aquí
    }
}
