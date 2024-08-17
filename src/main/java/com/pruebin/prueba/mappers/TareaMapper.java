package com.pruebin.prueba.mappers;

import com.pruebin.prueba.dto.NuevaTareaDTO;
import com.pruebin.prueba.dto.TareaDTO;
import com.pruebin.prueba.entities.Tarea;

public class TareaMapper {
    public static TareaDTO mapToTareaDTO(Tarea tarea) {
        return TareaDTO.builder()
                .descripcion(tarea.getDescripcion())
                .entregado(tarea.isEntregada())
                .build();
    }

    public static Tarea mapToTarea(TareaDTO tareaDTO) {
        return Tarea.builder()
                .descripcion(tareaDTO.getDescripcion())
                .entregada(tareaDTO.isEntregado())
                .build();
    }

    public static Tarea mapToTarea(NuevaTareaDTO tareaDTO) {
        return Tarea.builder()
                .descripcion(tareaDTO.getDescripcion())
                .entregada(tareaDTO.isEntregado())
                .build();
    }
}
