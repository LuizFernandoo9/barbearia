package com.salao.cabelereiro.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salao.cabelereiro.dtos.ServicesDto;
import com.salao.cabelereiro.service.ServicesService;

@RestController
@RequestMapping("/servicos")
public class ServicesController {

    @Autowired
    private ServicesService servicesService;

    @PostMapping("/")
    public ResponseEntity<Object> createServices(@RequestBody ServicesDto servicesDto){
        try {
            var services = this.servicesService.createService(servicesDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(services);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> getServices(@RequestBody ServicesDto servicesDto){
        try {
            var services = this.servicesService.findServices(servicesDto);
            return ResponseEntity.status(HttpStatus.OK).body(services);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editService(@RequestBody ServicesDto servicesDto, @PathVariable UUID id){

        try {
            var services = this.servicesService.editServices(servicesDto, id);
            return ResponseEntity.status(HttpStatus.OK).body(services);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteService(@PathVariable UUID id){

        try {
            this.servicesService.deleteService(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Serviço deletado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
