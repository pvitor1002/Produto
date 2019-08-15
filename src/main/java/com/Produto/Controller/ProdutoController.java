package com.Produto.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Produto.Model.Produto;
import com.Produto.Service.ProdutoService;

@RestController
public class ProdutoController {
	
	//@Autowired
	private ProdutoService prodserv = new ProdutoService();
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@GetMapping("produto/{id}")
	public String buscaProduto(@PathVariable("id") final String id) {
		return prodserv.buscaProduto(Integer.parseInt(id));
	}
	
	@PostMapping("/produto")
	public String criarProduto(@RequestBody Produto produto) {
		kafkaTemplate.send("Insert_Request", String.valueOf(produto.getId()));
		return prodserv.adicionarProduto(produto);
	}
	@PutMapping("/produto")
	public String atualizarProduto(@RequestBody Produto produto) {
		return prodserv.atualizarProduto(produto);
	}
	@DeleteMapping("produto/{id}")
	public String deletarProduto(@PathVariable("id") final String id) {
		kafkaTemplate.send("Delete_Request", id);
		return prodserv.deletarProduto(Integer.parseInt(id));
	}
}
