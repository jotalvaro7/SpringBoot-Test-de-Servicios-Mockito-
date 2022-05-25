package com.osoriojulio.test.springboot.app;

import com.osoriojulio.test.springboot.app.repositories.BancoRepository;
import com.osoriojulio.test.springboot.app.repositories.CuentaRepository;
import com.osoriojulio.test.springboot.app.services.CuentaService;
import com.osoriojulio.test.springboot.app.services.CuentaServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;


@SpringBootTest
class SpringbootTestApplicationTests {

	static CuentaRepository cuentaRepository;
	static BancoRepository bancoRepository;
	static CuentaService service;

	@BeforeAll
	static void beforeAll() {
		cuentaRepository = mock(CuentaRepository.class);
		bancoRepository = mock(BancoRepository.class);
		service = new CuentaServiceImpl(cuentaRepository, bancoRepository);
	}

	@Test
	void contextLoads() {
		when(cuentaRepository.findById(1L)).thenReturn(Datos.CUENTA_001);
		when(cuentaRepository.findById(2L)).thenReturn(Datos.CUENTA_002);
		when(bancoRepository.findById(1L)).thenReturn(Datos.BANCO);

		BigDecimal saldoOrigen = service.revisarSalgo(1L);
		BigDecimal saldoDestino = service.revisarSalgo(2L);
		assertEquals("1000", saldoOrigen.toPlainString());
		assertEquals("2000", saldoDestino.toPlainString());

		service.transferir(1L, 2L, new BigDecimal(100), 1L);

		saldoOrigen = service.revisarSalgo(1L);
		saldoDestino = service.revisarSalgo(2L);

		assertEquals("900", saldoOrigen.toPlainString());
		assertEquals("2100", saldoDestino.toPlainString());

	}

}
