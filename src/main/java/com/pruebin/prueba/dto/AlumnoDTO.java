package com.pruebin.prueba.dto;

import com.pruebin.prueba.entities.Tarea;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoDTO {
    @NotBlank(message = "Nombre requerido")
    @Size(min = 4, max = 100, message = "Nombre debe tener entre 4 and 100 caracteres")
    private String nombre;

    @NotBlank(message = "Apellido requerido")
    @Size(min = 4, max = 200, message = "Apellido debe tener entre 4 and 100 caracteres")
    private String apellido;

    @NotNull(message = "Edad requerida")
    @Min(value = 5, message = "La edad no debe ser menor de 5")
    @Max(value = 150, message = "La edad no debe ser mayor de 150")
    private Integer edad;
    private List<TareaDTO> tareaDTOS;
}
