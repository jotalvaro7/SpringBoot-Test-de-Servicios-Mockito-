package com.osoriojulio.test.springboot.app;

import com.osoriojulio.test.springboot.app.models.Banco;
import com.osoriojulio.test.springboot.app.models.Cuenta;

import java.math.BigDecimal;

public class Datos {
    public static final Cuenta CUENTA_001 = new Cuenta(1L, "Andres", new BigDecimal("1000"));
    public static final Cuenta CUENTA_002 = new Cuenta(2L, "Julio", new BigDecimal("2000"));
    public static final Banco BANCO = new Banco(1L, "Bancolombia", 0);
}
