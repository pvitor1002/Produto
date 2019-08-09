package com.Produto.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Produto.Model.Produto;
import com.Produto.Service.ProdutoService;

@RestController
public class ProdutoController {
	
	private ProdutoService prodserv = new ProdutoService();
	
	@GetMapping("produto/{id}")
	public String buscaProduto(@PathVariable("id") final int id) {
		return prodserv.buscaProduto(id);
	}
	
	@PostMapping("produto/")
	public String criarProduto(@RequestBody Produto produto) {
		return prodserv.adicionarProduto(produto);
	}
	
}
