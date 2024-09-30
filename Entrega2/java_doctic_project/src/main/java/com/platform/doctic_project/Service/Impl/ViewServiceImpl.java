package com.platform.doctic_project.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.doctic_project.Model.VistoPor;
import com.platform.doctic_project.Repository.VistoPorRepository;
import com.platform.doctic_project.Service.ViewService;

@Service
public class ViewServiceImpl implements ViewService {

    @Autowired
    private VistoPorRepository vistoPorRepository;

    @Override
    public void recordDocumentView(Integer documentId, Integer userId) {
        // Lógica para registrar visualización
    }

    @Override
    public List<VistoPor> getUserViewHistory(Integer userId) {
        // Lógica para obtener historial de visualizaciones
        return null;
    }
}
