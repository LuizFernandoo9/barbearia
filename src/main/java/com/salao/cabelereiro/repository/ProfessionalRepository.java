package com.salao.cabelereiro.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salao.cabelereiro.model.ProfessionalModel;

public interface ProfessionalRepository extends JpaRepository<ProfessionalModel, UUID>{

    Optional<ProfessionalModel> findByName(String name);


} 
