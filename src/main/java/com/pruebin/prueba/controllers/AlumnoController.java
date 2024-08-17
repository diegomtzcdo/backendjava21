package com.pruebin.prueba.controllers;

import com.pruebin.prueba.dto.AlumnoDTO;
import com.pruebin.prueba.dto.CustomResponseEntity;
import com.pruebin.prueba.servicios.AlumnoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("/todos")
    @ResponseBody
    public CustomResponseEntity<List<AlumnoDTO>> obtenerTodosLosAlumnos() {
        return alumnoService.obtenerTodosLosAlumnos();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CustomResponseEntity<AlumnoDTO> obtenerAlumno(@PathVariable("id") Long id) {
        return alumnoService.obtenerAlumnoPorID(id);
    }

    @PostMapping("/crear")
    @ResponseBody
    public CustomResponseEntity<AlumnoDTO> crearAlumno(@Valid @RequestBody AlumnoDTO alumnoNuevo) {
        return alumnoService.nuevoAlumno(alumnoNuevo);
    }

    @PutMapping("/actualizar/{id}")
    @ResponseBody
    public CustomResponseEntity<AlumnoDTO> actualizarAlumno(@PathVariable("id") Long id, @Valid @RequestBody AlumnoDTO alumnoActualizado) {
        return alumnoService.actualizarAlumno(alumnoActualizado, id);
    }

    @DeleteMapping("/borrar/{id}")
    @ResponseBody
    public CustomResponseEntity<Boolean> borrarAlumno(@PathVariable("id") Long id) {
        return alumnoService.eliminarAlumno(id);
    }
}
