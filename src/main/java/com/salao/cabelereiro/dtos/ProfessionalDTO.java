package com.salao.cabelereiro.dtos;

import com.salao.cabelereiro.enums.Shift;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalDTO {

    private String name;
    private String specialization;
    private Shift shift;
}  
