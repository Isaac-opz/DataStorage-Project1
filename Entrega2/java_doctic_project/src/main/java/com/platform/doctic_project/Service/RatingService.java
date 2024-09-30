package com.platform.doctic_project.Service;

import com.platform.doctic_project.Model.Valoracion;

public interface RatingService {
    Valoracion rateDocument(Integer userId, Integer documentId, Integer estrellas);
    Double calculateAverageRating(Integer documentId);
}
