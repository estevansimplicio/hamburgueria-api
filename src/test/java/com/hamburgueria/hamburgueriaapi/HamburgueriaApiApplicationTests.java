package com.hamburgueria.hamburgueriaapi;

import com.hamburgueria.hamburgueriaapi.domain.Lanche;
import com.hamburgueria.hamburgueriaapi.domain.LancheService;
import com.hamburgueria.hamburgueriaapi.domain.dto.LancheDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static junit.framework.TestCase.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HamburgueriaApiApplicationTests {

	@Autowired
	private LancheService service;

	@Test
	public void textSave() {
		Lanche lanche = new Lanche();
		lanche.setNome("Lanche teste");
		lanche.setDescricao("Descricao teste");
		lanche.setPreco(new BigDecimal(1.0));

		LancheDTO l = service.insert(lanche);

		assertNotNull(l);

		Long id = l.getId();
		assertNotNull(id);

		//buscar oobjeto

		Optional<LancheDTO> op = service.getLanchesById(id);
		assertTrue(op.isPresent());

		l = op.get();
		assertEquals("Lanche teste", l.getNome());
		assertEquals("Descricao teste", l.getDescricao());

		//deletar objeto após teste

		service.delete(id);

		//verificar se foi deletado
		assertFalse(service.getLanchesById(id).isPresent());
	}

	@Test
	public void testLista(){
		List<LancheDTO> lanches = service.getLanches();

		assertEquals(3, lanches.size());
	}

	@Test
	public void testGet(){
		Optional<LancheDTO> op = service.getLanchesById(1L);

		assertTrue(op.isPresent());

		LancheDTO l = op.get();

		assertEquals("Dorime", l.getNome());
	}

}
