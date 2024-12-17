package com.salao.cabelereiro.service;


import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salao.cabelereiro.dtos.ProfessionalDTO;
import com.salao.cabelereiro.exception.ProfessionalFoundException;
import com.salao.cabelereiro.exception.ProfessionalNotFoundException;
import com.salao.cabelereiro.exception.ServicesNotFoundException;
import com.salao.cabelereiro.model.ProfessionalModel;
import com.salao.cabelereiro.repository.ProfessionalRepository;
import com.salao.cabelereiro.repository.ServicesRepository;

@Service
public class ProfessionalService {

    @Autowired
    private ProfessionalRepository professionalRepository;

    @Autowired
    private ServicesRepository servicesRepository;

    public ProfessionalDTO createProfessional(ProfessionalDTO professionalDTO){
        this.professionalRepository.findByName(professionalDTO.getName()).ifPresent(professional -> {
            throw new ProfessionalFoundException();
        });

        var service = this.servicesRepository.findByName(professionalDTO.getSpecialization()).orElseThrow(()-> {
            throw new ServicesNotFoundException();
        });

        var professional = ProfessionalModel.builder()
        .name(professionalDTO.getName())
        .specialization(service)
        .shift(professionalDTO.getShift())
        .build();

        this.professionalRepository.save(professional);

        return ProfessionalDTO.builder()
        .name(professionalDTO.getName())
        .specialization(service.getName())
        .shift(professionalDTO.getShift())
        .build();    
    }

    public List<ProfessionalDTO> findUserByName(ProfessionalDTO professionalDTO){
        if(professionalDTO.getName().isEmpty()){
            return this.professionalRepository.findAll().stream().map(professional -> ProfessionalDTO.builder()
            .name(professional.getName())
            .specialization(professional.getSpecialization().getName())
            .shift(professional.getShift()).build()).collect(Collectors.toList());
        }else{
            var professional = this.professionalRepository.findByName(professionalDTO.getName()).orElseThrow(()->{
                throw new ProfessionalNotFoundException();
            });
            
            return List.of(ProfessionalDTO.builder()
            .name(professional.getName())
            .specialization(professional.getSpecialization().getName())
            .shift(professional.getShift()).build());
        }   
    }

    public ProfessionalDTO editProfessional(ProfessionalDTO professionalDTO, UUID id){
        var professional = this.professionalRepository.findById(id).orElseThrow(()->{
            throw new ProfessionalNotFoundException();
        });

        professional.setShift(professionalDTO.getShift());
        professionalRepository.save(professional);

        return ProfessionalDTO.builder()
        .name(professional.getName())
        .specialization(professional.getSpecialization().getName())
        .shift(professional.getShift())
        .build();
    }
}
