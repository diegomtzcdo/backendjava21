package com.pruebin.prueba.controller;

import com.pruebin.prueba.controllers.AlumnoController;
import com.pruebin.prueba.dto.AlumnoDTO;
import com.pruebin.prueba.dto.CustomResponseEntity;
import com.pruebin.prueba.servicios.AlumnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AlumnoControllerTest {
    @Mock
    private AlumnoService alumnoService;

    @InjectMocks
    private AlumnoController alumnoController;

    private static AlumnoDTO ALUMNO_PRUEBA = AlumnoDTO.builder()
            .nombre("Juan")
            .apellido("Sin Miedo")
            .edad(25)
            .build();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void obtenerTodosLosAlumnos() {
        List<AlumnoDTO> alumnos = Collections.singletonList(ALUMNO_PRUEBA);
        when(alumnoService.obtenerTodosLosAlumnos()).thenReturn(CustomResponseEntity.success200(alumnos, "Alumnos obtenidos"));

        CustomResponseEntity<List<AlumnoDTO>> response = alumnoController.obtenerTodosLosAlumnos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(alumnos, response.getBody().getData());
        verify(alumnoService, times(1)).obtenerTodosLosAlumnos();
    }

    @Test
    void obtenerAlumno() {
        Long id = 1L;
        when(alumnoService.obtenerAlumnoPorID(id)).thenReturn(CustomResponseEntity.success200(ALUMNO_PRUEBA, "Alumno obtenido"));

        CustomResponseEntity<AlumnoDTO> response = alumnoController.obtenerAlumno(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ALUMNO_PRUEBA, response.getBody().getData());
        verify(alumnoService, times(1)).obtenerAlumnoPorID(id);
    }

    @Test
    void crearAlumno() {
        AlumnoDTO nuevoAlumno = ALUMNO_PRUEBA;
        when(alumnoService.nuevoAlumno(nuevoAlumno)).thenReturn(CustomResponseEntity.success200(ALUMNO_PRUEBA, "Alumno creado"));

        CustomResponseEntity<AlumnoDTO> response = alumnoController.crearAlumno(nuevoAlumno);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(nuevoAlumno, response.getBody().getData());
        verify(alumnoService, times(1)).nuevoAlumno(nuevoAlumno);
    }

    @Test
    void actualizarAlumno() {
        Long id = 1L;
        AlumnoDTO alumnoActualizado = ALUMNO_PRUEBA;
        when(alumnoService.actualizarAlumno(alumnoActualizado, id)).thenReturn(CustomResponseEntity.success200(alumnoActualizado, "Alumno actualizado"));

        CustomResponseEntity<AlumnoDTO> response = alumnoController.actualizarAlumno(id, alumnoActualizado);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(alumnoActualizado, response.getBody().getData());
        verify(alumnoService, times(1)).actualizarAlumno(alumnoActualizado, id);
    }

    @Test
    void borrarAlumno() {
        Long id = 1L;
        when(alumnoService.eliminarAlumno(id)).thenReturn(CustomResponseEntity.success200(true, "Alumno eliminado"));

        CustomResponseEntity<Boolean> response = alumnoController.borrarAlumno(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody().getData());
        verify(alumnoService, times(1)).eliminarAlumno(id);
    }
}
