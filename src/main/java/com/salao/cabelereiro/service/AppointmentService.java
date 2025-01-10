package com.salao.cabelereiro.service;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salao.cabelereiro.dtos.AppointmentDTO;
import com.salao.cabelereiro.dtos.ProfessionalDTO;
import com.salao.cabelereiro.dtos.ServicesDto;
import com.salao.cabelereiro.exception.AppointmentNotAvailable;
import com.salao.cabelereiro.exception.ProfessionalNotFoundException;
import com.salao.cabelereiro.exception.ServicesNotFoundException;
import com.salao.cabelereiro.model.AppointmentModel;
import com.salao.cabelereiro.model.ProfessionalModel;
import com.salao.cabelereiro.model.ServicesModel;
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

    public ProfessionalModel getProfessionalsByName(String name){
        return  this.professionalRepository.findByName(name).orElseThrow(()->{
            throw new ProfessionalNotFoundException();
        });
    }

    public ServicesModel getServicesByName(String name){
        return this.servicesRepository.findByName(name).orElseThrow(()->{
            throw new ServicesNotFoundException();
        });
    }

    public void validateAppointmentAvailability(LocalDateTime dateTimeAppointment){
        this.appointmentRepository.findByNewAppointment(dateTimeAppointment).ifPresent(newAppointment -> {
            throw new AppointmentNotAvailable();
        });

        if(dateTimeAppointment.toLocalTime().isAfter(LocalTime.of(17, 0))){
            throw new AppointmentNotAvailable();
        }
    }

    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO){
       var professional = getProfessionalsByName(appointmentDTO.getProfessionalName());

       var services = getServicesByName(appointmentDTO.getServiceName());

       validateAppointmentAvailability(appointmentDTO.getNewAppointment());

        var newAppointment = AppointmentModel.builder()
        .professional(professional)
        .service(services)
        .newAppointment(appointmentDTO.getNewAppointment()).build();

        this.appointmentRepository.save(newAppointment);

        return AppointmentDTO.builder()
        .professionalName(professional.getName())
        .serviceName(services.getName())
        .newAppointment(appointmentDTO.getNewAppointment()).build();

    }

    public AppointmentDTO findAppointment(AppointmentDTO appointmentDTO){
        var appointment = this.appointmentRepository.findByNewAppointment(appointmentDTO.getNewAppointment()).orElseThrow(()->{
            throw new AppointmentNotAvailable();
        });

        return AppointmentDTO.builder()
        .professionalName(appointment.getProfessional().getName())
        .serviceName(appointment.getService().getName())
        .newAppointment(appointment.getNewAppointment()).build();
    }
    
}
