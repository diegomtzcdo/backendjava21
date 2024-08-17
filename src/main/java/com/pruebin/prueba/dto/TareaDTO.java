package com.pruebin.prueba.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TareaDTO {
    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;
    private boolean entregado;
}
