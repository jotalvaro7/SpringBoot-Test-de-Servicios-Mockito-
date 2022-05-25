package com.osoriojulio.test.springboot.app.exceptions;

public class DineroInsuficienteException  extends  RuntimeException{

    public DineroInsuficienteException(String mensaje){
        super(mensaje);
    }
}
