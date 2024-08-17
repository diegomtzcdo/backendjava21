/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pruebin.prueba.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class OperacionNoPermitidaExcepcion extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String mensaje;
    
    public OperacionNoPermitidaExcepcion(String mensaje) {
        super(String.format(mensaje));
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
    
}
