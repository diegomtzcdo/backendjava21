package com.pruebin.prueba.controllers;

import com.pruebin.prueba.dto.AlumnoDTO;
import com.pruebin.prueba.dto.CustomResponseEntity;
import com.pruebin.prueba.dto.NuevaTareaDTO;
import com.pruebin.prueba.dto.TareaDTO;
import com.pruebin.prueba.servicios.TareaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarea")
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @GetMapping("/todos")
    @ResponseBody
    public CustomResponseEntity<List<TareaDTO>> obtenerTodasLasTareas() {
        return tareaService.obtenerTodosLasTareas();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CustomResponseEntity<TareaDTO> obtenerTarea(@PathVariable("id") Long id) {
        return tareaService.obtenerTareaPorID(id);
    }

    @PostMapping("/crear")
    @ResponseBody
    public CustomResponseEntity<TareaDTO> crearTarea(@Valid @RequestBody NuevaTareaDTO tareaNueva) {
        return tareaService.nuevaTarea(tareaNueva);
    }

    @PutMapping("/actualizar/{id}")
    @ResponseBody
    public CustomResponseEntity<TareaDTO> actualizarTarea(@PathVariable("id") Long id, @Valid @RequestBody TareaDTO tareaActualizada) {
        return tareaService.actualizarTarea(tareaActualizada, id);
    }

    @PostMapping("/estatus/{id}/{estatus}")
    @ResponseBody
    public  CustomResponseEntity<TareaDTO> actualizarEstatus(@PathVariable("id") Long id, @PathVariable("estatus") Boolean estatus) {
        return tareaService.cambiarEstatus(id, estatus);
    }

    @DeleteMapping("/borrar/{id}")
    @ResponseBody
    public CustomResponseEntity<Boolean> borrarTarea(@PathVariable("id") Long id) {
        return tareaService.eliminarTarea(id);
    }
}
