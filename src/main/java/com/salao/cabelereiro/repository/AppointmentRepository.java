package com.salao.cabelereiro.repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salao.cabelereiro.model.AppointmentModel;

public interface AppointmentRepository extends JpaRepository<AppointmentModel, UUID>{

    Optional<AppointmentModel> findByNewAppointment(LocalDateTime newAppointment);
}
