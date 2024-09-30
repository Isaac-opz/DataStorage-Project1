package com.platform.doctic_project.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.doctic_project.Model.Descarga;
import com.platform.doctic_project.Repository.DescargaRepository;
import com.platform.doctic_project.Service.DownloadService;

@Service
public class DownloadServiceImpl implements DownloadService {

    @Autowired
    private DescargaRepository descargaRepository;

    @Override
    public void recordDocumentDownload(Integer documentId, Integer userId) {
        // Lógica para registrar descarga
    }

    @Override
    public List<Descarga> getUserDownloadHistory(Integer userId) {
        // Lógica para obtener historial de descargas
        return null;
    }
}
