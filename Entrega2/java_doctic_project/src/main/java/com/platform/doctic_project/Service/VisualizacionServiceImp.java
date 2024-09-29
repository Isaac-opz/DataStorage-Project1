package com.platform.doctic_project.Service;

import com.platform.doctic_project.Model.Documento;
import com.platform.doctic_project.Model.Usuario;
import com.platform.doctic_project.Model.VistoPor;
import com.platform.doctic_project.Repository.VistoPorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VisualizacionServiceImp implements IVisualizacionService {

    @Autowired
    private VistoPorRepository vistoPorRepository;

    @Override
    public List<VistoPor> getUserViewHistory(Integer userId) {
        return vistoPorRepository.findByUsuarioId(userId);
    }

    public void saveView(Integer documentId, Integer userId) {
        VistoPor visualizacion = new VistoPor();
        Documento documento = new Documento();
        Usuario usuario = new Usuario();

        // Establecer relaciones
        documento.setId(documentId);  // Asignamos el ID del documento
        usuario.setId(userId);        // Asignamos el ID del usuario
        visualizacion.setDocumento(documento);
        visualizacion.setUsuario(usuario);
        visualizacion.setFechaHora(LocalDateTime.now());

        // Guardar la visualización en la base de datos
        vistoPorRepository.save(visualizacion);
    }
}
