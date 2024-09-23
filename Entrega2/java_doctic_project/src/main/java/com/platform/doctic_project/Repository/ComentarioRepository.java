package com.platform.doctic_project.Repository;

import com.platform.doctic_project.Model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    // No es necesario agregar nada, ya que JpaRepository proporciona los métodos findById() y save()
}
