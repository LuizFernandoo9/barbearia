package com.salao.cabelereiro.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salao.cabelereiro.dtos.ServicesDto;
import com.salao.cabelereiro.exception.ServicesFoundException;
import com.salao.cabelereiro.exception.ServicesNotFoundException;
import com.salao.cabelereiro.model.ServicesModel;
import com.salao.cabelereiro.repository.ServicesRepository;

@Service
public class ServicesService {

    @Autowired
    private ServicesRepository servicesRepository;

    public ServicesDto createService(ServicesDto servicesDto){
        servicesRepository.findByName(servicesDto.getName()).ifPresent(service -> {
            throw new ServicesFoundException();
        });

        if (servicesDto.getDuration() < 15) {
           throw new IllegalArgumentException("o tempo não pode ser menor que 15 minutos");
        }

        var newService = ServicesModel.builder()
        .name(servicesDto.getName())
        .description(servicesDto.getDescription())
        .duration(servicesDto.getDuration()).build();

        this.servicesRepository.save(newService);

        return ServicesDto.builder()
        .name(servicesDto.getName())
        .description(servicesDto.getDescription())
        .duration(servicesDto.getDuration()).build();
    }

    public List<ServicesDto> findServices(ServicesDto servicesDto){

        if (servicesDto.getName().isEmpty()) {
            return this.servicesRepository.findAll().stream().map(services -> ServicesDto.builder()
            .name(services.getName())
            .description(services.getDescription())
            .duration(services.getDuration()).build()).collect(Collectors.toList());
            
        }else{
            var services = this.servicesRepository.findByName(servicesDto.getName()).orElseThrow(()-> {
                throw new ServicesNotFoundException();
            });

            return List.of(ServicesDto.builder()
            .name(services.getName())
            .description(services.getDescription())
            .duration(services.getDuration()).build());
        }
    }

    public ServicesDto editServices(ServicesDto servicesDto, UUID id){
        var service = this.servicesRepository.findById(id).orElseThrow(()->{
            throw new ServicesNotFoundException();
        });

        service.setName(servicesDto.getName());
        service.setDescription(servicesDto.getDescription());

        this.servicesRepository.save(service);

        return ServicesDto.builder()
        .name(service.getName())
        .description(service.getDescription())
        .duration(service.getDuration()).build();
    }

    public void deleteService(UUID id){
        var service = this.servicesRepository.findById(id).orElseThrow(()->{
            throw new ServicesNotFoundException();
        });

        this.servicesRepository.delete(service);
    }
}
