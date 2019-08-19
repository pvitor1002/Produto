package com.Produto.Service;

import java.io.IOException;

import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.Produto.Model.Produto;
import com.Produto.Repository.ProdutoRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class ProdutoService {

	private String insert = null;
	private static final String TOPIC1 = "Insert_Request";
	private static final String TOPIC2 = "Delete_Request";
	private static final String TOPIC3 = "Insert_Response";
	private static final String TOPIC4 = "Delete_Response";
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	
	@Autowired
	@InjectMocks
	private ProdutoRepository prodrep;
	
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
		kafkaTemplate.send(TOPIC2, String.valueOf(id));
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
	
	@KafkaListener(topics = TOPIC1, groupId = "group_id")
	public void consumeInsertRequest(String message) throws JsonParseException, JsonMappingException, NumberFormatException, JsonProcessingException, IOException {
		kafkaTemplate.send(TOPIC3, buscaProduto(Integer.parseInt(message)));
	}
	
	@KafkaListener(topics = TOPIC4, groupId = "group_id")
	public void consumeDeleteResponse(String message) throws JsonParseException, JsonMappingException, IOException {
		if(message.equals("Falha ao encontrar")) {
			this.insert = "deletar";
		} else {
			this.insert = "Falha ao deletar";
		}
	}
}
