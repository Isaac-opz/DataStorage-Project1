package com.platform.doctic_project.Service;

import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Model.Usuario;
import com.platform.doctic_project.Model.VistoPor;
import com.platform.doctic_project.Model.Descarga;
import com.platform.doctic_project.Repository.VistoPorRepository;
import com.platform.doctic_project.Repository.DescargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecomendacionServiceImp implements IRecomendacionService {

    @Autowired
    private VistoPorRepository vistoPorRepository;

    @Autowired
    private DescargaRepository descargaRepository;

    @Override
    public List<Documento> generateDocumentRecommendations(Integer userId) {
        // Obtener historial de documentos vistos y descargados
        List<VistoPor> vistos = getUserViewHistory(userId);
        List<Descarga> descargas = getUserDownloads(userId);

        // Lógica para generar recomendaciones basadas en historial de vistos y descargas
        return vistos.stream()
            .map(VistoPor::getDocumento)
            .distinct()
            .collect(Collectors.toList());
    }

    // Método para obtener el historial de vistos de un usuario
    private List<VistoPor> getUserViewHistory(Integer userId) {
        return vistoPorRepository.findByUserId(userId);
    }

    // Método para obtener el historial de descargas de un usuario
    private List<Descarga> getUserDownloads(Integer userId) {
        return descargaRepository.findByUserId(userId);
    }
}
