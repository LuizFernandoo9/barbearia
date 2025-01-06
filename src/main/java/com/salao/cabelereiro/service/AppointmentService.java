package com.salao.cabelereiro.service;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salao.cabelereiro.dtos.AppointmentDTO;
import com.salao.cabelereiro.exception.AppointmentNotAvailable;
import com.salao.cabelereiro.exception.ProfessionalNotFoundException;
import com.salao.cabelereiro.exception.ServicesNotFoundException;
import com.salao.cabelereiro.model.AppointmentModel;
import com.salao.cabelereiro.repository.AppointmentRepository;
import com.salao.cabelereiro.repository.ProfessionalRepository;
import com.salao.cabelereiro.repository.ServicesRepository;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ProfessionalRepository professionalRepository;

    @Autowired
    private ServicesRepository servicesRepository;

    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO){
        var professional = this.professionalRepository.findByName(appointmentDTO.getProfessionalName()).orElseThrow(()->{
            throw new ProfessionalNotFoundException();
        });

        var services = this.servicesRepository.findByName(appointmentDTO.getServiceName()).orElseThrow(()->{
            throw new ServicesNotFoundException();
        });

        this.appointmentRepository.findByNewAppointment(appointmentDTO.getNewAppointment()).ifPresent(appointment -> {
            throw new AppointmentNotAvailable();
        });
        
        var timeLimit = LocalTime.of(17, 0);

        var dateTimeAppointment = appointmentDTO.getNewAppointment();

        if (dateTimeAppointment.toLocalTime().isAfter(timeLimit)) {
            throw new AppointmentNotAvailable();
        }

        var newAppointment = AppointmentModel.builder()
        .professionalName(professional)
        .serviceName(services)
        .newAppointment(appointmentDTO.getNewAppointment()).build();

        this.appointmentRepository.save(newAppointment);

        return AppointmentDTO.builder()
        .professionalName(professional.getName())
        .serviceName(services.getName())
        .newAppointment(appointmentDTO.getNewAppointment()).build();

    }
}
