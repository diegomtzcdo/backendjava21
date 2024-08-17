package com.pruebin.prueba.servicios;

import com.pruebin.prueba.dto.AlumnoDTO;
import com.pruebin.prueba.dto.CustomResponseEntity;
import com.pruebin.prueba.entities.Alumno;
import com.pruebin.prueba.excepciones.ResourceNotFoundException;
import com.pruebin.prueba.mappers.AlumnoMapper;
import com.pruebin.prueba.repositorios.AlumnoRepository;
import com.pruebin.prueba.servicios.impl.AlumnoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AlumnoServiceTest {

    @Mock
    private AlumnoRepository alumnoRepository;

    @InjectMocks
    private AlumnoServiceImpl alumnoService;

    private Alumno alumno;
    private AlumnoDTO alumnoDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        alumno = Alumno.builder().id(1L).nombre("Juan").apellido("Perez").edad(20).build();
        alumnoDTO = AlumnoDTO.builder().nombre("Juan").apellido("Perez").edad(20).build();
    }

    @Test
    void testObtenerTodosLosAlumnos() {
        when(alumnoRepository.findAll()).thenReturn(Arrays.asList(alumno));

        CustomResponseEntity<List<AlumnoDTO>> response = alumnoService.obtenerTodosLosAlumnos();

        assertNotNull(response);
        assertEquals(1, response.getBody().getData().size());
        assertEquals("Juan", response.getBody().getData().get(0).getNombre());
        verify(alumnoRepository, times(1)).findAll();
    }

    @Test
    void testObtenerAlumnoPorID_Existente() {
        when(alumnoRepository.findById(1L)).thenReturn(Optional.of(alumno));

        CustomResponseEntity<AlumnoDTO> response = alumnoService.obtenerAlumnoPorID(1L);

        assertNotNull(response);
        assertEquals("Juan", response.getBody().getData().getNombre());
        verify(alumnoRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerAlumnoPorID_NoExistente() {
        when(alumnoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> alumnoService.obtenerAlumnoPorID(1L));
        verify(alumnoRepository, times(1)).findById(1L);
    }

    @Test
    void testNuevoAlumno() {
        when(alumnoRepository.save(any(Alumno.class))).thenReturn(alumno);

        CustomResponseEntity<AlumnoDTO> response = alumnoService.nuevoAlumno(alumnoDTO);

        assertNotNull(response);
        assertEquals("Juan", response.getBody().getData().getNombre());
        verify(alumnoRepository, times(1)).save(any(Alumno.class));
    }

    @Test
    void testActualizarAlumno_Existente() {
        when(alumnoRepository.findById(1L)).thenReturn(Optional.of(alumno));
        when(alumnoRepository.save(any(Alumno.class))).thenReturn(alumno);

        CustomResponseEntity<AlumnoDTO> response = alumnoService.actualizarAlumno(alumnoDTO, 1L);

        assertNotNull(response);
        assertEquals("Juan", response.getBody().getData().getNombre());
        verify(alumnoRepository, times(1)).findById(1L);
        verify(alumnoRepository, times(1)).save(any(Alumno.class));
    }

    @Test
    void testActualizarAlumno_NoExistente() {
        when(alumnoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> alumnoService.actualizarAlumno(alumnoDTO, 1L));
        verify(alumnoRepository, times(1)).findById(1L);
        verify(alumnoRepository, times(0)).save(any(Alumno.class));
    }

    @Test
    void testEliminarAlumno_Existente() {
        when(alumnoRepository.findById(1L)).thenReturn(Optional.of(alumno));

        CustomResponseEntity<Boolean> response = alumnoService.eliminarAlumno(1L);

        assertNotNull(response);
        assertTrue(response.getBody().getData());
        verify(alumnoRepository, times(1)).findById(1L);
        verify(alumnoRepository, times(1)).delete(alumno);
    }

    @Test
    void testEliminarAlumno_NoExistente() {
        when(alumnoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> alumnoService.eliminarAlumno(1L));
        verify(alumnoRepository, times(1)).findById(1L);
        verify(alumnoRepository, times(0)).delete(any(Alumno.class));
    }
}

