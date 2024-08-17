package com.pruebin.prueba.controller;

import com.pruebin.prueba.controllers.TareaController;
import com.pruebin.prueba.dto.CustomResponseEntity;
import com.pruebin.prueba.dto.NuevaTareaDTO;
import com.pruebin.prueba.dto.TareaDTO;
import com.pruebin.prueba.servicios.TareaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TareaControllerTest {

    @Mock
    private TareaService tareaService;

    @InjectMocks
    private TareaController tareaController;

    private TareaDTO tareaDTO;
    private NuevaTareaDTO nuevaTareaDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tareaDTO = TareaDTO.builder().descripcion("Descripción 1").build();
        nuevaTareaDTO = NuevaTareaDTO.builder().descripcion("Descripción Nueva").build();
    }

    @Test
    void testObtenerTodasLasTareas() {
        List<TareaDTO> tareaDTOList = Arrays.asList(tareaDTO);
        when(tareaService.obtenerTodosLasTareas()).thenReturn(CustomResponseEntity.success200(tareaDTOList, "Lista obtenida"));

        CustomResponseEntity<List<TareaDTO>> response = tareaController.obtenerTodasLasTareas();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tareaDTOList, response.getBody().getData());
    }

    @Test
    void testObtenerTarea() {
        when(tareaService.obtenerTareaPorID(1L)).thenReturn(CustomResponseEntity.success200(tareaDTO, "Tarea obtenida"));

        CustomResponseEntity<TareaDTO> response = tareaController.obtenerTarea(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tareaDTO, response.getBody().getData());
    }

    @Test
    void testCrearTarea() {
        when(tareaService.nuevaTarea(any(NuevaTareaDTO.class))).thenReturn(CustomResponseEntity.customStatus(tareaDTO, "Tarea creada", HttpStatus.CREATED));

        CustomResponseEntity<TareaDTO> response = tareaController.crearTarea(nuevaTareaDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(tareaDTO, response.getBody().getData());
    }

    @Test
    void testActualizarTarea() {
        when(tareaService.actualizarTarea(any(TareaDTO.class), eq(1L))).thenReturn(CustomResponseEntity.success200(tareaDTO, "Tarea actualizada"));

        CustomResponseEntity<TareaDTO> response = tareaController.actualizarTarea(1L, tareaDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tareaDTO, response.getBody().getData());
    }

    @Test
    void testActualizarEstatus() {
        when(tareaService.cambiarEstatus(1L, true)).thenReturn(CustomResponseEntity.success200(tareaDTO, "Estatus actualizado"));

        CustomResponseEntity<TareaDTO> response = tareaController.actualizarEstatus(1L, true);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tareaDTO, response.getBody().getData());
    }

    @Test
    void testBorrarTarea() {
        when(tareaService.eliminarTarea(1L)).thenReturn(CustomResponseEntity.success200(true, "Tarea eliminada"));

        CustomResponseEntity<Boolean> response = tareaController.borrarTarea(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody().getData());
    }
}
