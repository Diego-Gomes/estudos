package br.com.caelum.leilao.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestLeilao {

	private static Usuario diego;
	private static Usuario rafael;

	@BeforeAll
	public static void Init() {

		diego = new Usuario("Diego");
		rafael = new Usuario("Rafael");

	}

	@Test
	public void validar_lances_consecutivos_mesmo_usuario() {
		Leilao leilao = new Leilao("Video Game - PS4");

		assertEquals(0, leilao.getLances().size());

		leilao.propoe(new Lance(diego, 1500.00));
		leilao.propoe(new Lance(diego, 2000.00));

		assertEquals(1, leilao.getLances().size());
		assertEquals(1500.00, leilao.getLances().get(0).getValor());

	}

	@Test
	public void validar_qtd_de_lances_por_usuario() {
		
		Leilao leilao = new BuilderLeilao()
							.Para("Video Game - PS4")
								.Lance(diego, 1500.00)
								.Lance(rafael, 2000.00)
								.Lance(diego, 4000.00)
								.Lance(rafael, 5000.00)
								.Lance(diego, 6000.00)
								.Lance(rafael, 6500.00)
								.Lance(diego, 7000.00)
								.Lance(rafael, 8000.00)
								.Lance(diego, 9000.00)
								.Lance(rafael, 10000.00)
								.Build();


		List<Lance> lances = leilao.getLances();

		assertEquals(10, leilao.getLances().size());
		assertEquals(7900.00, lances.get(lances.size() - 1).getValor());

	}

	@Test
	public void validar_dobro_do_lance() {
		Leilao leilao = new BuilderLeilao().Para("Video Game - PS4")
				.Lance(diego, 1500.00)
				.Lance(rafael, 2000.00)
				.Lance(diego, 4000.00)
				.Lance(rafael, 5000.00)
				.Lance(diego, 6000.00)
				.Lance(rafael, 6500.00)
				.Lance(diego, 7000.00)
				.Lance(rafael, 8000.00)
				.DobrarLance(diego)
				.Lance(rafael, 9000.00)
				.Build();

		List<Lance> lances = leilao.getLances();

		assertEquals(10, leilao.getLances().size());
		assertEquals(9000.00, lances.get(lances.size() - 1).getValor());
		assertEquals(7000.00 * 2, leilao.getUltimoLanceDoUsuario(diego).getValor());

	}

}
