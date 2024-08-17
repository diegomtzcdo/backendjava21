package com.pruebin.prueba.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private Integer edad;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "alumno_tareas",
            joinColumns = {@JoinColumn(name = "alumno_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "tarea_id", referencedColumnName = "id")})
    private List<Tarea> tareas;
}
