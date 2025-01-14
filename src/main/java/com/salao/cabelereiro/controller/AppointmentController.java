package com.salao.cabelereiro.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salao.cabelereiro.dtos.AppointmentDTO;
import com.salao.cabelereiro.service.AppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/")
    public ResponseEntity<Object> newAppointment(@RequestBody AppointmentDTO appointmentDTO){
        try {
            var appointment = this.appointmentService.createAppointment(appointmentDTO);
            return ResponseEntity.status(HttpStatus.OK).body(appointment);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAppointment(@RequestBody AppointmentDTO appointmentDTO){
        try {
            var bodyAppointment = this.appointmentService.findAppointment(appointmentDTO);
            return ResponseEntity.ok().body(bodyAppointment);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllAppointment(){
        try {
            var bodyAppointment = this.appointmentService.findAllAppointment();
            return ResponseEntity.ok().body(bodyAppointment);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editAppointment(@RequestBody AppointmentDTO appointmentDTO, @PathVariable UUID id){
        try {
            var bodyAppointment = this.appointmentService.updateAppointment(appointmentDTO, id);
            return ResponseEntity.ok().body(bodyAppointment);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
