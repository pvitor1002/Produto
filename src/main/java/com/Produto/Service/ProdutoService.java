package com.Produto.Service;

import org.json.JSONObject;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import com.Produto.Model.Produto;
import com.Produto.Repository.ProdutoRepository;

public class ProdutoService {

	private String insert = null;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@InjectMocks
	private ProdutoRepository prodrep = new ProdutoRepository();
	
	public String buscaProduto(int id) {
		String resposta = "Falha ao encontrar";
		if(id >= 0)
			resposta = prodrep.buscar(id);
		return resposta;
	}

	public String adicionarProduto(Produto produto) {
		String resposta = "Falha ao criar";
		if(produto != null && produto.getNome() != null && produto.getId() >= 0 && produto.getValor() >= 0.00 && buscaProduto(produto.getId()).equals("Falha ao encontrar")) {
			resposta = prodrep.adicionar(produto);
		}
		return resposta;
	}

	public String atualizarProduto(Produto produto) {
		String resposta = "Falha ao atualizar";
		if(produto != null && produto.getId() >= 0 && produto.getValor() >= 0.00 && !buscaProduto(produto.getId()).equals("Falha ao encontrar"))
			resposta = prodrep.atualizar(produto);
		return resposta;
	}

	public String deletarProduto(int id) {
		String resposta = "Falha ao deletar";
		while(this.insert == null) {
			Thread.yield();
		}
		if(!(this.insert == "Falha ao deletar")) {
			if(id >= 0 && !buscaProduto(id).equals("Falha ao encontrar"))
				resposta = prodrep.deletar(id);
		}
		this.insert = null;
		return resposta;
	}
	
	@KafkaListener(topics = "Insert_Request", groupId = "group_id")
	public void Insert(String message) {
		kafkaTemplate.send("Insert_Response", buscaProduto(Integer.parseInt(message)));
	}
	
	@KafkaListener(topics = "Delete_Response", groupId = "group_id")
	public void Delete(String message) {
		if(message.equals("Falha ao encontrar")) {
			this.insert = "deletar";
		} else {
			JSONObject jo = new JSONObject(message);
			if(jo.getInt("quantidade") == 0)
				this.insert = "deletar";
			else
				this.insert = "Falha ao deletar";
		}
	}
}
