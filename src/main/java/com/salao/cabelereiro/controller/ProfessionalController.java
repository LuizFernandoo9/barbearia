package com.salao.cabelereiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salao.cabelereiro.dtos.ProfessionalDTO;
import com.salao.cabelereiro.service.ProfessionalService;

@RestController
@RequestMapping("/professional")
public class ProfessionalController {

    @Autowired
    private ProfessionalService professionalService;

    public ResponseEntity<Object> saveProfessional(@RequestBody ProfessionalDTO professionalDTO){
        try {
            var professional = this.professionalService.createProfessional(professionalDTO);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(professional);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
