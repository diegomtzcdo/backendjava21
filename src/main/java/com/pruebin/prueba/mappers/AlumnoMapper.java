package com.pruebin.prueba.mappers;

import com.pruebin.prueba.dto.AlumnoDTO;
import com.pruebin.prueba.dto.TareaDTO;
import com.pruebin.prueba.entities.Alumno;
import com.pruebin.prueba.entities.Tarea;

import java.util.List;
import java.util.stream.Collectors;

public class AlumnoMapper {

    public static AlumnoDTO mapToAlumnoDTO(Alumno alumno) {
        List<TareaDTO> tareaDTOS = null;
        if(alumno.getTareas() != null) {
            tareaDTOS = alumno.getTareas().stream().map(TareaMapper::mapToTareaDTO).collect(Collectors.toList());
        }
        return AlumnoDTO.builder()
                .nombre(alumno.getNombre())
                .edad(alumno.getEdad())
                .apellido(alumno.getApellido())
                .tareaDTOS(tareaDTOS)
                .build();
    }

    public static Alumno mapToAlumno(AlumnoDTO alumnoDTO) {
        List<Tarea> tareas = null;
        if(alumnoDTO.getTareaDTOS() != null) {
            tareas = alumnoDTO.getTareaDTOS().stream().map(TareaMapper::mapToTarea).collect(Collectors.toList());
        }
        return Alumno.builder()
                .nombre(alumnoDTO.getNombre())
                .apellido(alumnoDTO.getApellido())
                .edad(alumnoDTO.getEdad())
                .tareas(tareas)
                .build();
    }
}
