package com.Produto.Test.Model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.Produto.Model.Produto;

public class ProdutoTest {
	
	Produto produto;
	
	@Before
	public void setUp() {
		produto = new Produto();
	}
	@Test
	public void ProdutoGetSetNomeTest() {
		produto.setNome("Carne");
		assertEquals("Carne", produto.getNome());
	}
	@Test
	public void ProdutoGetSetValorTest() {
		produto.setValor(12.50);
		assertEquals(12.50, produto.getValor(), 0.001);
	}

}
