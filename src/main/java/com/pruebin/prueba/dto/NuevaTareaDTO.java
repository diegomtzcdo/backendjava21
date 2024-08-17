package com.pruebin.prueba.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NuevaTareaDTO {
    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;
    private boolean entregado;

    @NotNull(message = "alumnoID es requerido")
    private Long alumnoID;
}
