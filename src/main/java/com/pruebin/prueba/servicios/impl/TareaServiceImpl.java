package com.pruebin.prueba.servicios.impl;

import com.pruebin.prueba.dto.CustomResponseEntity;
import com.pruebin.prueba.dto.NuevaTareaDTO;
import com.pruebin.prueba.dto.TareaDTO;
import com.pruebin.prueba.entities.Alumno;
import com.pruebin.prueba.entities.Tarea;
import com.pruebin.prueba.excepciones.ResourceNotFoundException;
import com.pruebin.prueba.mappers.TareaMapper;
import com.pruebin.prueba.repositorios.AlumnoRepository;
import com.pruebin.prueba.repositorios.TareaRepository;
import com.pruebin.prueba.servicios.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TareaServiceImpl implements TareaService {

    @Autowired
    private TareaRepository tareaRepository;
    @Autowired
    private AlumnoRepository alumnoRepository;

    @Override
    public CustomResponseEntity<List<TareaDTO>> obtenerTodosLasTareas() {
        List<Tarea> tareas = tareaRepository.findAll();
        return CustomResponseEntity.success200(
                tareas.stream().map(TareaMapper::mapToTareaDTO).collect(Collectors.toList()),
                "Tareas obtenidas");
    }

    @Override
    public CustomResponseEntity<TareaDTO> obtenerTareaPorID(Long id) {
        Tarea tarea = tareaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tareas", "id", id));
        return CustomResponseEntity.success200(TareaMapper.mapToTareaDTO(tarea), "Tarea Obtenida");
    }

    @Override
    public CustomResponseEntity<TareaDTO> nuevaTarea(NuevaTareaDTO tarea) {
        Tarea tareaNueva = TareaMapper.mapToTarea(tarea);
        Alumno alumno = alumnoRepository.findById(tarea.getAlumnoID())
                .orElseThrow(() -> new ResourceNotFoundException("Alumnos", "id", tarea.getAlumnoID()));
        tareaNueva.setAlumno(alumno);
        alumno.getTareas().add(tareaNueva);
        alumnoRepository.save(alumno);
        return CustomResponseEntity.success200(TareaMapper.mapToTareaDTO(tareaNueva), "Tarea creada");
    }

    @Override
    public CustomResponseEntity<TareaDTO> actualizarTarea(TareaDTO tarea, Long id) {
        Tarea tareaActualizar = tareaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tareas", "id", id));
        tareaActualizar.setDescripcion(tarea.getDescripcion());
        tareaActualizar.setEntregada(tareaActualizar.isEntregada());
        return CustomResponseEntity.success200(TareaMapper.mapToTareaDTO(tareaRepository.save(tareaActualizar)), "Tarea Actualizada");
    }

    @Override
    public CustomResponseEntity<TareaDTO> cambiarEstatus(Long id, boolean estatus) {
        Tarea tareaActualizar = tareaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tareas", "id", id));
        tareaActualizar.setEntregada(estatus);
        return CustomResponseEntity.success200(TareaMapper.mapToTareaDTO(tareaRepository.save(tareaActualizar)), "Estatus Actualizado");
    }

    @Override
    public CustomResponseEntity<Boolean> eliminarTarea(Long id) {
        Tarea tareaEliminar = tareaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tareas", "id", id));
        Alumno alumno = alumnoRepository.findById(tareaEliminar.getAlumno().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Alumnos", "id", tareaEliminar.getAlumno().getId()));
        alumno.getTareas().remove(tareaEliminar);
        tareaRepository.delete(tareaEliminar);
        alumnoRepository.save(alumno);
        return CustomResponseEntity.success200(true, "Tarea Eliminada");
    }
}
