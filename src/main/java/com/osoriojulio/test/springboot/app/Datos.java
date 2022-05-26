package com.osoriojulio.test.springboot.app;

import com.osoriojulio.test.springboot.app.models.Banco;
import com.osoriojulio.test.springboot.app.models.Cuenta;

import java.math.BigDecimal;

public class Datos {

    public static Cuenta crearCuenta001(){
        return new Cuenta(1L, "Andres", new BigDecimal("1000"));
    }

    public static Cuenta crearCuenta002(){
        return new Cuenta(2L, "Julio", new BigDecimal("2000"));
    }

    public static Banco crearBanco(){
        return new Banco(1L, "Bancolombia", 0);
    }
}
