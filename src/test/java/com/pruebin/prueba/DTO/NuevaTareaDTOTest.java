package com.pruebin.prueba.DTO;

import com.pruebin.prueba.dto.NuevaTareaDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NuevaTareaDTOTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void validacionDescripcionVacia() {
        NuevaTareaDTO nuevaTareaDTO = NuevaTareaDTO.builder()
                .descripcion("")
                .alumnoID(1L)
                .build();

        Set<ConstraintViolation<NuevaTareaDTO>> violations = validator.validate(nuevaTareaDTO);

        assertEquals(1, violations.size());
        assertEquals("La descripción no puede estar vacía", violations.iterator().next().getMessage());
    }

    @Test
    void validacionDescripcionNull() {
        NuevaTareaDTO nuevaTareaDTO = NuevaTareaDTO.builder()
                .descripcion(null)
                .alumnoID(1L)
                .build();

        Set<ConstraintViolation<NuevaTareaDTO>> violations = validator.validate(nuevaTareaDTO);

        assertEquals(1, violations.size());
        assertEquals("La descripción no puede estar vacía", violations.iterator().next().getMessage());
    }

    @Test
    void validacionAlumnoIDNull() {
        NuevaTareaDTO nuevaTareaDTO = NuevaTareaDTO.builder()
                .descripcion("Tarea de matemáticas")
                .alumnoID(null)
                .build();

        Set<ConstraintViolation<NuevaTareaDTO>> violations = validator.validate(nuevaTareaDTO);

        assertEquals(1, violations.size());
        assertEquals("alumnoID es requerido", violations.iterator().next().getMessage());
    }

    @Test
    void validacionExito() {
        NuevaTareaDTO nuevaTareaDTO = NuevaTareaDTO.builder()
                .descripcion("Tarea de matemáticas")
                .alumnoID(1L)
                .build();

        Set<ConstraintViolation<NuevaTareaDTO>> violations = validator.validate(nuevaTareaDTO);

        assertTrue(violations.isEmpty());
    }
}
