package com.salao.cabelereiro.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salao.cabelereiro.model.ServicesModel;

public interface ServicesRepository extends JpaRepository<ServicesModel, UUID>{

    Optional<ServicesModel> findByName(String name);

    List<ServicesModel> findAllByCategory(String category);
    
}