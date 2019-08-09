package com.Produto.Test.Service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import com.Produto.Model.Produto;
import com.Produto.Repository.ProdutoRepository;
import com.Produto.Service.ProdutoService;

public class ProdutoServiceTest {
	
	@Mock
	ProdutoRepository prodrep;	
	
	@SpyBean
	@InjectMocks
	ProdutoService prodserv;
	
	Produto produto;
	
	@Before
	public void setUp() {
		prodserv = spy(ProdutoService.class);
		produto = new Produto();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void ProdutoServiceAdicionarProdutoInvalidoIdTest() {
		produto.setId(-1);
		produto.setNome("Teste");
		produto.setValor(10.00);
		assertEquals("Falha ao criar", prodserv.adicionarProduto(produto));
	}
	
	@Test
	public void ProdutoServiceAdicionarProdutoIdExistenteTest() {
		produto.setId(1);
		produto.setNome("Teste");
		produto.setValor(10.00);
		when(prodserv.buscaProduto(1)).thenReturn("Sucesso ao encontrar");
		assertEquals("Falha ao criar", prodserv.adicionarProduto(produto));
	}
	
	@Test
	public void ProdutoServiceAdicionarProdutoValorInvalidoTest() {
		produto.setId(2);
		produto.setNome("Teste");
		produto.setValor(-10.00);
		when(prodserv.buscaProduto(1)).thenReturn("Falha ao encontrar");
		assertEquals("Falha ao criar", prodserv.adicionarProduto(produto));
	}
	
	@Test
	public void ProdutoServiceAdicionarProdutoNullTest() {
		Produto produto =  null;
		when(prodserv.buscaProduto(1)).thenReturn("Falha ao encontrar");
		assertEquals("Falha ao criar", prodserv.adicionarProduto(produto));
	}
	
	@Test
	public void ProdutoServiceBuscarProdutoIdInvalidoTest() {
		assertEquals("Falha ao encontrar", prodserv.buscaProduto(-1));
	}
	
	@Test
	public void ProdutoServiceBuscarProdutoIdNaoExistenteTest() {
		when(prodrep.buscar(1)).thenReturn("Falha ao encontrar");
		assertEquals("Falha ao encontrar", prodserv.buscaProduto(1));
		verify(prodrep).buscar(1);
	}
	
	@Test
	public void ProdutoServiceAtualizarProdutoIdInvalidoTest() {
		produto.setId(-1);
		produto.setNome("Teste");
		produto.setValor(10.00);
		assertEquals("Falha ao atualizar", prodserv.atualizarProduto(produto));
	}
	
	@Test
	public void ProdutoServiceAtualizarProdutoIdExistenteTest() {
		produto.setId(1);
		produto.setNome("Teste");
		produto.setValor(10.00);
		when(prodserv.buscaProduto(1)).thenReturn("Sucesso ao encontrar");
		assertEquals("Falha ao atualizar", prodserv.atualizarProduto(produto));
	}
	
	@Test
	public void ProdutoServiceAtualizarProdutoValorInvalidoTest() {
		produto.setId(3);
		produto.setNome("Teste");
		produto.setValor(-10.00);
		when(prodserv.buscaProduto(3)).thenReturn("Sucesso ao encontrar");
		assertEquals("Falha ao atualizar", prodserv.atualizarProduto(produto));
	}
	
	@Test
	public void ProdutoServiceAtualizarProdutoNullTest() {
		Produto produto = null;
		when(prodserv.buscaProduto(3)).thenReturn("Sucesso ao encontrar");
		assertEquals("Falha ao atualizar", prodserv.atualizarProduto(produto));
	}
	
	@Test
	public void ProdutoServiceDeletarProdutoIdInvalidoTest() {
		assertEquals("Falha ao deletar", prodserv.deletarProduto(-1));
	}
	
	@Test
	public void ProdutoServiceDeletarProdutoIdExistenteTest() {
		when(prodserv.buscaProduto(1)).thenReturn("Sucesso ao encontrar");
		assertEquals("Falha ao deletar", prodserv.deletarProduto(1));
	}
}
