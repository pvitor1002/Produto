package com.Produto.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Produto.Model.Produto;
import com.Produto.Service.ProdutoService;

@RestController
public class ProdutoController {
	
	//@Autowired
	private ProdutoService prodserv = new ProdutoService();
	
	@GetMapping("produto/{id}")
	public String buscaProduto(@PathVariable("id") final int id) {
		prodserv.Connect();
		String resposta = prodserv.buscaProduto(id);
		prodserv.Close();
		return resposta;
	}
	
	@PostMapping("/produto")
	public String criarProduto(@RequestBody Produto produto) {
		prodserv.Connect();
		String resposta = prodserv.adicionarProduto(produto);
		prodserv.Close();
		return resposta;
	}
	@PutMapping("/produto")
	public String atualizarProduto(@RequestBody Produto produto) {
		prodserv.Connect();
		String resposta = prodserv.atualizarProduto(produto);
		prodserv.Close();
		return resposta;
	}
	@DeleteMapping("produto/{id}")
	public String deletarProduto(@PathVariable("id") final int id) {
		prodserv.Connect();
		String resultado = prodserv.deletarProduto(id);
		prodserv.Close();
		return resultado;
	}
	
}
