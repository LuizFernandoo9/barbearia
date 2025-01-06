package com.salao.cabelereiro.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "agendamentos")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;        
    
    @ManyToOne
    @JoinColumn(name = "idProfessional", nullable = false)
    private ProfessionalModel professionalName;

    @ManyToMany
    @JoinColumn(name = "idService", nullable = false)
    private ServicesModel serviceName;

    private LocalDateTime newAppointment;
}
