package com.pruebin.prueba.DTO;

import com.pruebin.prueba.dto.TareaDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TareaDTOTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void validacionDescripcionVacia() {
        TareaDTO tareaDTO = TareaDTO.builder()
                .descripcion("")
                .build();

        Set<ConstraintViolation<TareaDTO>> violations = validator.validate(tareaDTO);

        assertEquals(1, violations.size());
        assertEquals("La descripción no puede estar vacía", violations.iterator().next().getMessage());
    }

    @Test
    void validacionDescripcionNull() {
        TareaDTO tareaDTO = TareaDTO.builder()
                .descripcion(null)
                .build();

        Set<ConstraintViolation<TareaDTO>> violations = validator.validate(tareaDTO);

        assertEquals(1, violations.size());
        assertEquals("La descripción no puede estar vacía", violations.iterator().next().getMessage());
    }
    

    @Test
    void validacionExito() {
        TareaDTO tareaDTO = TareaDTO.builder()
                .descripcion("Tarea de matemáticas")
                .entregado(true)
                .build();

        Set<ConstraintViolation<TareaDTO>> violations = validator.validate(tareaDTO);

        assertTrue(violations.isEmpty());
    }
}

