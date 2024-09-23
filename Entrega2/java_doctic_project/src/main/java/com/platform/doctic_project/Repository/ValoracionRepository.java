package com.platform.doctic_project.Repository;

import com.platform.doctic_project.Model.Valoracion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ValoracionRepository extends JpaRepository<Valoracion, Integer> {

    List<Valoracion> findByDocumentoId(Integer documentId);



}


