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
		return prodserv.buscaProduto(id);
	}
	
	@PostMapping("/produto")
	public String criarProduto(@RequestBody Produto produto) {
		return prodserv.adicionarProduto(produto);
	}
	@PutMapping("/produto")
	public String atualizarProduto(@RequestBody Produto produto) {
		return prodserv.atualizarProduto(produto);
	}
	@DeleteMapping("produto/{id}")
	public String deletarProduto(@PathVariable("id") final int id) {
		return prodserv.deletarProduto(id);
	}
	
}
