package com.pruebin.prueba.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class DetallesErrorDTO {

    private Date timestamp;
    private String message;
    private String details;
    
}
