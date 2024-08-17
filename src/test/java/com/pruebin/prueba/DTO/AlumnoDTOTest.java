package com.pruebin.prueba.DTO;

import com.pruebin.prueba.dto.AlumnoDTO;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlumnoDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void validacionNombreVacio() {
        AlumnoDTO alumnoDTO = AlumnoDTO.builder().nombre("").apellido("Apellido").edad(10).build();
        Set<jakarta.validation.ConstraintViolation<AlumnoDTO>> violations = validator.validate(alumnoDTO);

        assertEquals(2, violations.size());
        List<String> messages = violations.stream()
                .map(jakarta.validation.ConstraintViolation::getMessage)
                .toList();
        assertTrue(messages.contains("Nombre requerido"));
        assertTrue(messages.contains("Nombre debe tener entre 4 and 100 caracteres"));
    }

    @Test
    void validacionApellidoVacio() {
        AlumnoDTO alumnoDTO = AlumnoDTO.builder().nombre("Nombre").apellido("").edad(10).build();
        Set<jakarta.validation.ConstraintViolation<AlumnoDTO>> violations = validator.validate(alumnoDTO);

        assertEquals(2, violations.size());

        List<String> messages = violations.stream()
                .map(jakarta.validation.ConstraintViolation::getMessage)
                .toList();

        assertTrue(messages.contains("Apellido requerido"));
        assertTrue(messages.contains("Apellido debe tener entre 4 and 100 caracteres"));
    }


    @Test
    void validacionApellidoPocosCaracteres() {
        AlumnoDTO alumnoDTO = AlumnoDTO.builder().nombre("Nombre").apellido("ap").edad(10).build();
        Set<jakarta.validation.ConstraintViolation<AlumnoDTO>> violations = validator.validate(alumnoDTO);

        assertEquals(1, violations.size());
        assertEquals("Apellido debe tener entre 4 and 100 caracteres", violations.iterator().next().getMessage());
    }

    @Test
    void validacionNombrePocosCaracteres() {
        AlumnoDTO alumnoDTO = AlumnoDTO.builder().nombre("di").apellido("Apellido").edad(10).build();
        Set<jakarta.validation.ConstraintViolation<AlumnoDTO>> violations = validator.validate(alumnoDTO);

        assertEquals(1, violations.size());
        assertEquals("Nombre debe tener entre 4 and 100 caracteres", violations.iterator().next().getMessage());
    }

    @Test
    void validacionEdadFueraDeRango() {
        AlumnoDTO alumnoDTO = AlumnoDTO.builder().nombre("Nombre").apellido("Apellido").edad(200).build();
        Set<jakarta.validation.ConstraintViolation<AlumnoDTO>> violations = validator.validate(alumnoDTO);

        assertEquals(1, violations.size());
        assertEquals("La edad no debe ser mayor de 150", violations.iterator().next().getMessage());
    }

    // Agrega más pruebas para las demás validaciones
}
