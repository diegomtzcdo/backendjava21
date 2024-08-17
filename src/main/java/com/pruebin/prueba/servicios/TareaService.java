package com.pruebin.prueba.servicios;

import com.pruebin.prueba.dto.CustomResponseEntity;
import com.pruebin.prueba.dto.NuevaTareaDTO;
import com.pruebin.prueba.dto.TareaDTO;
import com.pruebin.prueba.entities.Tarea;

import java.util.List;

public interface TareaService {
    CustomResponseEntity<List<TareaDTO>> obtenerTodosLasTareas();
    CustomResponseEntity<TareaDTO> obtenerTareaPorID(Long id);
    CustomResponseEntity<TareaDTO> nuevaTarea(NuevaTareaDTO Tarea);
    CustomResponseEntity<TareaDTO> actualizarTarea(TareaDTO Tarea, Long id);
    CustomResponseEntity<TareaDTO> cambiarEstatus(Long id, boolean estatus);
    CustomResponseEntity<Boolean> eliminarTarea(Long id);
}
