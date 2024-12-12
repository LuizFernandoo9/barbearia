package com.salao.cabelereiro.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServicesDto {

    private String name;
    private String description;
    private Integer duration;
}
