package com.platform.doctic_project.Service;

import java.util.List;

import com.platform.doctic_project.Model.Valoracion;

public interface RatingService {
    Valoracion rateDocument(Integer userId, Integer documentId, Integer estrellas);
    List<Valoracion> listDocumentRatings(Integer documentId);
}
