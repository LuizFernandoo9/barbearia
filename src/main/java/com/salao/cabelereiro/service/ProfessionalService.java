package com.salao.cabelereiro.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salao.cabelereiro.dtos.ProfessionalDTO;
import com.salao.cabelereiro.exception.ProfessionalFoundException;
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

        this.professionalRepository.findByAvailability(professionalDTO.getAvailability()).ifPresent(available -> {
            throw new IllegalArgumentException("Horário já reservado para esse profissional");
        });


        var service = this.servicesRepository.findById(professionalDTO.getSpecialization()).orElseThrow(()-> {
            throw new ServicesNotFoundException();
        });

        var professional = ProfessionalModel.builder()
        .name(professionalDTO.getName())
        .specialization(service)
        .availability(professionalDTO.getAvailability())
        .build();

        this.professionalRepository.save(professional);

        return ProfessionalDTO.builder()
        .name(professionalDTO.getName())
        .specialization(service.getId())
        .availability(professionalDTO.getAvailability())
        .build();    

    }
}
