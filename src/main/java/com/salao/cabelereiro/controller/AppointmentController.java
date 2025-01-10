package com.salao.cabelereiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public ResponseEntity<Object> getAppoinment(@RequestBody AppointmentDTO appointmentDTO){
        try {
            var bodyAppointment = this.appointmentService.findAppointment(appointmentDTO);
            return ResponseEntity.ok().body(bodyAppointment);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
