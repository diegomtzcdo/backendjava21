package com.pruebin.prueba.servicios.impl;

import com.pruebin.prueba.dto.AlumnoDTO;
import com.pruebin.prueba.dto.CustomResponseEntity;
import com.pruebin.prueba.entities.Alumno;
import com.pruebin.prueba.excepciones.ResourceNotFoundException;
import com.pruebin.prueba.mappers.AlumnoMapper;
import com.pruebin.prueba.repositorios.AlumnoRepository;
import com.pruebin.prueba.servicios.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public CustomResponseEntity<List<AlumnoDTO>> obtenerTodosLosAlumnos() {
        List<Alumno> alumnos = alumnoRepository.findAll();
        return CustomResponseEntity.success200(
                alumnos.stream().map(AlumnoMapper::mapToAlumnoDTO).collect(Collectors.toList()),
                "Alumnos obtenidos");
    }

    @Override
    public CustomResponseEntity<AlumnoDTO> obtenerAlumnoPorID(Long id) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alumnos", "id", id));
        return CustomResponseEntity.success200(AlumnoMapper.mapToAlumnoDTO(alumno), "Alumno obtenido");
    }

    @Override
    public CustomResponseEntity<AlumnoDTO> nuevoAlumno(AlumnoDTO alumno) {
        Alumno alumnoNuevo = AlumnoMapper.mapToAlumno(alumno);
        return CustomResponseEntity.success200(AlumnoMapper.mapToAlumnoDTO(alumnoRepository.save(alumnoNuevo)), "Alumno creado");
    }

    @Override
    public CustomResponseEntity<AlumnoDTO> actualizarAlumno(AlumnoDTO alumno, Long id) {
        Alumno alumnoActualizar = alumnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alumnos", "id", id));
        alumnoActualizar.setNombre(alumno.getNombre());
        alumnoActualizar.setApellido(alumno.getApellido());
        alumnoActualizar.setEdad(alumno.getEdad());
        return CustomResponseEntity.success200(AlumnoMapper.mapToAlumnoDTO(alumnoRepository.save(alumnoActualizar)), "Alumno actualizado");
    }

    @Override
    public CustomResponseEntity<Boolean> eliminarAlumno(Long id) {
        Alumno alumnoEliminar = alumnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Alumnos", "id", id));
        alumnoRepository.delete(alumnoEliminar);
        return CustomResponseEntity.success200(true, "Alumno eliminado");
    }
}
