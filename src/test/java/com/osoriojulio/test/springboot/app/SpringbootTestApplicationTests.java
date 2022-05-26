package com.osoriojulio.test.springboot.app;

import com.osoriojulio.test.springboot.app.exceptions.DineroInsuficienteException;
import com.osoriojulio.test.springboot.app.models.Banco;
import com.osoriojulio.test.springboot.app.models.Cuenta;
import com.osoriojulio.test.springboot.app.repositories.BancoRepository;
import com.osoriojulio.test.springboot.app.repositories.CuentaRepository;
import com.osoriojulio.test.springboot.app.services.CuentaService;
import com.osoriojulio.test.springboot.app.services.CuentaServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.osoriojulio.test.springboot.app.Datos.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;


@SpringBootTest
class SpringbootTestApplicationTests {

	static CuentaRepository cuentaRepository;
	static BancoRepository bancoRepository;
	static CuentaService service;

	@BeforeEach
	void setup() {
		cuentaRepository = mock(CuentaRepository.class);
		bancoRepository = mock(BancoRepository.class);
		service = new CuentaServiceImpl(cuentaRepository, bancoRepository);
	}

	@Test
	void contextLoads() {
		when(cuentaRepository.findById(1L)).thenReturn(crearCuenta001());
		when(cuentaRepository.findById(2L)).thenReturn(crearCuenta002());
		when(bancoRepository.findById(1L)).thenReturn(crearBanco());

		BigDecimal saldoOrigen = service.revisarSalgo(1L);
		BigDecimal saldoDestino = service.revisarSalgo(2L);
		assertEquals("1000", saldoOrigen.toPlainString());
		assertEquals("2000", saldoDestino.toPlainString());

		service.transferir(1L, 2L, new BigDecimal(100), 1L);

		saldoOrigen = service.revisarSalgo(1L);
		saldoDestino = service.revisarSalgo(2L);

		assertEquals("900", saldoOrigen.toPlainString());
		assertEquals("2100", saldoDestino.toPlainString());


		int totalTransferencias = service.revisarTotalTransferencias(1L);
		assertEquals(1, totalTransferencias);

		verify(cuentaRepository, times(3)).findById(1L);
		verify(cuentaRepository, times(3)).findById(2L);
		verify(cuentaRepository, times(2)).update(any(Cuenta.class));

		verify(bancoRepository, times(2)).findById(1L);
		verify(bancoRepository).update(any(Banco.class));

	}

	@Test
	void contextLoads2() {
		when(cuentaRepository.findById(1L)).thenReturn(crearCuenta001());
		when(cuentaRepository.findById(2L)).thenReturn(crearCuenta002());
		when(bancoRepository.findById(1L)).thenReturn(crearBanco());

		BigDecimal saldoOrigen = service.revisarSalgo(1L);
		BigDecimal saldoDestino = service.revisarSalgo(2L);
		assertEquals("1000", saldoOrigen.toPlainString());
		assertEquals("2000", saldoDestino.toPlainString());

		assertThrows(DineroInsuficienteException.class, () -> {
			service.transferir(1L, 2L, new BigDecimal("1200"), 1L);
		});

		saldoOrigen = service.revisarSalgo(1L);
		saldoDestino = service.revisarSalgo(2L);

		assertEquals("1000", saldoOrigen.toPlainString());
		assertEquals("2000", saldoDestino.toPlainString());


		int totalTransferencias = service.revisarTotalTransferencias(1L);
		assertEquals(0, totalTransferencias);

		verify(cuentaRepository, times(3)).findById(1L);
		verify(cuentaRepository, times(2)).findById(2L);
		verify(cuentaRepository, never()).update(any(Cuenta.class));

		verify(bancoRepository, times(1)).findById(1L);
		verify(bancoRepository, never()).update(any(Banco.class));

	}

}
