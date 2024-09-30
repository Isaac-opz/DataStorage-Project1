package com.platform.doctic_project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.platform.doctic_project.Model.AutorDocumento;

@Repository
public interface AutorDocumentoRepository extends JpaRepository<AutorDocumento, Integer> {
}
