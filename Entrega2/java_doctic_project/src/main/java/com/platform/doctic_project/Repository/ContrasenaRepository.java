package com.platform.doctic_project.Repository;

import com.platform.doctic_project.Model.Contrasena;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ContrasenaRepository extends JpaRepository<Contrasena, Integer> {

    List<Contrasena> findByUserId(Integer userId);

}


