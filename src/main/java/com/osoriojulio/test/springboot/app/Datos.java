package com.osoriojulio.test.springboot.app;

import com.osoriojulio.test.springboot.app.models.Banco;
import com.osoriojulio.test.springboot.app.models.Cuenta;

import java.math.BigDecimal;
import java.util.Optional;

public class Datos {

    public static Optional<Cuenta> crearCuenta001(){
        return Optional.of(new Cuenta(1L, "Andres", new BigDecimal("1000")));
    }

    public static Optional<Cuenta> crearCuenta002(){
        return Optional.of(new Cuenta(2L, "Julio", new BigDecimal("2000")));
    }

    public static Optional<Banco> crearBanco(){
        return Optional.of(new Banco(1L, "Bancolombia", 0));
    }
}
