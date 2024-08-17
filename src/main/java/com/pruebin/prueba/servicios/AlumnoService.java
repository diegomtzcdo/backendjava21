package com.pruebin.prueba.servicios;

import com.pruebin.prueba.dto.AlumnoDTO;
import com.pruebin.prueba.dto.CustomResponseEntity;

import java.util.List;

public interface AlumnoService {
    CustomResponseEntity<List<AlumnoDTO>> obtenerTodosLosAlumnos();
    CustomResponseEntity<AlumnoDTO> obtenerAlumnoPorID(Long id);
    CustomResponseEntity<AlumnoDTO> nuevoAlumno(AlumnoDTO alumno);
    CustomResponseEntity<AlumnoDTO> actualizarAlumno(AlumnoDTO alumno, Long id);
    CustomResponseEntity<Boolean> eliminarAlumno(Long id);
}
