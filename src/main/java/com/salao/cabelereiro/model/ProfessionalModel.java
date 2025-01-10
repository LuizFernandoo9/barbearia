package com.salao.cabelereiro.model;

import java.util.UUID;

import com.salao.cabelereiro.enums.Shift;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "profissionais")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "idServices", nullable = false)
    private ServicesModel specialization;

    @Enumerated(EnumType.STRING)
    private Shift shift;
}
