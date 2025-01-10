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

import com.salao.cabelereiro.dtos.ProfessionalDTO;
import com.salao.cabelereiro.service.ProfessionalService;

@RestController
@RequestMapping("/professional")
public class ProfessionalController {

    @Autowired
    private ProfessionalService professionalService;

    @PostMapping("/")
    public ResponseEntity<Object> saveProfessional(@RequestBody ProfessionalDTO professionalDTO){
        try {
            var professional = this.professionalService.createProfessional(professionalDTO);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(professional);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> getProfessional(@RequestBody ProfessionalDTO professionalDTO){
        try {
            var professional = this.professionalService.findUserByName(professionalDTO);
            return ResponseEntity.status(HttpStatus.OK).body(professional);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProfessional(@RequestBody ProfessionalDTO professionalDTO, @PathVariable UUID id){
        try {
            var professional = this.professionalService.editProfessional(professionalDTO, id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(professional);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProfessional(@PathVariable UUID id){
        try {
            this.professionalService.removeProfessional(id);
            return ResponseEntity.ok().body("Profissional deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
